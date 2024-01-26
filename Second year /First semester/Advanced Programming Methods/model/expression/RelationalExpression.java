package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

import java.util.Objects;

public class RelationalExpression implements Expression{
    private Expression e1;
    private Expression e2;
    private String operator;
    public RelationalExpression(Expression e1, Expression e2, String operator){
        this.e1= e1;
        this.e2 = e2;
        this.operator = operator;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        Value v1, v2;
        v1 = e1.evaluate(symTable, heap);
        if(v1.getType().equals(new IntType())){
            v2 = e2.evaluate(symTable, heap);
            if (v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int value1, value2;
                value1 = i1.getValue();
                value2 = i2.getValue();
                if(Objects.equals(this.operator, "<")){
                    return new BoolValue(value1<value2);
                }
                else if(Objects.equals(this.operator, ">")){
                    return new BoolValue(value1>value2);}
                else if(Objects.equals(this.operator, "==")){
                    return new BoolValue(value1==value2);
                }else if(Objects.equals(this.operator, "<=")){
                    return new BoolValue(value1<=value2);}
                else if(Objects.equals(this.operator, ">=")){
                    return new BoolValue(value1>=value2);}
                else if(Objects.equals(this.operator, "!=")){
                    return new BoolValue(value1!=value2);}
            }else throw new MyException("Second operand is not an integer!");
        }else throw new MyException("First operand is not an integer!");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand is not an integer.");
    }

    public Expression deepCopy(){
        return new RelationalExpression(e1.deepCopy(), e2.deepCopy(), operator);
    }
    public String toString(){
        return e1.toString() + " " + operator + " " + e2.toString();
    }
}
