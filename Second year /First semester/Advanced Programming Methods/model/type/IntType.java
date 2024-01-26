package model.type;

import model.value.IntValue;
import model.value.Value;

public class IntType implements Type{


    public boolean equals(Type type) {
        return type instanceof IntType;
    }


    public Value defaultValue() {
        return new IntValue(0);
    }
    public Type deepCopy(){
        return new IntType();
    }

    public String toString()
    {
        return "int";
    }
}
