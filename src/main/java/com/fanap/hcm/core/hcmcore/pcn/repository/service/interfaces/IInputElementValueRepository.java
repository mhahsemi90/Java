package com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputElementValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInputElementValueRepository extends JpaRepository<InputElementValue, Long> {
}
