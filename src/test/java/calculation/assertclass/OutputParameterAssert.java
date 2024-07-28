package calculation.assertclass;

import calculation.repository.entity.OutputParameter;
import org.assertj.core.api.AbstractAssert;

public class OutputParameterAssert extends AbstractAssert<OutputParameterAssert, OutputParameter> {
    protected OutputParameterAssert(OutputParameter outputParameter) {
        super(outputParameter, OutputParameterAssert.class);
    }

    public static OutputParameterAssert assertThat(OutputParameter actual) {
        return new OutputParameterAssert(actual);
    }
}
