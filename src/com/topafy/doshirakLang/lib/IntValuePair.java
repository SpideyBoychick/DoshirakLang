package com.topafy.doshirakLang.lib;

public class IntValuePair implements TypeValuePair{

    private int value;

    public IntValuePair(Object value) {
        this.value = (int)value;
    }

    @Override
    public VariableType getType() {
        return VariableType.INT;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object val){
        value = (int)val;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
