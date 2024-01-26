package model.ADT.stack;

import exception.MyException;

import java.util.List;

public interface MyIStack<T> {
    boolean isEmpty();
    void push(T elem);
    T pop() throws MyException;
    List<T> getReverse();
}
