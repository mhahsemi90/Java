package calculation.pcn.services.impl;

import calculation.assertclass.CalculationDtoAssert;
import calculation.repository.entity.Calculation;
import calculation.repository.service.interfaces.CalculationRepository;
import calculation.services.dto.entity.CalculationDto;
import calculation.services.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceImplTest {
    @Mock
    private CalculationRepository calculationRepository;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Test
    void findCalculationByIdTest() {
        when(calculationRepository.findById(0L))
                .then(invocation -> Optional.empty());
        CalculationDtoAssert
                .assertThat(calculationService.findCalculationById(0L))
                .isNull();
        Calculation calculation = new Calculation(
                1L
                , new Timestamp(System.currentTimeMillis())
                , new ArrayList<>()
                , new ArrayList<>()
        );
        CalculationDto calculationDto = new CalculationDto(
                1L
                , calculation.getActionDate()
                , new ArrayList<>()
                , new ArrayList<>()
        );
        when(calculationRepository.findById(1L))
                .then(invocation -> Optional.of(calculation));
        CalculationDtoAssert
                .assertThat(calculationService.findCalculationById(1L))
                .isSameAs(calculationDto);
        CalculationDtoAssert
                .assertThat(calculationService.findCalculationById(1L))
                .isInstanceOf(CalculationDto.class);
    }
}