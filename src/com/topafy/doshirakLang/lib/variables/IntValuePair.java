package com.topafy.doshirakLang.lib.variables;

public class IntValuePair implements TypeValuePair {

    private int value;

    public IntValuePair(Object value) {
        if (value instanceof Boolean) {
            this.value = (Boolean)value == false ? 0 : 1;
        } else {
            this.value = (int) value;
        }
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
