package com.topafy.doshirakLang.ast.expressions;

import com.topafy.doshirakLang.lib.Value;

public interface Expression {
    Value eval();
}
