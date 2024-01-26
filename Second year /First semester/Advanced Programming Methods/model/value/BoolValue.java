package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements  Value{
    private final boolean value;
    public BoolValue(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return this.value;
    }
    public Type getType()
    {
        return new BoolType();

    }
    public boolean equals(Value value)
    {
        if(!(value instanceof BoolValue))
            return false;
        BoolValue castValue = (BoolValue) value;
        return this.value == castValue.value;
    }
    public String toString()
    {
        return this.value ? "true" : "false";
    }
    public Value deepCopy(){
        return new BoolValue(value);
    }
}
