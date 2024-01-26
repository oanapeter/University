package model.ADT.stack;

import exception.MyException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;
    public MyStack()
    {
        stack = new Stack<T>();
    }
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    public void push(T elem)
    {
        stack.push(elem);
    }
    public T pop() throws MyException{
        if(stack.isEmpty())
        {
            throw new MyException("The stack is empty!");
        }
        return stack.pop();
    }
    public List<T> getReverse()
    {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
    public String toString()
    {
        return stack.toString();
    }

}
