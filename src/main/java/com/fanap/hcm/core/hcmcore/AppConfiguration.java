package com.fanap.hcm.core.hcmcore;

import com.fanap.hcm.core.hcmcore.pcn.services.scalars.TimeStampScalar;
import graphql.scalars.ExtendedScalars;
import graphql.validation.constraints.standard.NotBlankRule;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class AppConfiguration {
    public static String locale = "";

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        var validationRules = ValidationRules
                .newValidationRules()
                .addRule(new NotBlankRule())
                .build();
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(TimeStampScalar.GraphQLTimestamp)
                .directiveWiring(new ValidationSchemaWiring(validationRules));
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("exception");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
