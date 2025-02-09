package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;

public class PrintStatement implements Statement {

    private final Expression expression;
    private final String adding;

    public PrintStatement(Expression expression) {
        this.expression = expression;
        adding = "";
    }

    public PrintStatement(Expression expression, String adding) {
        this.expression = expression;
        this.adding = adding;
    }

    @Override
    public void execute() {
        System.out.print(expression.eval() + adding);
    }


    @Override
    public String toString() {
        return String.format("print(%s)", expression);
    }
}
