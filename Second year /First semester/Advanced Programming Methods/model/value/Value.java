package model.value;

import model.type.Type;

public interface Value {
    Type getType();
    //boolean equals(Value value);
    Value deepCopy();

}
