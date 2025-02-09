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
    LOWER,// <
    BIGGER,// >
    LOWEROREQ,// <=
    BIGGEROREQ,// >=
    EQ,// ==
    NOT,// !
    NOTEQ,// !=

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
    COMMA,// ,

    //keyWords and names
    WORD,// names
    IF,// condition
    ELSEIF,// condition
    ELSE,// condition
    WHILE,// repeat code while condition is true
    FOR,// repeat code with some init, condition and increment
    REPEAT,// repeat code n times
    BREAK,// breaks the cycle
    CONTINUE,// go to the next iteration
    EOF// End of file
}
