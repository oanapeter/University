package model.statement;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.expression.Expression;
import model.programState.ProgramState;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStatement implements IStatement{
    private String varName;
    private Expression expression;
    public WriteHeapStatement(String varName, Expression expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symTable.isDefined(varName))
            throw new MyException(String.format("%s is not in the Symbol Table", varName));
        Value value = symTable.lookUp(varName);
        if(!(value instanceof RefValue))
            throw new MyException(String.format("%s is not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.evaluate(symTable, heap);
        if(!evaluated.getType().equals(refValue.getLocationType()))
            throw new MyException(String.format("%s is not of %s", evaluated, refValue));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(typeEnv.lookUp(varName).equals(new RefType(expression.typeCheck(typeEnv))))
            return typeEnv;
        else throw new MyException("Types are not compatible.");
    }

    public  String toString(){
        return String.format("WriteHeap(%s, %s)", varName, expression);
    }
}
