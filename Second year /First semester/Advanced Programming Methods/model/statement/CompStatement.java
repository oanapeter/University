package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.stack.MyIStack;
import model.programState.ProgramState;
import model.type.Type;

public class CompStatement implements IStatement{
    IStatement first;
    IStatement second;
    public CompStatement(IStatement first, IStatement second)
    {
        this.first = first;
        this.second = second;
    }
    public ProgramState execute(ProgramState state)
    {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return null;
    }
    public String toString(){
        return String.format("(%s|%s)", first.toString(), second.toString());
    }
    public IStatement deepCopy(){
        return new CompStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
