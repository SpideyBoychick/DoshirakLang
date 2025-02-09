package com.topafy.doshirakLang.lib;

public class StringValue implements Value{

    private final String stringValue;

    public StringValue(String stringValue){
        this.stringValue = stringValue;
    }

    @Override
    public double asDouble() {
        try{
            return Double.parseDouble(stringValue);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    @Override
    public int asInt() {
        try{
            return Integer.parseInt(stringValue);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    @Override
    public String asString() {
        return stringValue;
    }

    @Override
    public String toString() {
        return asString();
    }
}
