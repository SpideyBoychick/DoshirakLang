package com.topafy.doshirakLang.lib;

public class BooleanValuePair implements TypeValuePair{

    private boolean value;

    public BooleanValuePair(Object value) {
        this.value = (boolean)value;
    }

    @Override
    public VariableType getType() {
        return VariableType.BOOL;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object val){
        value = (boolean)val;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
