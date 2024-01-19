package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.services.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.CalculationAssert.assertThat;
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
        assertThat(calculationController.findCalculationById(0L)).isNull();
        Calculation calculation = new Calculation(1L, new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        when(calculationService.findCalculationById(1L))
                .then(invocation -> calculation);
        assertThat(calculationController.findCalculationById(1L)).isSameAs(calculation);
        assertThat(calculationController.findCalculationById(1L)).isInstanceOf(Calculation.class);
    }
}