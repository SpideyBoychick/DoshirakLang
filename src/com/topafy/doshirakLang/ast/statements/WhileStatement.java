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
            try {
                statement.execute();
            }  catch (BreakStatement bs){
                break;
            }  catch (ContinueStatement cs){
                //continue;
            }
        }
    }

    @Override
    public String toString() {
        return "while" + condition + " " + statement;
    }
}
