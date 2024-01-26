package model.ADT.dictionary;
import exception.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    private Map<K, V> map;
    public MyDictionary()
    {
        map = new HashMap<K, V>();
    }
    public void put(K key, V value){
        map.put(key, value);
    }

    @Override
    public boolean isDefined(K key) {
        return map.containsKey(key);
    }

    public void update(K key, V value) throws MyException{
        if(!isDefined(key)){
            throw new MyException(key + "is not found!");
        }
        map.put(key, value);
    }
    public V lookUp(K key) throws MyException{
        if(!isDefined(key)){
            throw new MyException(key + "is not found!");
        }
        return map.get(key);
    }
    public Set<K> keySet(){
        return map.keySet();
    }

    public void remove(K val) throws MyException{
        if(!isDefined(val)){
            throw new MyException(val + "is not defined!");
        }
        map.remove(val);

    }
    public String toString()
    {
        return map.toString();
    }

    public Map<K, V> getContent(){
        return map;
    }
    public MyIDictionary<K, V> deepCopy() throws  MyException{
        MyIDictionary<K, V> toReturn = new MyDictionary<>();
        for(K key: keySet()){
            toReturn.put(key, lookUp(key));
        }
        return toReturn;
    }
    public Collection<V> values() {
        return this.map.values();
    }
}
