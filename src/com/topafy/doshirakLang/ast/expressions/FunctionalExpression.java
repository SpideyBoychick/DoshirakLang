package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.functions.Functions;
import com.topafy.doshirakLang.lib.variables.SuperValuePair;
import com.topafy.doshirakLang.lib.variables.TypeValuePair;

import java.util.ArrayList;
import java.util.List;

public class FunctionalExpression implements Expression{

    private final String name;
    private final List<Expression> args;

    public FunctionalExpression(String name, List<Expression> args) {
        this.name = name;
        this.args = args;
    }

    public FunctionalExpression(String name) {
        this.name = name;
        args = new ArrayList<>();
    }

    public void addArg(Expression arg){
        args.add(arg);
    }

    @Override
    public TypeValuePair eval() {
        final int size = args.size();
        TypeValuePair[] values = new TypeValuePair[size];
        for(int i = 0; i < size; i++){
            values[i] = new SuperValuePair(args.get(i).eval().getType(), args.get(i).eval().getValue());
        }
        return Functions.get(name).execute(values);
    }

    @Override
    public String toString() {
        return name + "(" + args.toString() + ")";
    }
}
