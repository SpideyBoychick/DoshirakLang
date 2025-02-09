package com.topafy.doshirakLang.lib;

public class StringValuePair implements TypeValuePair{
    private String value;

    public StringValuePair(Object value) {
        this.value = (String)value;
    }

    @Override
    public VariableType getType() {
        return VariableType.STRING;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object val){
        value = (String)val;
    }

    @Override
    public String toString() {
        return value;
    }
}
