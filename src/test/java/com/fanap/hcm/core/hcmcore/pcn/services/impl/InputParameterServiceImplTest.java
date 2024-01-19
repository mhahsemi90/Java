package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IInputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.InputParameterAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputParameterServiceImplTest {
    @Mock
    private IInputParameterRepository inputParameterRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InputParameterServiceImpl inputParameterService;

    @Test
    void persistNewInputParameter() {
        InputParameterInput inputParameterInput = new InputParameterInput(null, "Test1", "Test2", "TEXT");
        InputParameter inputParameterMapped = new InputParameter(null, "Test1", "Test2", "TEXT", new ArrayList<>());
        InputParameter inputParameterPersisted = new InputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(modelMapper.map(inputParameterInput, InputParameter.class))
                .then(invocation -> inputParameterMapped);
        when(inputParameterRepository.save(inputParameterMapped))
                .then(invocation -> inputParameterPersisted);
        assertThat(inputParameterService.persistInputParameter(inputParameterInput))
                .isSameAs(inputParameterPersisted);
    }
}