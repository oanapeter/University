package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyDictionary;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.stack.MyIStack;
import model.ADT.stack.MyStack;
import model.programState.ProgramState;
import model.type.Type;
import model.value.Value;

import java.util.Map;

public class ForkStatement implements  IStatement{
    private IStatement statement;
    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws MyException{
        MyIStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        MyIDictionary<String, Value> newSymbolTable = new MyDictionary<>();
        for(Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()){
            newSymbolTable.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return new ProgramState(newStack, newSymbolTable, state.getOut(), state.getFileTable(), state.getHeap());
    }
    public IStatement deepCopy(){
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }
    public String toString() {
        return String.format("Fork(%s)", statement.toString());}

}
