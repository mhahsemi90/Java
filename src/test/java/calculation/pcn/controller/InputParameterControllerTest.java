package calculation.pcn.controller;

import calculation.controller.InputParameterController;
import calculation.repository.entity.InputParameter;
import calculation.services.inputs.InputParameterInput;
import calculation.services.interfaces.InputParameterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static calculation.assertclass.InputParameterAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputParameterControllerTest {

    @Mock
    InputParameterService inputParameterService;

    @InjectMocks
    InputParameterController inputParameterController;

    @Test
    void persistNewInputParameter() {
        InputParameterInput inputParameterInput = new InputParameterInput(null, "Test1", "Test2", "TEXT");
        InputParameter inputParameterPersisted = new InputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(inputParameterService.persistInputParameter(inputParameterInput))
                .then(invocation -> inputParameterPersisted);
        assertThat(inputParameterController.persistInputParameter(inputParameterInput))
                .isSameAs(inputParameterPersisted);
    }
}