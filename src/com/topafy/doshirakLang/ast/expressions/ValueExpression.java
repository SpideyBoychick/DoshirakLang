package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.DoubleValue;
import com.topafy.doshirakLang.lib.StringValue;
import com.topafy.doshirakLang.lib.Value;

public class ValueExpression implements Expression {

    private final Value value;

    public ValueExpression(double value){
        this.value = new DoubleValue(value);
    }

    public ValueExpression(String value){
        this.value = new StringValue(value);
    }

    @Override
    public Value eval(){
        return value;
    }

    @Override
    public String toString() {
        return value.asString();
    }
}
