package com.topafy.doshirakLang.lexer;

public enum TokenType {
    PLUS,// +
    MINUS,// -
    MUL,// *
    DIV,// /

    INT_LITERAL,// 0123456789
    HEX_NUMBER_LITERAL,// #FCA987
    DOUBLE_LITERAL,//3.141592 2.71 2.5, 80.0
    STRING_LITERAL,// "Hello world!"

    INT,// int
    DOUBLE,// double
    STRING,// string

    LPAREN,// (
    RPAREN,// )

    ASIGN,// =
    COLON,// :
    SEMICOLON,// ;

    WORD,// names
    PRINT,// Console output
    PRINTLN,// Console output this auto \n
    EOF// End of file
}
