package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;
import com.topafy.doshirakLang.lib.TypeValuePair;
import com.topafy.doshirakLang.lib.VariableType;
import com.topafy.doshirakLang.lib.Variables;

public class AssignmentStatement implements Statement{
    private final VariableType variableType;
    private final String variableName;
    private final Expression expression;

    public AssignmentStatement(VariableType variableType, String variableName, Expression expression) {
        this.variableType = variableType;
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public void execute() {
        final TypeValuePair exprRes = expression.eval();
        Variables.set(variableName, exprRes);
    }

    @Override
    public String toString() {
        return String.format("%s %s = %s", variableType, variableName, expression);
    }
}
