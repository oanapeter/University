package model.value;

import model.type.StringType;
import model.type.Type;

public class StringValue implements Value{
    private String value;
    public StringValue(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }
    public Type getType(){
        return new StringType();

    }
    public Value deepCopy()
    {
        return new StringValue(value);
    }
    public boolean equals(Value value){
        if(!(value instanceof StringValue))
            return false;
        StringValue castValue = (StringValue) value;
        return this.value.equals(castValue.value);
    }
    public String toString(){
        return "\"" + this.value + "\"";
    }
}
