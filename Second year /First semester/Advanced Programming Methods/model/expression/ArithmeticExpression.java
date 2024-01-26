package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class ArithmeticExpression implements Expression{
    private Expression e1;
    private Expression e2;
    private char operator;
    public ArithmeticExpression(Expression e1, Expression e2, char operator){
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        Value v1, v2;
        v1=e1.evaluate(symTable, heap);
        if(v1.getType().equals(new IntType())){
            v2 = e2.evaluate(symTable, heap);
            if(v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(this.operator == '+') return new IntValue(n1+n2);
                else if(this.operator == '-') return new IntValue(n1-n2);
                else if(this.operator == '*') return new IntValue(n1*n2);
                else if(this.operator == '/')
                {
                    if(n2==0) throw new MyException("Division by zero!");
                    else return new IntValue(n1/n2);
                }
            }else throw new MyException("Second operand is not an integer!");
        }else throw new MyException("First operand is not an integer!");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new IntType();
            }else
                throw new MyException("Second operand is not an integer.");
        }else
            throw new MyException("First operand is not an integer.");
    }

    public String toString()
    {
        return e1.toString() + " " + operator + " " + e2.toString();
    }
    public Expression deepCopy(){
        return new ArithmeticExpression(e1.deepCopy(), e2.deepCopy(), operator);
    }
}
