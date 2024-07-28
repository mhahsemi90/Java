package calculation.pcn.controller;

import calculation.assertclass.CalculationAssert;
import calculation.controller.CalculationController;
import calculation.repository.entity.Calculation;
import calculation.services.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    @Mock
    CalculationServiceImpl calculationService;

    @InjectMocks
    CalculationController calculationController;

    @Test
    void findCalculationById() {
        when(calculationService.findCalculationById(0L))
                .then(invocation -> null);
        CalculationAssert.assertThat(calculationController.findCalculationById(0L)).isNull();
        Calculation calculation = new Calculation(1L, new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        when(calculationService.findCalculationById(1L))
                .then(invocation -> calculation);
        CalculationAssert.assertThat(calculationController.findCalculationById(1L)).isSameAs(calculation);
        CalculationAssert.assertThat(calculationController.findCalculationById(1L)).isInstanceOf(Calculation.class);
    }
}