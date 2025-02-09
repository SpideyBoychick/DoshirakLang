package com.topafy.doshirakLang.lib;

public class IntValue implements Value{
    private final int intValue;

    public IntValue(int intValue){
        this.intValue = intValue;
    }

    @Override
    public double asDouble() {
        return intValue;
    }

    @Override
    public int asInt() {
        return intValue;
    }

    @Override
    public String asString() {
        return Integer.toString(intValue);
    }

    @Override
    public String toString() {
        return asString();
    }
}
