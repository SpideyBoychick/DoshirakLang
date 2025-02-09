package com.topafy.doshirakLang.lib.variables;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private static final VoidValue voidVal = new VoidValue();
    private static final TypeValuePair zeroValue = new IntValuePair(0);
    private static final Map<String, TypeValuePair> variables;

    static {
        variables = new HashMap<>();
        variables.put("PI", new DoubleValuePair(Math.PI));
        variables.put("PI_2", new DoubleValuePair(Math.PI / 2));
        variables.put("PI_4", new DoubleValuePair(Math.PI / 4));
        variables.put("E", new DoubleValuePair(Math.E));

        variables.put("false", new BooleanValuePair(false));
        variables.put("true", new BooleanValuePair(true));
    }

    public static boolean isExists(String key){
        return variables.containsKey(key);
    }

    public static TypeValuePair get(String key){
        if(!isExists(key)) return zeroValue;
        return variables.get(key);
    }

    public static void set(VariableType type, String name, Object value){
        TypeValuePair val = switch (type) {
            case VariableType.INT -> new IntValuePair(value);
            case VariableType.DOUBLE -> new DoubleValuePair(value);
            case VariableType.STRING -> new StringValuePair(value);
            case VariableType.VOID -> voidVal;
            case BOOL -> new BooleanValuePair(value);
        };

        if (!isExists(name)) {
            variables.put(name, val);
        }
        else {
            variables.replace(name, val);
        }
    }

    public static void set(String name, TypeValuePair val) {
        if (!isExists(name)) {
            variables.put(name, val);
        }
        else {
            variables.replace(name, val);
        }
    }


}
