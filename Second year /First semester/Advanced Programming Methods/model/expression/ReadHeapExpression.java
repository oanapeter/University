package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements Expression{

    private Expression expression;
    public ReadHeapExpression(Expression expression){
        this.expression = expression;
    }
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        Value value = expression.evaluate(symTable, heap);
        if(!(value instanceof RefValue))
            throw new MyException(String.format("%s is not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }
    public Expression deepCopy(){
        return new ReadHeapExpression(expression.deepCopy());

    }
    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if(type instanceof RefType)
        {
            RefType refType = (RefType) type;
            return refType.getInner();
        }else throw new MyException("The rH argument is not of RefType.");
    }
    public String toString(){
        return String.format("ReadHeap(%s", expression);
    }
}
