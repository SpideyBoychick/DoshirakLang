package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.variables.TypeValuePair;

public interface Expression {
    TypeValuePair eval();
}