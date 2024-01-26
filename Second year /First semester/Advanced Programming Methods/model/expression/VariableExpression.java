package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.Type;
import model.value.Value;

public class VariableExpression implements Expression{
    private String key;
    public VariableExpression(String key)
    {
        this.key = key;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        return symTable.lookUp(key);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookUp(key);
    }

    public String toString()
    {
        return key;
    }
    public Expression deepCopy(){
        return new VariableExpression(key);
    }
}
