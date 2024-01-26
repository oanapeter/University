package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.stack.MyIStack;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.Type;
import model.value.Value;

public class AssignStatement implements IStatement{
    String key;
    Expression expression;
    public AssignStatement(String key, Expression expression){
        this.key = key;
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(key)){
            Value value = expression.evaluate(symTable, state.getHeap());
            Type type = (symTable.lookUp(key)).getType();
            if(value.getType().equals(type)){
                symTable.update(key,value);

            }else throw new MyException("Type of "+ key + " and type of the assigned expression do not match!");
        }else throw new MyException("The variable "+ key + " is not declared!");
        state.setSymTable(symTable);
        return null;
    }
    public String toString()
    {
        return key + " = "+ expression.toString();
    }
    public IStatement deepCopy(){
        return new AssignStatement(key, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(key);
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeVar.equals(typeExpr)){
            return typeEnv;
        }
        else{
            throw new MyException("Types are not compatible.");
        }
    }

}
