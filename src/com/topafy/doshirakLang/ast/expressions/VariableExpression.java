package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.Value;
import com.topafy.doshirakLang.lib.Variables;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name){
        this.name = name;
    }

    @Override
    public Value eval(){
        if(!Variables.isExists(name)) throw new RuntimeException("Constant doesn't exists");
        return Variables.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
