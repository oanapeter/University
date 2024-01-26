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

public class WhileStatement implements IStatement {
    private Expression expression;
    private IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.evaluate(state.getSymTable(), state.getHeap());
        MyIStack<IStatement> stack = state.getExeStack();
        if (!value.getType().equals(new BoolType())) {
            throw new MyException(String.format("%s is not of BoolType", value));

        }
        BoolValue boolValue = (BoolValue) value;
        if (boolValue.getValue()) {
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The while condition does not evaluate to Bool Type.");
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
}