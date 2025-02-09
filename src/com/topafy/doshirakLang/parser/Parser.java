package com.topafy.doshirakLang.parser;

import com.topafy.doshirakLang.ast.expressions.*;
import com.topafy.doshirakLang.ast.statements.AssignmentStatement;
import com.topafy.doshirakLang.ast.statements.IfStatement;
import com.topafy.doshirakLang.ast.statements.PrintStatement;
import com.topafy.doshirakLang.ast.statements.Statement;
import com.topafy.doshirakLang.lexer.Token;
import com.topafy.doshirakLang.lexer.TokenType;
import com.topafy.doshirakLang.lib.VariableType;

import java.util.ArrayList;
import java.util.List;

public class Parser {


    private static final List<TokenType> variableTypes = new ArrayList<>();
    static {
        variableTypes.add(TokenType.INT);
        variableTypes.add(TokenType.DOUBLE);
        variableTypes.add(TokenType.STRING);
        variableTypes.add(TokenType.BOOL);
    }
    private static final Token EOF = new Token(TokenType.EOF, null);

    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    public List<Statement> parse(){
        final List<Statement> res = new ArrayList<>();
        while(!match(TokenType.EOF)){
            res.add(statement());
        }
        return res;
    }

    private Statement statement(){
        if(match(TokenType.PRINT)){
            consume(TokenType.LPAREN);
            Expression expr = expression();
            consume(TokenType.RPAREN);
            return new PrintStatement(expr);
        }
        if(match(TokenType.PRINTLN)){
            consume(TokenType.LPAREN);
            Expression expr = expression();
            consume(TokenType.RPAREN);
            return new PrintStatement(expr, "\n");
        }
        if(match(TokenType.IF)){
            return ifElseStatement();
        }
        if(match(TokenType.ELSEIF)){
            return ifElseStatement();
        }
        //if(match(TokenType.ELSE)){
        //    return ifElseStatement();
        //}
        return assignmentStatement();
    }

    private Statement assignmentStatement(){
        Token current = get(0);
        final TokenType vtype = current.getType();
        if(variableTypes.contains(vtype) && get(1).getType() == TokenType.WORD && get(2).getType() == TokenType.ASIGN){
            current = consume(vtype);
            final VariableType type = strToVarType(current.getValue());
            current = consume(TokenType.WORD);
            final String name = current.getValue();
            consume(TokenType.ASIGN);
            return new AssignmentStatement(type, name, expression());
        }
        throw new RuntimeException("Unknown statement");
    }

    private Statement ifElseStatement(){
        final Expression cond = expression();
        final Statement ifStatement = statement();
        Statement elseStatement;

        if(match(TokenType.ELSE)){
            elseStatement = statement();
        }
        else {
            elseStatement = null;
        }
        return new IfStatement(cond, ifStatement, elseStatement);
    }

    private Expression expression(){
        return conditional();
    }

    private Expression conditional(){
        Expression expr = additive();

        while(true){
            if(match(TokenType.EQ)){
                expr = new ConditionExpression("==", expr, additive());
                continue;
            }
            if(match(TokenType.NOTEQ)){
                expr = new ConditionExpression("!=", expr, additive());
                continue;
            }
            if(match(TokenType.LOWER)){
                expr = new ConditionExpression("<", expr, additive());
                continue;
            }
            if(match(TokenType.LOWEROREQ)){
                expr = new ConditionExpression("<=", expr, additive());
                continue;
            }
            if(match(TokenType.BIGGER)){
                expr = new ConditionExpression(">", expr, additive());
                continue;
            }
            if(match(TokenType.BIGGEROREQ)){
                expr = new ConditionExpression(">=", expr, additive());
                continue;
            }
            if(match(TokenType.OROR)){
                expr = new ConditionExpression("||", expr, additive());
                continue;
            }
            if(match(TokenType.ANDAND)){
                expr = new ConditionExpression("&&", expr, additive());
                continue;
            }
            break;
        }

        return expr;
    }

    private Expression additive(){
        Expression expr = multiplicative();

        while(true){
            if(match(TokenType.PLUS)){
                expr = new BinaryExpression('+', expr, multiplicative());
                continue;
            }
            if(match(TokenType.MINUS)){
                expr = new BinaryExpression('-', expr, multiplicative());
                continue;
            }
            break;
        }

        return expr;
    }

    private Expression multiplicative(){
        Expression expr = unary();
        while(true){
            if(match(TokenType.MUL)){
                expr = new BinaryExpression('*', expr, unary());
                continue;
            }
            if(match(TokenType.DIV)){
                expr = new BinaryExpression('/', expr, unary());
                continue;
            }
            break;
        }
        return expr;
    }

    private Expression unary(){
        if(match(TokenType.PLUS)){
            return new UnaryExpression('+', primary());
        }
        if(match(TokenType.MINUS)){
            return new UnaryExpression('-', primary());
        }
        return primary();
    }

    private Expression primary(){
        final Token current = get(0);
        if(match(TokenType.DOUBLE_LITERAL)){
            return new ValueExpression(Double.parseDouble(current.getValue()));
        }
        if(match(TokenType.INT_LITERAL)){
            return new ValueExpression(Integer.parseInt(current.getValue()));
        }
        if(match(TokenType.HEX_NUMBER_LITERAL)){
            return new ValueExpression(Long.parseLong(current.getValue(), 16));
        }
        if(match(TokenType.WORD)){
            return new VariableExpression(current.getValue());
        }
        if(match(TokenType.STRING_LITERAL)){
            return new ValueExpression(current.getValue());
        }
        if(match(TokenType.LPAREN)){
            Expression result = expression();
            consume(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("exception primary");
    }

    private boolean match(TokenType type){
        final Token current = get(0);
        if(type != current.getType()) return false;
        pos++;
        return true;
    }

    private Token consume(TokenType type){
        final Token current = get(0);
        if(type != current.getType()) throw new RuntimeException("Wrong token type " + current.getType() + ". Needs:" + type);
        pos++;
        return current;
    }

    private Token get(int relPos){
        final int position = pos + relPos;
        if(position >= size) return EOF;
        return tokens.get(position);
    }

    private VariableType strToVarType(String str){
        return switch (str) {
            case "int" -> VariableType.INT;
            case "double" -> VariableType.DOUBLE;
            case "string" -> VariableType.STRING;
            case "bool" -> VariableType.BOOL;
            default -> throw new RuntimeException("Unknown type");
        };
    }
}
