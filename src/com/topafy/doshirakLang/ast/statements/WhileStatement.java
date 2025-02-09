package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;

public class WhileStatement implements Statement{

    private final Expression condition;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute() {
        while((boolean)condition.eval().getValue() == true){
            statement.execute();
        }
    }

    @Override
    public String toString() {
        return "while(" + condition + ")" + statement;
    }
}
