package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import calculation.repository.entity.InputParameter;
import calculation.repository.service.interfaces.InputParameterRepository;
import calculation.services.impl.InputParameterServiceImpl;
import calculation.services.inputs.InputParameterInput;
import calculation.services.mapper.InputParameterInputMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.InputParameterAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputParameterServiceImplTest {
    @Mock
    private InputParameterRepository inputParameterRepository;
    @Mock
    private InputParameterInputMapper inputParameterInputMapper;

    @InjectMocks
    private InputParameterServiceImpl inputParameterService;

    @Test
    void persistNewInputParameter() {
        InputParameterInput inputParameterInput = new InputParameterInput(null, "Test1", "Test2", "TEXT");
        InputParameter inputParameterMapped = new InputParameter(null, "Test1", "Test2", "TEXT", new ArrayList<>());
        InputParameter inputParameterPersisted = new InputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(inputParameterInputMapper.mapToInputParameter(inputParameterInput))
                .then(invocation -> inputParameterMapped);
        when(inputParameterRepository.save(inputParameterMapped))
                .then(invocation -> inputParameterPersisted);
        assertThat(inputParameterService.persistInputParameter(inputParameterInput))
                .isSameAs(inputParameterPersisted);
    }
}