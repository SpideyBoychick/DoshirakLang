package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;
import com.topafy.doshirakLang.lib.variables.BooleanValuePair;

public class IfStatement implements Statement{

    private final Expression expression;
    private final Statement ifStatement, elseStatement;

    public IfStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void execute() {
        final boolean result;

        if(!(expression.eval() instanceof BooleanValuePair)) {
            result = intToBool((int) expression.eval().getValue());
        }
        else{
            result = (boolean)expression.eval().getValue();
        }

        if(result == true){
            ifStatement.execute();
        }
        else if(elseStatement != null){
            elseStatement.execute();
        }
    }

    private boolean intToBool(int value){
        return value != 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("if(").append(expression).append("){\n");
        sb.append(ifStatement).append("\n");
        sb.append("}\n");

        if(elseStatement != null){
            sb.append("else{\n");
            sb.append(elseStatement).append("\n");
            sb.append("}\n");
        }

        return sb.toString();
    }
}
