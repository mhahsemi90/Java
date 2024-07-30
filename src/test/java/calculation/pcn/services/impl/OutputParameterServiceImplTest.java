package calculation.pcn.services.impl;

import calculation.assertclass.OutputParameterAssert;
import calculation.repository.entity.OutputParameter;
import calculation.repository.service.interfaces.OutputParameterRepository;
import calculation.services.impl.OutputParameterServiceImpl;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.mapper.OutputParameterInputMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputParameterServiceImplTest {
    @Mock
    private OutputParameterRepository outputParameterRepository;
    @Mock
    private OutputParameterInputMapper outputParameterInputMapper;
    @InjectMocks
    private OutputParameterServiceImpl outputParameterService;

    @Test
    void persistNewOutputParameter() {
        OutputParameterInput outputParameterInput = new OutputParameterInput(null, "Test1", "Test2", "TEXT");
        OutputParameter outputParameterMapped = new OutputParameter(null, "Test1", "Test2", "TEXT", new ArrayList<>());
        OutputParameter outputParameterPersisted = new OutputParameter(1L, "Test1", "Test2", "TEXT", new ArrayList<>());
        when(outputParameterInputMapper
                .mapToOutputParameter(outputParameterInput))
                .then(invocation -> outputParameterMapped);
        lenient().when(outputParameterRepository.save(outputParameterMapped))
                .then(invocation -> outputParameterPersisted);
        OutputParameterAssert.assertThat(outputParameterService.persistOutputParameter(outputParameterInput))
                .isSameAs(outputParameterPersisted);
    }
}