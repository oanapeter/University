package model.type;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type{
    public boolean equals(Type type ){
        return type instanceof StringType;
    }
    public Value defaultValue(){
        return new StringValue("");
    }
    public Type deepCopy(){
        return new StringType();
    }
    public String toString(){
        return "string";
    }
}
