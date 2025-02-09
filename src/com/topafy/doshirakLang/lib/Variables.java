package com.topafy.doshirakLang.lib;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private static final IntValue zeroValue = new IntValue(0);
    private static final Map<String, Value> variables;

    static {
        variables = new HashMap<>();
        variables.put("PI", new DoubleValue(Math.PI));
        variables.put("PI_2", new DoubleValue(Math.PI / 2));
        variables.put("PI_4", new DoubleValue(Math.PI / 4));
        variables.put("E", new DoubleValue(Math.E));
    }

    public static boolean isExists(String key){
        return variables.containsKey(key);
    }

    public static Value get(String key){
        if(!isExists(key)) return zeroValue;
        return variables.get(key);
    }

    public static void set(String key, Value value){
        variables.put(key, value);
    }

}
