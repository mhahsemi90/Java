package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.services.exception.CalculationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ControllerExceptionMessageCreation {
    private final MessageSource messageSource;

    String getMessage(Exception e) {
        return e.getMessage();
    }

    String getMessage(CalculationException e) {
        Locale locale = new Locale("");
        if (e.getLocale() != null)
            locale = new Locale(e.getLocale().toLowerCase());
        return messageSource.getMessage(
                e.getBundle(),
                e.getArgs(),
                e.getBundle(),
                locale);
    }
}
