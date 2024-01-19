package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.ICalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import static com.fanap.hcm.core.hcmcore.assertclass.CalculationAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceImplTest {
    @Mock
    private ICalculationRepository calculationRepository;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Test
    void findCalculationByIdTest() {
        when(calculationRepository.findById(0L))
                .then(invocation -> Optional.empty());
        assertThat(calculationService.findCalculationById(0L)).isNull();
        Calculation calculation = new Calculation(1L, new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        when(calculationRepository.findById(1L))
                .then(invocation -> Optional.of(calculation));
        assertThat(calculationService.findCalculationById(1L)).isSameAs(calculation);
        assertThat(calculationService.findCalculationById(1L)).isInstanceOf(Calculation.class);
    }
}