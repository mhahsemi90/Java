package com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputElementTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutputElementTransactionRepository extends JpaRepository<OutputElementTransaction, Long> {
}
