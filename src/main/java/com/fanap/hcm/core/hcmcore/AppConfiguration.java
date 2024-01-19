package com.fanap.hcm.core.hcmcore;

import com.fanap.hcm.core.hcmcore.pcn.services.scalars.TimeStampScalar;
import graphql.scalars.ExtendedScalars;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(TimeStampScalar.GraphQLTimestamp);
    }
}
