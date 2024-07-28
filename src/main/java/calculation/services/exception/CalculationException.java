package calculation.services.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

@AllArgsConstructor
@Getter
public class CalculationException extends RuntimeException {
    private String locale;
    private String bundle;
    private Object[] args;
    private ErrorType errorType;
}
