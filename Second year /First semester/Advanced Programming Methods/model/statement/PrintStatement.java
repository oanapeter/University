package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.list.MyIList;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.Type;
import model.value.Value;

public class PrintStatement implements  IStatement{
    Expression expression;
    public PrintStatement(Expression expression)
    {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        MyIList<Value> output = state.getOut();
        output.add(expression.evaluate(state.getSymTable(), state.getHeap()));
        state.setOut(output);
        return null;
    }
    public String toString()
    {
        return String.format("Print(%s)", expression.toString());
    }
    public IStatement deepCopy(){
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
