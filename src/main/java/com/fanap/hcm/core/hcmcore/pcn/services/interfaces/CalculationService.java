package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;

public interface CalculationService {
    Calculation findCalculationById(Long id);
}
