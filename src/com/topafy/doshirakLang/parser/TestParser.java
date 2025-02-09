package com.topafy.doshirakLang.parser;

import com.topafy.doshirakLang.ast.statements.BlockStatement;
import com.topafy.doshirakLang.ast.statements.Statement;
import com.topafy.doshirakLang.lexer.Lexer;
import com.topafy.doshirakLang.lexer.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestParser {
    public static void main(String[] args){
        Lexer lexer = new Lexer();

        String input;
        try {
            input = Files.readString(Paths.get("program.doshi"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Token> tokens = lexer.tokenize(input);
        for(Token t : tokens){
            //System.out.println(t);
        }
        BlockStatement statements = new Parser(tokens).parse();
        //System.out.println(statements.toString());
        //System.out.println("-----Вывод-----");
        statements.execute();
    }
}
