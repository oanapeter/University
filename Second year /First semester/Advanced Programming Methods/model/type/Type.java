package model.type;

import model.value.Value;

public interface Type {
    boolean equals(Type type);
    Value defaultValue();
    Type deepCopy();

}
