package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.*;

public class ConditionExpression implements Expression {

    private final Expression expr1, expr2;
    private final String operation;

    public ConditionExpression(String operation, Expression expr1, Expression expr2) {
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
                case "==":
                    return new BooleanValuePair(string1.equals(value2.getValue().toString()));
                case "!=":
                    return new BooleanValuePair(!string1.equals(value2.getValue().toString()));
                default:
                    throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        else if(value1.getType() == VariableType.INT && value2.getType() == VariableType.INT){
            final int inumber1 = (int)value1.getValue();
            final int inumber2 = (int)value2.getValue();
            switch (operation){
                case "==": return new BooleanValuePair(inumber1 == inumber2);
                case "!=": return new BooleanValuePair(inumber1 != inumber2);
                case "<": return new BooleanValuePair(inumber1 < inumber2);
                case "<=": return new BooleanValuePair(inumber1 <= inumber2);
                case ">": return new BooleanValuePair(inumber1 > inumber2);
                case ">=": return new BooleanValuePair(inumber1 >= inumber2);
                case "||": return new BooleanValuePair(intToBool(inumber1) || intToBool(inumber2));
                case "&&": return new BooleanValuePair(intToBool(inumber1) && intToBool(inumber2));

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        else if(value1.getType() == VariableType.DOUBLE || value2.getType() == VariableType.DOUBLE) {
            final double dnumber1 = Double.parseDouble(value1.getValue().toString());
            final double dnumber2 = Double.parseDouble(value2.getValue().toString());
            switch (operation){
                case "==": return new BooleanValuePair(dnumber1 == dnumber2);
                case "!=": return new BooleanValuePair(dnumber1 != dnumber2);
                case "<": return new BooleanValuePair(dnumber1 < dnumber2);
                case "<=": return new BooleanValuePair(dnumber1 <= dnumber2);
                case ">": return new BooleanValuePair(dnumber1 > dnumber2);
                case ">=": return new BooleanValuePair(dnumber1 >= dnumber2);
                case "||": return new BooleanValuePair(doubleToBool(dnumber1) || doubleToBool(dnumber2));
                case "&&": return new BooleanValuePair(doubleToBool(dnumber1) && doubleToBool(dnumber2));

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        else if(value1.getType() == VariableType.BOOL || value2.getType() == VariableType.BOOL){
            final boolean bool1 = intToBool(Integer.parseInt(value1.getValue().toString()));
            final boolean bool2 = intToBool(Integer.parseInt(value2.getValue().toString()));
            switch (operation){
                case "||": return new BooleanValuePair(bool1 || bool2);
                case "&&": return new BooleanValuePair(bool1 && bool2);

                default: throw new IllegalArgumentException("Wrong operation" + operation);
            }
        }
        throw new RuntimeException(value1.getType() + " not comparable with " + value2.getType());
    }

    private boolean intToBool(int value){
        return value != 0;
    }
    private boolean doubleToBool(double value){
        return value != 0;
    }


    @Override
    public String toString() {
        return String.format("(%s%c%s)", expr1, operation, expr2);
    }
}