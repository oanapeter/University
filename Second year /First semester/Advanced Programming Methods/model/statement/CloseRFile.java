package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement{
    Expression expression;
    public CloseRFile(Expression expression)
    {
        this.expression = expression;
    }
    public ProgramState execute (ProgramState state) throws MyException{
        Value value = expression.evaluate(state.getSymTable(), state.getHeap());
        if(value.getType().equals(new StringType())){
            StringValue fileName = (StringValue) value;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if(fileTable.isDefined(fileName.getValue())){
                BufferedReader reader = fileTable.lookUp(fileName.getValue());
                try{
                    reader.close();
                    fileTable.remove(fileName.getValue());
                    state.setFileTable(fileTable);
                }catch (IOException exception){
                    throw new MyException(String.format("Could not close file %s", fileName));
                }

            }else
                throw new MyException(String.format("The file table does not contain %s", fileName));
        }else
            throw new MyException(String.format("%s does not evaluate to StringType", value));
        return null;
    }
    public IStatement deepCopy(){
        return new CloseRFile(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("CloseReadFile requires a string expression.");
    }

    public String toString(){
        return String.format("CloseRFile(%s)",expression.toString());
    }
}
