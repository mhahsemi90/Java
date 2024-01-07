package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.ICalculationRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {
    private final ICalculationRepository ICalculationRepository;

    @Override
    public Calculation findCalculationById(Long id) {
        return ICalculationRepository.findById(id).orElse(null);
    }
}
