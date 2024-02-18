package com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFormulaRepository extends JpaRepository<Formula, Long> {

    @Query("select f from Formula f where f.code = :code")
    List<Formula> findFormulaByCode(@Param("code") String code);

    @Query("select f from Formula f " +
            "where f.code = :code " +
            "and f.version=(select max(f.version) from Formula f where f.code=:code)")
    List<Formula> findTopByVersionAndCode(@Param("code") String code);

    @Query("select f from Formula f where f.code = :code and f.version = :version")
    List<Formula> findFormulaByVersionAndCode(
            @Param("code") String code,
            @Param("version") Long version
    );
}
