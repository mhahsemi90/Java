package calculation.assertclass;

import calculation.repository.entity.Calculation;
import org.assertj.core.api.AbstractAssert;

public class CalculationAssert extends AbstractAssert<CalculationAssert, Calculation> {
    protected CalculationAssert(Calculation calculation) {
        super(calculation, CalculationAssert.class);
    }

    public static CalculationAssert assertThat(Calculation actual) {
        return new CalculationAssert(actual);
    }
}
