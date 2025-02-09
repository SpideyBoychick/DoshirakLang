package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.DoubleValue;
import com.topafy.doshirakLang.lib.StringValue;
import com.topafy.doshirakLang.lib.Value;

public class BinaryExpression implements Expression {

    private final Expression expr1, expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval(){

        final Value value1 = expr1.eval();
        final Value value2 = expr2.eval();

        if(value1 instanceof StringValue){
            final String string1 = value1.asString();
            switch (operation){
                case '+': return new StringValue(string1 + value2.asString());
                case '*':
                {
                    final int iterations = value2.asInt();
                    final StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < iterations; i++){
                        sb.append(string1);
                    }
                    return new StringValue(sb.toString());
                }

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }


        final double number1 = value1.asDouble();
        final double number2 = value2.asDouble();
        switch (operation){
            case '+': return new DoubleValue(number1 + number2);
            case '-': return new DoubleValue(number1 - number2);
            case '*': return new DoubleValue(number1 * number2);
            case '/': return new DoubleValue(number1 / number2);

            default: throw new IllegalArgumentException("Wrong operation" + operation);
        }
    }

    @Override
    public String toString() {
        return String.format("(%s%c%s)", expr1, operation, expr2);
    }
}
