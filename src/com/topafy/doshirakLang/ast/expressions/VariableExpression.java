package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.TypeValuePair;
import com.topafy.doshirakLang.lib.Variables;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name){
        this.name = name;
    }

    @Override
    public TypeValuePair eval(){
        if(!Variables.isExists(name)) throw new RuntimeException("Constant doesn't exists");
        return Variables.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
