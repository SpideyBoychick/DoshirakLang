package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.variables.*;

public class UnaryExpression implements Expression {
    private final Expression expr;
    private final char operation;

    public UnaryExpression(char operation, Expression expr) {
        this.expr = expr;
        this.operation = operation;
    }

    @Override
    public TypeValuePair eval(){
        final TypeValuePair res = expr.eval();
        switch (res.getType()){
            case VariableType.INT:
                return switch (operation) {
                    case '+' -> res;
                    case '-' -> new IntValuePair(-(int) res.getValue());
                    default -> throw new IllegalArgumentException("Wrong operation" + operation);
                };

            case VariableType.DOUBLE:
                return switch (operation){
                    case '+' -> res;
                    case '-' -> new DoubleValuePair(-(double)res.getValue());
                    default -> throw new IllegalArgumentException("Wrong operation" + operation);
                };

            case VariableType.STRING:
                return switch (operation){
                    case '+' -> res;
                    case '-' -> reverseString((String)res.getValue());
                    default -> throw new IllegalArgumentException("Wrong operation" + operation);
                };

            case VariableType.BOOL:
                return switch (operation){
                    case '!' -> new BooleanValuePair(!(boolean)res.getValue());
                    default -> throw new IllegalArgumentException("Wrong operation" + operation);
                };
        }
        throw new RuntimeException("Unknown type of variable");
    }

    private StringValuePair reverseString(String input){
        StringBuilder sb = new StringBuilder(input);
        sb.reverse();
        return new StringValuePair(sb.toString());
    }

    @Override
    public String toString() {
        return String.format("%c%s", operation, expr);
    }
}
