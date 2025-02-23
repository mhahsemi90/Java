package calculation.pcn.controller;

import calculation.assertclass.OutputParameterAssert;
import calculation.controller.OutputParameterController;
import calculation.repository.entity.OutputParameter;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.interfaces.OutputParameterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputParameterControllerTest {

    @Mock
    OutputParameterService outputParameterService;

    @InjectMocks
    OutputParameterController outputParameterController;

    @Test
    void persistNewOutputParameter() {
        OutputParameterInput outputParameterInput = new OutputParameterInput(null, "Test1", "Test2", "TEXT");
        OutputParameter outputParameterPersisted = new OutputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(outputParameterService.persistOutputParameter(outputParameterInput))
                .then(invocation -> outputParameterPersisted);
        OutputParameterAssert.assertThat(outputParameterController.persistOutputParameter(outputParameterInput))
                .isSameAs(outputParameterPersisted);
    }
}