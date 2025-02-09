package com.topafy.doshirakLang.lib;

public class DoubleValue implements Value{

    private final double doubleValue;

    public DoubleValue(double doubleValue){
        this.doubleValue = doubleValue;
    }

    @Override
    public double asDouble() {
        return doubleValue;
    }

    @Override
    public int asInt() {
        return (int)doubleValue;
    }

    @Override
    public String asString() {
        return Double.toString(doubleValue);
    }

    @Override
    public String toString() {
        return asString();
    }
}
