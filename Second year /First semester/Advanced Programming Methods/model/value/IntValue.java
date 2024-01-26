package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue implements Value{
    private int value;
    public IntValue(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return this.value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
    public Type getType()
    {
        return new IntType();
    }

    public boolean equals(Value value)
    {
        if(!(value instanceof IntValue))
            return false;
        IntValue castValue = (IntValue) value;
        return this.value == castValue.value;
    }
    public String toString()
    {
        return String.format("%d", this.value);
    }

    public Value deepCopy()
    {
        return new IntValue(value);
    }
}
