package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.programState.ProgramState;
import model.type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    IStatement deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws  MyException;
}
