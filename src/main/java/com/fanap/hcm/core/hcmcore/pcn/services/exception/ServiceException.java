package com.fanap.hcm.core.hcmcore.pcn.services.exception;

import com.fanap.hcm.core.hcmcore.AppConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.execution.ErrorType;

@RequiredArgsConstructor
@Getter
public enum ServiceException {
    PCN_TEST_EXCEPTION("PCN_TEST_EXCEPTION", ErrorType.NOT_FOUND);

    private final String code;
    private final ErrorType errorType;

    public void throwException(Object... args) {
        throw new CalculationException(
                AppConfiguration.locale,
                getCode(),
                args,
                getErrorType()
        );
    }

    public CalculationException getException(Object... args) {
        return new CalculationException(
                AppConfiguration.locale,
                getCode(),
                args,
                getErrorType()
        );
    }
}
