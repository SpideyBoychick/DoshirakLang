package com.topafy.doshirakLang.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String input;
    private int pos;
    private Character currentChar;

    public List<Token> tokenize(String input) {
        this.input = input;
        this.pos = 0;
        this.currentChar = input.charAt(0);

        final List<Token> tokens = new ArrayList<>();

        while (currentChar != null) {
            if(Character.isSpaceChar(currentChar)){
                skipWhiteSpaces();
            }else if(currentChar == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
                advance();
            }else if(currentChar == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
                advance();
            }else if(currentChar == '*') {
                tokens.add(new Token(TokenType.MUL, "*"));
                advance();
            }else if(currentChar == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                advance();
            }else if(currentChar == '/') {
                advance();
                if(currentChar == '/') {
                    skipComments();
                }
                else if(currentChar == '*') {
                    skipMultilineComments();
                }
                else
                    tokens.add(new Token(TokenType.DIV, "/"));
            }else if(currentChar == '=') {
                advance();
                if(currentChar == '=') {
                    tokens.add(new Token(TokenType.EQ, "=="));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.ASIGN, "="));
            }else if(currentChar == '<') {
                advance();
                if(currentChar == '=') {
                    tokens.add(new Token(TokenType.LOWEROREQ, "<="));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.LOWER, "<"));
            }else if(currentChar == '>') {
                advance();
                if(currentChar == '=') {
                    tokens.add(new Token(TokenType.BIGGEROREQ, ">="));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.BIGGER, ">"));
            }else if(currentChar == '!') {
                advance();
                if(currentChar == '=') {
                    tokens.add(new Token(TokenType.NOTEQ, "!="));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.NOT, "!"));
            }else if(currentChar == '|') {
                advance();
                if(currentChar == '|') {
                    tokens.add(new Token(TokenType.OROR, "||"));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.OR, "|"));
            }else if(currentChar == '&') {
                advance();
                if(currentChar == '&') {
                    tokens.add(new Token(TokenType.ANDAND, "&&"));
                    advance();
                }
                else
                    tokens.add(new Token(TokenType.AND, "&"));
            }else if(currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            }else if(currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                advance();
            }else if(currentChar == '{') {
                tokens.add(new Token(TokenType.LFIGURE, "{"));
                advance();
            }else if(currentChar == '}') {
                tokens.add(new Token(TokenType.RFIGURE, "}"));
                advance();
            }else if(currentChar == ':') {
                tokens.add(new Token(TokenType.COLON, ":"));
                advance();
            }else if(currentChar == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
                advance();
            }else if(Character.isDigit(currentChar)){
                tokens.add(tokenizeNumber());
            }else if(currentChar == '#'){
                tokens.add(tokenizeHexInt());
            }else if(currentChar == '"'){
                tokens.add(new Token(TokenType.STRING_LITERAL, consumeString()));
            }else if(Character.isAlphabetic(currentChar)){
                tokens.add(wordOrKeyword());
            }else{
                //throw new RuntimeException("Unrecognized token " + currentChar);
                advance();
            }
        }
        //tokens.add(new Token(TokenType.EOF, null));
        return tokens;
    }

    private void advance(){
        pos++;
        if(pos>input.length() - 1) currentChar = null;
        else currentChar = input.charAt(pos);
    }

    private void skipWhiteSpaces(){
        while(currentChar != null && Character.isSpaceChar(currentChar))
            advance();
    }

    private void skipComments() {
        try {
            while ("\r\n\0".indexOf(currentChar) == -1) {
                advance();
            }
        } catch (NullPointerException e){
            advance();
        }
    }

    private void skipMultilineComments() {
        while (true) {
            advance();
            if(currentChar != null) {
                if ("\r\n\0".indexOf(currentChar) != -1) {
                    throw new RuntimeException("Not closed comment");
                }
                if (currentChar == '*') {
                    advance();
                    if (currentChar == '/') {
                        advance();
                        break;
                    }
                }
            }
            else{
                advance();
                return;
            }
        }
    }

    private String consumeString(){
        final StringBuilder result = new StringBuilder();
        advance();
        while(currentChar != null){

            if(currentChar == '\\'){
                advance();
                switch (currentChar){
                    case '"': result.append('\"'); advance(); continue;
                    case 'n': result.append('\n'); advance(); continue;
                    case 't': result.append('\t'); advance(); continue;
                }
                result.append('\\');
                advance();
                continue;
            }
            if(currentChar == '"') {
                advance();
                break;
            }

            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    private Token wordOrKeyword(){
        final StringBuilder result = new StringBuilder();

        while(currentChar != null && Character.isLetterOrDigit(currentChar)){
            result.append(currentChar);
            advance();
        }

        final String res = result.toString();
        if(res.equals("int")) return new Token(TokenType.INT, res);
        if(res.equals("double")) return new Token(TokenType.DOUBLE, res);
        if(res.equals("string")) return new Token(TokenType.STRING, res);
        if(res.equals("bool")) return new Token(TokenType.BOOL, res);
        if(res.equals("if")) return new Token(TokenType.IF, res);
        if(res.equals("elseif")) return new Token(TokenType.ELSEIF, res);
        if(res.equals("else")) return new Token(TokenType.ELSE, res);
        if(res.equals("while")) return new Token(TokenType.WHILE, res);
        if(res.equals("for")) return new Token(TokenType.FOR, res);
        if(res.equals("repeat")) return new Token(TokenType.REPEAT, res);
        if(res.equals("break")) return new Token(TokenType.BREAK, res);
        if(res.equals("continue")) return new Token(TokenType.CONTINUE, res);
        else return new Token(TokenType.WORD, res);
    }

    private Token tokenizeNumber(){
        final StringBuilder result = new StringBuilder();
        TokenType type = TokenType.INT_LITERAL;
        while(currentChar != null){
            if(currentChar == '.'){
                type = TokenType.DOUBLE_LITERAL;
                if(result.indexOf(".") != -1) throw new IllegalArgumentException("Нада только адну точку");
            } else if(!Character.isDigit(currentChar)) break;
            result.append(currentChar);
            advance();
        }
        return new Token(type, result.toString());
    }

    private Token tokenizeHexInt(){
        final StringBuilder result = new StringBuilder();
        advance();
        while(currentChar != null && (Character.isDigit(currentChar) || ("abcdef".indexOf(Character.toLowerCase(currentChar))) != -1)){
            result.append(currentChar);
            advance();
        }
        return new Token(TokenType.HEX_NUMBER_LITERAL, result.toString());
    }
}
