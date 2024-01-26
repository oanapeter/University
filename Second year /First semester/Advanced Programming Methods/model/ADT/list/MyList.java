package model.ADT.list;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> out;
    public MyList()
    {
        out = new ArrayList<>();
    }
    public void add(T elem)
    {
        out.add(elem);
    }
    public List<T> getList()
    {
        return out;
    }
    public String toString()
    {
        return out.toString();
    }

}
