package model.type;

import model.value.BoolValue;
import model.value.Value;

public class BoolType implements Type{

    public boolean equals(Type type) {
        return type instanceof BoolType;
    }
    public Value defaultValue()
    {
        return new BoolValue(false);
    }
    public Type deepCopy(){
        return new BoolType();
    }
    public String toString()
    {
        return "bool";
    }
}
