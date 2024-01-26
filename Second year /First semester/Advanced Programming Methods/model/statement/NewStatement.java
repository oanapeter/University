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

public class NewStatement implements IStatement{
    private String varName;
    private Expression expression;
    public NewStatement(String varName, Expression expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symTable.isDefined(varName))
            throw new MyException(String.format("%s is not in Symbol Table", varName));
        Value varValue = symTable.lookUp(varName);
        if(!(varValue.getType() instanceof RefType))
            throw new MyException(String.format("%s is not of RefType", varName));
        Value evaluated = expression.evaluate(symTable, heap);
        Type locationType = ((RefValue)varValue).getLocationType();
        if(!locationType.equals(evaluated.getType()))
            throw new MyException(String.format("%s is not of %s", varName, evaluated));
        int newPosition = heap.add(evaluated);
        symTable.put(varName, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
        state.setHeap(heap);
        return null;

    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(varName, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(varName);
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeVar.equals(new RefType(typeExpr))){
            return typeEnv;

        }else throw new MyException("The types are not compatible.");
    }

    public String toString(){
        return String.format("New(%s, %s)", varName, expression);
    }
}
