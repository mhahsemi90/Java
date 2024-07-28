package com.fanap.hcm.core.hcmcore.assertclass;

import calculation.repository.entity.InputParameter;
import org.assertj.core.api.AbstractAssert;

public class InputParameterAssert extends AbstractAssert<InputParameterAssert, InputParameter> {
    protected InputParameterAssert(InputParameter inputParameter) {
        super(inputParameter, InputParameterAssert.class);
    }

    public static InputParameterAssert assertThat(InputParameter actual) {
        return new InputParameterAssert(actual);
    }
}
