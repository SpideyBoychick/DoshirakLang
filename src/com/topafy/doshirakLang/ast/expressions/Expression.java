package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.TypeValuePair;

public interface Expression {
    TypeValuePair eval();
}