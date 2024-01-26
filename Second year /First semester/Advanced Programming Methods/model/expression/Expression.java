package model.expression;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.type.Type;
import model.value.Value;

public interface Expression {
    Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException;

    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;

    Expression deepCopy();
}
