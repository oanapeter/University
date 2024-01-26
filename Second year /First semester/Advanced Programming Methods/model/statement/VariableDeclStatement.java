package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.programState.ProgramState;
import model.type.Type;
import model.value.Value;

public class VariableDeclStatement implements IStatement {
    String name;
    Type type;
    public VariableDeclStatement(String name, Type type){
        this.name = name;
        this.type = type;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(name))
        {
            throw new MyException("Variable "+ name + " is already declared!");
        }else{
            symTable.put(name, type.defaultValue());
            state.setSymTable(symTable);
            return null;

        }
    }
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }
    public IStatement deepCopy(){
        return new VariableDeclStatement(name, type.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(name, type);
        return typeEnv;
    }
}
