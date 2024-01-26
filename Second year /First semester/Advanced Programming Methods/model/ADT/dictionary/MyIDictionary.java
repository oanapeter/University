package model.ADT.dictionary;

import exception.MyException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void put(K key, V value);
    boolean isDefined(K key);
    void update(K key, V value) throws MyException;
    V lookUp(K key) throws MyException;
    Set<K> keySet();
    void remove(K val) throws MyException;

    Map<K, V> getContent();
    Collection<V> values();
    MyIDictionary<K, V> deepCopy() throws  MyException;
}
