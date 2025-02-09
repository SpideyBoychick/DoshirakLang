package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;
import com.topafy.doshirakLang.lib.Value;
import com.topafy.doshirakLang.lib.Variables;

public class AssignmentStatement implements Statement{
    private final String variableType;
    private final String variableName;
    private final Expression expression;

    public AssignmentStatement(String variableType, String variableName, Expression expression) {
        this.variableType = variableType;
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public void execute() {
        final Value exprRes = expression.eval();
        Variables.set(variableName, exprRes);
    }

    @Override
    public String toString() {
        return String.format("%s %s = %s", variableType, variableName, expression);
    }
}
