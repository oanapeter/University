package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.stack.MyIStack;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class IfStatement implements  IStatement{
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;
    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement)
    {
        this.expression =expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        Value result = this.expression.evaluate(state.getSymTable(), state.getHeap());
        if(result instanceof BoolValue boolResult){
            IStatement statement;
            if(boolResult.getValue()){
                statement = thenStatement;
            }
            else{
                statement = elseStatement;
            }
            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return null;

        }else{
            throw new MyException("You have to provide a boolean expression in an if statement!");
        }
    }
    public String toString()
    {
        return "IF ("+expression.toString()+ ") THEN ("+ thenStatement.toString()+") ELSE ("+ elseStatement.toString()+"))";
    }
    public IStatement deepCopy(){
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }else throw new MyException("The if condition does not evaluate to Bool Type");

    }
}
