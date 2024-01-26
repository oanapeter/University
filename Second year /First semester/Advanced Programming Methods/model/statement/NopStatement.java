package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.programState.ProgramState;
import model.type.Type;

public class NopStatement implements IStatement{
    public ProgramState execute(ProgramState state)
    {
        return null;
    }
    public IStatement deepCopy(){
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

}
