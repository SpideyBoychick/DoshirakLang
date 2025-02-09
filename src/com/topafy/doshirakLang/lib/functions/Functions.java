package com.topafy.doshirakLang.lib.functions;

import com.topafy.doshirakLang.lib.functions.Function;
import com.topafy.doshirakLang.lib.variables.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Functions {

    private static final VoidValue voidVal = new VoidValue();
    private static final Map<String, Function> functions;
    private static final Scanner scanner = new Scanner(System.in);

    static {
        functions = new HashMap<>();
        functions.put("sin", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                if(args.length != 1)
                    throw new RuntimeException("1 argument expected");
                if(args[0].getType() == VariableType.INT)
                    return new DoubleValuePair(Math.sin((int)args[0].getValue()));
                else if(args[0].getType() == VariableType.DOUBLE)
                    return new DoubleValuePair(Math.sin((double)args[0].getValue()));
                else
                    throw new RuntimeException("sin expected int or double");
            }
        });
        functions.put("cos", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                if(args.length != 1)
                    throw new RuntimeException("1 argument expected");
                if(args[0].getType() == VariableType.INT)
                    return new DoubleValuePair(Math.cos((int)args[0].getValue()));
                else if(args[0].getType() == VariableType.DOUBLE)
                    return new DoubleValuePair(Math.cos((double)args[0].getValue()));
                else
                    throw new RuntimeException("cos expected int or double");
            }
        });
        functions.put("tg", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                if(args.length != 1)
                    throw new RuntimeException("1 argument expected");
                if(args[0].getType() == VariableType.INT)
                    return new DoubleValuePair(Math.tan((int)args[0].getValue()));
                else if(args[0].getType() == VariableType.DOUBLE)
                    return new DoubleValuePair(Math.tan((double)args[0].getValue()));
                else
                    throw new RuntimeException("tg expected int or double");
            }
        });
        functions.put("ctg", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                if(args.length != 1)
                    throw new RuntimeException("1 argument expected");
                if(args[0].getType() == VariableType.INT)
                    return new DoubleValuePair(Math.sin((int)args[0].getValue()) / Math.cos((int)args[0].getValue()));
                else if(args[0].getType() == VariableType.DOUBLE)
                    return new DoubleValuePair(Math.sin((double)args[0].getValue()) / Math.cos((double)args[0].getValue()));
                else
                    throw new RuntimeException("ctg expected int or double");
            }
        });
        functions.put("abs", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                if(args.length != 1)
                    throw new RuntimeException("1 argument expected");
                if(args[0].getType() == VariableType.INT)
                    return new DoubleValuePair(Math.abs((int)args[0].getValue()));
                else if(args[0].getType() == VariableType.DOUBLE)
                    return new DoubleValuePair(Math.abs((double)args[0].getValue()));
                else
                    throw new RuntimeException("abs expected int or double");
            }
        });
        functions.put("println", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                for(TypeValuePair arg : args) {
                    System.out.println(arg.getValue().toString());
                }
                return voidVal;
            }
        });
        functions.put("print", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                for(TypeValuePair arg : args) {
                    System.out.print(arg.getValue().toString());
                }
                return voidVal;
            }
        });
        functions.put("read", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                return new StringValuePair(scanner.next());
            }
        });
        functions.put("readInt", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                return new IntValuePair(scanner.nextInt());
            }
        });
        functions.put("readDouble", new Function() {
            @Override
            public TypeValuePair execute(TypeValuePair... args) {
                return new DoubleValuePair(scanner.nextDouble());
            }
        });
    }

    public static boolean isExists(String key){
        return functions.containsKey(key);
    }

    public static Function get(String key){
        if(!isExists(key)) throw new RuntimeException("Unknown function " + key);
        return functions.get(key);
    }

    public static void set(String name, Function value){
        functions.put(name, value);
    }
}
