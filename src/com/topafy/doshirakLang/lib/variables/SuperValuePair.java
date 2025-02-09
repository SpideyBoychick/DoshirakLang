package com.topafy.doshirakLang.lib.variables;

public class SuperValuePair implements TypeValuePair {

    private final VariableType type;
    private Object value;

    public SuperValuePair(VariableType type, Object value){
        this.type = type;
        this.value = value;
    }

    @Override
    public VariableType getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object val) {
        value = val;
    }

    @Override
    public String toString() {
        return type + " " + value;
    }
}
