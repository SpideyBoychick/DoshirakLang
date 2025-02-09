package com.topafy.doshirakLang.lib.functions;

import com.topafy.doshirakLang.lib.variables.TypeValuePair;

public interface Function {

    TypeValuePair execute(TypeValuePair... args);
}
