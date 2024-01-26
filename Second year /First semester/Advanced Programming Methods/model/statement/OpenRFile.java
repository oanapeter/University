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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement{
    Expression expression;
    public OpenRFile(Expression expression){
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        Value result = expression.evaluate(state.getSymTable(), state.getHeap());
        if(result.getType().equals(new StringType())){
            StringValue fileName = (StringValue) result;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if(!fileTable.isDefined(fileName.getValue())){
                BufferedReader reader;
                try{
                    reader = new BufferedReader(new FileReader(fileName.getValue()));
                }catch(FileNotFoundException exception){
                    throw new MyException(String.format("%s could not be opened", fileName.getValue()));
                }fileTable.put(fileName.getValue(), reader);
                state.setFileTable(fileTable);
            }else{
                throw new MyException(String.format("%s is already opened", fileName.getValue()));

            }
        }else{
            throw new MyException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
        return null;
    }
    public IStatement deepCopy(){
        return new OpenRFile(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }
        else throw new MyException("OpenReadFile requires a string expression.");

    }

    public String toString()
    {
        return String.format("OpenReadFile(%s)", expression.toString());
    }
}
