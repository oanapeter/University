package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.Type;
import model.value.Value;

public class ValueExpression implements Expression{
    private Value expression;
    public ValueExpression(Value expression)
    {
        this.expression = expression;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap)
    {
        return expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return expression.getType();
    }

    public String toString()
    {
        return this.expression.toString();
    }
    public Expression deepCopy(){
        return new ValueExpression(expression.deepCopy());
    }

}
