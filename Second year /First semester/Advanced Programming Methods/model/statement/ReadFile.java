package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    Expression expression;
    String varName;
    public ReadFile(Expression expression, String varName){
        this.expression = expression;
        this.varName = varName;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if( symTable.isDefined(varName)){
            Value value = symTable.lookUp(varName);
            if(value.getType().equals(new IntType())){
                value = expression.evaluate(symTable, state.getHeap());
                if(value.getType().equals(new StringType())){
                    StringValue castValue = (StringValue) value;
                    if(fileTable.isDefined(castValue.getValue())){
                        BufferedReader reader = fileTable.lookUp(castValue.getValue());
                        try{
                            String line = reader.readLine();
                            if(line == null){
                                line = "0";
                            }
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        }catch (IOException exception){
                            throw new MyException(String.format("Could not read from file %s", castValue));
                        }
                    }else{
                        throw new MyException(String.format("The file table does not contain %s", castValue));
                    }
                }else {
                    throw new MyException(String.format("%s does not evaluate to StringType", value));
                }
            }else{
                throw new MyException(String.format("%s is not of type IntType", value));
            }
        }else{
            throw new MyException(String.format("%s is not present in the Symbol Table", varName));
        }
        return null;
    }
    public IStatement deepCopy(){
        return new ReadFile(expression.deepCopy(),varName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new StringType())){
            if(typeEnv.lookUp(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("ReadFile requires an int as its variable parameter.");
        }else throw new MyException("ReadFile requires a string as its expression parameter.");
    }

    public String toString(){
        return String.format("ReadFile(%s, %s)", expression.toString(), varName);
    }
}
