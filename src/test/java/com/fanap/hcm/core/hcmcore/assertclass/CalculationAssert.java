package com.fanap.hcm.core.hcmcore.assertclass;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import org.assertj.core.api.AbstractAssert;

public class CalculationAssert extends AbstractAssert<CalculationAssert, Calculation> {
    protected CalculationAssert(Calculation calculation) {
        super(calculation, CalculationAssert.class);
    }

    public static CalculationAssert assertThat(Calculation actual) {
        return new CalculationAssert(actual);
    }
}
