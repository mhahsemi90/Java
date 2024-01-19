package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IOutputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.OutputParameterAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputParameterServiceImplTest {
    @Mock
    private IOutputParameterRepository outputParameterRepository;

    private final OutputParameterServiceImpl outputParameterService = new OutputParameterServiceImpl(
            outputParameterRepository
            , new ModelMapper());

    @Test
    void persistNewOutputParameter() {
        OutputParameterInput outputParameterInput = new OutputParameterInput(null, "Test1", "Test2", "TEXT");
        OutputParameter outputParameterMapped = new OutputParameter(null, "Test1", "Test2", "TEXT", new ArrayList<>());
        OutputParameter outputParameterPersisted = new OutputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(outputParameterRepository.save(outputParameterMapped))
                .then(invocation -> outputParameterPersisted);
        assertThat(outputParameterService.persistOutputParameter(outputParameterInput))
                .isSameAs(outputParameterPersisted);
    }
}