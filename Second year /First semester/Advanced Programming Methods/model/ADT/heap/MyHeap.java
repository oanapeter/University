package model.ADT.heap;

import exception.MyException;
import model.value.Value;

import java.util.HashMap;
import java.util.Set;

public class MyHeap implements MyIHeap{
    HashMap<Integer, Value> heap;
    Integer freeLocationValue;
    public MyHeap(){
        this.heap = new HashMap<>();
        freeLocationValue = 1;
    }
    public int newValue(){
        freeLocationValue += 1;
        while(freeLocationValue == 0 || heap.containsKey(freeLocationValue))
            freeLocationValue += 1;
        return freeLocationValue;
    }

    public int getFreeValue(){
        return freeLocationValue;
    }
    public HashMap<Integer, Value> getContent(){
        return heap;
    }

    public void setContent(HashMap<Integer, Value> newMap){
        this.heap = newMap;
    }
    public int add(Value value){
        heap.put(freeLocationValue, value);
        Integer toReturn = freeLocationValue;
        freeLocationValue = newValue();
        return toReturn;
    }

    public void update(Integer position, Value value) throws MyException{
        if(!heap.containsKey(position))
            throw new MyException(String.format("%d is not in the heap.", position));
        heap.put(position, value);
    }
    public Value get(Integer position) throws  MyException{
        if(!heap.containsKey(position))
            throw new MyException(String.format("%d is not in the heap.", position));
        return heap.get(position);
    }
    public boolean containsKey(Integer position){
        return this.heap.containsKey(position);
    }

    public void remove(Integer key) throws MyException{
        if(!containsKey(key)){
            throw new MyException(key + "is not defined.");
        }
        freeLocationValue = key;
        this.heap.remove(key);
    }
    public Set<Integer> keySet(){
        return heap.keySet();
    }
    public String toString(){
        return this.heap.toString();
    }
}
