package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.OutputParameterAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputParameterControllerTest {

    @Mock
    IOutputParameterService outputParameterService;

    @InjectMocks
    OutputParameterController outputParameterController;

    @Test
    void persistNewOutputParameter() {
        OutputParameterInput outputParameterInput = new OutputParameterInput(null, "Test1", "Test2", "TEXT");
        OutputParameter outputParameterPersisted = new OutputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(outputParameterService.persistOutputParameter(outputParameterInput))
                .then(invocation -> outputParameterPersisted);
        assertThat(outputParameterController.persistOutputParameter(outputParameterInput))
                .isSameAs(outputParameterPersisted);
    }
}