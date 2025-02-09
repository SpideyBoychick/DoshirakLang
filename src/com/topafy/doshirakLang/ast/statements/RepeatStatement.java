package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;

public class RepeatStatement implements Statement{
    private final Expression times;
    private final Statement statement;

    public RepeatStatement(Statement statement, Expression times) {
        this.times = times;
        this.statement = statement;
    }

    @Override
    public void execute() {
        for(int i = 0; i < (int)times.eval().getValue(); i++){
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
        return "repeat" + times + " " + statement;
    }
}
