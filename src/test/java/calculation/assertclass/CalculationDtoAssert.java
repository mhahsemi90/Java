package calculation.assertclass;

import calculation.services.dto.entity.CalculationDto;
import org.assertj.core.api.AbstractAssert;

public class CalculationDtoAssert extends AbstractAssert<CalculationDtoAssert, CalculationDto> {
    protected CalculationDtoAssert(CalculationDto calculationDto) {
        super(calculationDto, CalculationDtoAssert.class);
    }

    public static CalculationDtoAssert assertThat(CalculationDto actual) {
        return new CalculationDtoAssert(actual);
    }
}
