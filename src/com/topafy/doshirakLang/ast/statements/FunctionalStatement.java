package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.FunctionalExpression;

public class FunctionalStatement implements Statement{

    private final FunctionalExpression function;

    public FunctionalStatement(FunctionalExpression function) {
        this.function = function;
    }

    @Override
    public void execute(){
        function.eval();
    }

    @Override
    public String toString() {
        return function.toString();
    }
}
