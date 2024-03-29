package model.programState;

import exception.MyException;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.ADT.list.MyIList;
import model.ADT.stack.MyIStack;
import model.statement.IStatement;
import model.value.Value;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private IStatement originalProgram;
    private MyIDictionary<String, BufferedReader> fileTable;
    private MyIHeap heap;
    private int id;
    private static int lastId = 0;
    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, IStatement originalProgram)
    {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram.deepCopy();
        this.exeStack.push(originalProgram);
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }
    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String,Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }
    public synchronized int setId(){
        lastId++;
        return lastId;
    }
    public String getId(){
        return Integer.toString(this.id);
    }
    public MyIStack<IStatement> getExeStack()
    {
        return exeStack;
    }
    public void setExeStack(MyIStack<IStatement> exeStack)
    {
        this.exeStack = exeStack;
    }
    public MyIDictionary<String, Value> getSymTable()
    {
        return this.symTable;
    }
    public void setSymTable(MyIDictionary<String, Value> symTable){
        this.symTable = symTable;
    }
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }
    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }
    public MyIList<Value> getOut()
    {
        return this.out;
    }
    public void setOut(MyIList<Value> newOut){
        this.out = newOut;
    }
    public String exeStackToString()
    {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getReverse();
        for(IStatement statement: stack)
        {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws MyException
    {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for(String key: symTable.keySet()){
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString()
    {
        StringBuilder outStringBuilder = new StringBuilder();
        for(Value v: out.getList())
        {
            outStringBuilder.append(String.format("%s\n", v.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString(){
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for(String key : fileTable.keySet()){
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }
    public void setHeap(MyIHeap newHeap){
        this.heap = newHeap;
    }

    public MyIHeap getHeap(){
        return heap;
    }
    public String toString()
    {
        return "ID: " + getId() + "\n"+
                "Execution stack: "+ exeStack.getReverse()+ "\n"+
                "Symbol table: " + symTable.toString() + "\n"+
                "Output list: " + out.toString()+ "\n" +
                "File table: " + fileTable.toString() + "\n" +
                "Heap: " + heap.toString() + "\n";
    }

    public String programStateToString() throws MyException{
        return "ID: " + getId() + "\n" +
                "Execution Stack: \n" + exeStackToString() +
                "Symbol Table: \n" + symTableToString() +
                "Output list: \n" + outToString() +
                "File table: \n" + fileTableToString() +
                "Heap: \n" + heapToString() + "\n";
    }
    public String heapToString() throws MyException{
        StringBuilder heapStringBuilder = new StringBuilder();
        for(int key: heap.keySet()){
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }
    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public ProgramState oneStep() throws MyException{
        if(exeStack.isEmpty()) throw new MyException("Execution stack is empty!");
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

}
