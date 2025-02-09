package com.topafy.doshirakLang.lexer;

import java.util.List;

public class TestLexer {

    public static void main(String[] args){
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize("int a = 0");
        for(Token token : tokens){
            System.out.println(token.toString());
        }
    }
}
