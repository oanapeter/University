package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

import java.util.Objects;

public class LogicExpression implements  Expression{
    private Expression e1;
    private Expression e2;
    private String operator;
    public LogicExpression(Expression e1, Expression e2, String operator)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        Value v1, v2;
        v1=e1.evaluate(symTable, heap);
        if(v1.getType().equals(new BoolType()))
        {
            v2=e2.evaluate(symTable, heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean a1,a2;
                a1 = b1.getValue();
                a2 = b2.getValue();
                if(Objects.equals(this.operator, "and"))
                {
                    return new BoolValue(a1 && a2);
                }
                if(Objects.equals(this.operator, "or"))
                {
                    return new BoolValue(a1 || a2);
                }
            }else throw new MyException("Second operand is not a boolean type!");
        }else throw new MyException("First operand is not a boolean type!");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1;
        Type type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }
            else throw new MyException("Second operand is not boolean.");
        }else throw new MyException("First operand is not boolean.");

    }

    @Override
    public String toString() {
        return e1.toString() + " " + operator + " " + e2.toString() + "\n";
    }

    public Expression deepCopy(){
        return new LogicExpression(e1.deepCopy(), e2.deepCopy(), operator);
    }
}
