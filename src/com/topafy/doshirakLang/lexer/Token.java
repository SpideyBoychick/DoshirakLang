package com.topafy.doshirakLang.lexer;

public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public TokenType getType(){
        return type;
    }

    public String getValue(){
        return value;
    }

    public String toString(){
        return String.format("%s %s", type, value);
    }
}
