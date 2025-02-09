package com.topafy.doshirakLang.lib.variables;

import com.topafy.doshirakLang.lib.variables.TypeValuePair;
import com.topafy.doshirakLang.lib.variables.VariableType;

public class VoidValue implements TypeValuePair {

    @Override
    public VariableType getType() {
        return VariableType.VOID;
    }

    @Override
    public Object getValue() {
        throw new RuntimeException("Cannot to get void");
    }

    @Override
    public void setValue(Object val){
        throw new RuntimeException("Cannot to set void");
    }
}
