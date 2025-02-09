package com.topafy.doshirakLang.lexer;

import java.util.List;

public class TestLexer {

    public static void main(String[] args){
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize("bool a = 2+2 == 4//2+2 is 4\nif a == 1 /*print*/print(a)");
        for(Token token : tokens){
            System.out.println(token.toString());
        }
    }
}
