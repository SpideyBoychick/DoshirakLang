package com.topafy.doshirakLang.lib.variables;

public class DoubleValuePair implements TypeValuePair {

    private double value;

    public DoubleValuePair(Object value) {
        this.value = (double)value;
    }

    @Override
    public VariableType getType() {
        return VariableType.DOUBLE;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object val){
        value = (double)val;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
