package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.DoubleValuePair;
import com.topafy.doshirakLang.lib.IntValuePair;
import com.topafy.doshirakLang.lib.StringValuePair;
import com.topafy.doshirakLang.lib.TypeValuePair;

public class ValueExpression implements Expression {

    private final TypeValuePair value;

    public ValueExpression(int value){
        this.value = new IntValuePair(value);
    }

    public ValueExpression(double value){
        this.value = new DoubleValuePair(value);
    }

    public ValueExpression(String value){
        this.value = new StringValuePair(value);
    }

    @Override
    public TypeValuePair eval(){
        return value;
    }

    @Override
    public String toString() {
        return value.getValue().toString();
    }
}
