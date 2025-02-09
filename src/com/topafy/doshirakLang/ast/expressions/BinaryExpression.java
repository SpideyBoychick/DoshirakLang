package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.*;

import java.awt.*;

public class BinaryExpression implements Expression {

    private final Expression expr1, expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public TypeValuePair eval(){

        final TypeValuePair value1 = expr1.eval();
        final TypeValuePair value2 = expr2.eval();

        if(value1.getType() == VariableType.STRING || value2.getType() == VariableType.STRING){
            final String string1 = value1.getValue().toString();
            switch (operation) {
                case '+':
                    return new StringValuePair(string1 + value2.getValue().toString());
                case '*': {
                    if (value2 instanceof IntValuePair) {
                        final int iterations = (int) value2.getValue();
                        final StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < iterations; i++)
                            sb.append(string1);
                        return new StringValuePair(sb.toString());
                    } else throw new RuntimeException("Wrong iterations");
                }
                default:
                    throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        else if(value1.getType() == VariableType.INT && value2.getType() == VariableType.INT){
            final int inumber1 = (int)value1.getValue();
            final int inumber2 = (int)value2.getValue();
            switch (operation){
                case '+': return new IntValuePair(inumber1 + inumber2);
                case '-': return new IntValuePair(inumber1 - inumber2);
                case '*': return new IntValuePair(inumber1 * inumber2);
                case '/': return new IntValuePair(inumber1 / inumber2);

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        else if(value1.getType() == VariableType.DOUBLE || value2.getType() == VariableType.DOUBLE) {
            final double dnumber1 = Double.parseDouble(value1.getValue().toString());
            final double dnumber2 = Double.parseDouble(value2.getValue().toString());
            switch (operation){
                case '+': return new DoubleValuePair(dnumber1 + dnumber2);
                case '-': return new DoubleValuePair(dnumber1 - dnumber2);
                case '*': return new DoubleValuePair(dnumber1 * dnumber2);
                case '/': return new DoubleValuePair(dnumber1 / dnumber2);

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        throw new RuntimeException(value1.getType() + " not comparable with " + value2.getType());
    }

    @Override
    public String toString() {
        return String.format("(%s%c%s)", expr1, operation, expr2);
    }
}