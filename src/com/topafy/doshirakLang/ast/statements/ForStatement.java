package com.topafy.doshirakLang.ast.statements;

import com.topafy.doshirakLang.ast.expressions.Expression;

public class ForStatement implements Statement{

    private final Statement init;
    private final Expression termination;
    private final Statement increment;
    private final Statement statement;

    public ForStatement(Statement init, Expression termination, Statement increment, Statement statement) {
        this.init = init;
        this.termination = termination;
        this.increment = increment;
        this.statement = statement;
    }

    @Override
    public void execute() {
        for(init.execute(); (boolean)termination.eval().getValue() == true; increment.execute()) {
            try {
                statement.execute();
            }  catch (BreakStatement bs){
                break;
            } catch (ContinueStatement cs){
                //continue;
            }
        }
    }

    @Override
    public String toString() {
        return "for " + init + "; " + termination + "; " + increment;
    }
}
