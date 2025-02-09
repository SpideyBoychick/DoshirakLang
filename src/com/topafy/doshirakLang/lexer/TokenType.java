package com.topafy.doshirakLang.lexer;

public enum TokenType {
    PLUS,// +
    MINUS,// -
    MUL,// *
    DIV,// /
    OR,// |
    OROR,// ||
    AND,// &
    ANDAND,// &&

    //data
    INT_LITERAL,// 0123456789
    HEX_NUMBER_LITERAL,// #FCA987
    DOUBLE_LITERAL,//3.141592 2.71 2.5, 80.0
    STRING_LITERAL,// "Hello world!"

    //types of data
    INT,// int
    DOUBLE,// double
    STRING,// string
    BOOL,// bool

    //parentheses
    LPAREN,// (
    RPAREN,// )
    LFIGURE,// {
    RFIGURE,// }

    //keyChars
    ASIGN,// =
    COLON,// :
    SEMICOLON,// ;
    LOWER,// <
    BIGGER,// >
    LOWEROREQ,// <=
    BIGGEROREQ,// >=
    EQ,// ==
    NOT,// !
    NOTEQ,// !=

    //keyWords and names
    WORD,// names
    PRINT,// Console output
    PRINTLN,// Console output this auto \n
    IF,// condition
    ELSEIF,// condition
    ELSE,// condition
    WHILE,
    FOR,
    REPEAT,
    EOF// End of file
}
