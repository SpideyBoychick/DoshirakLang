package com.topafy.doshirakLang.lib;

public interface TypeValuePair {
    VariableType getType();
    Object getValue();
    void setValue(Object val);
}
