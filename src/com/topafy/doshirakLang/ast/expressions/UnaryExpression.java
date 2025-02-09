package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.ast.expressions.Expression;
import com.topafy.doshirakLang.lib.DoubleValue;
import com.topafy.doshirakLang.lib.Value;

public class UnaryExpression implements Expression {
    private final Expression expr;
    private final char operation;

    public UnaryExpression(char operation, Expression expr) {
        this.expr = expr;
        this.operation = operation;
    }

    @Override
    public Value eval(){
        switch (operation){
            case '+': return expr.eval();
            case '-': return new DoubleValue(-expr.eval().asDouble());

            default: throw new IllegalArgumentException("Wrong operation" + operation);
        }
    }

    @Override
    public String toString() {
        return String.format("%c%s", operation, expr);
    }
}
