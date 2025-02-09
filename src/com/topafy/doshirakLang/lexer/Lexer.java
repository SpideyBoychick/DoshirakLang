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
            }else if(currentChar == '/') {
                tokens.add(new Token(TokenType.DIV, "/"));
                advance();
            }else if(currentChar == '=') {
                tokens.add(new Token(TokenType.ASIGN, "="));
                advance();
            }else if(currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            }else if(currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
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
        if(res.equals("print")) return new Token(TokenType.PRINT, res);
        if(res.equals("println")) return new Token(TokenType.PRINTLN, res);
        if(res.equals("int")) return new Token(TokenType.INT, res);
        if(res.equals("double")) return new Token(TokenType.DOUBLE, res);
        if(res.equals("string")) return new Token(TokenType.STRING, res);
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
