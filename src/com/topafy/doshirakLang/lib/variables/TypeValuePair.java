package com.topafy.doshirakLang.lib.variables;

public interface TypeValuePair {
    VariableType getType();
    Object getValue();
    void setValue(Object val);
}
