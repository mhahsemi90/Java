package com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces;


import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select p from Person p where p.vrId = :vrId")
    List<Person> findPersonByVrIdEquals(@Param("vrId") String vrId);

    @Query(
            value = "select p.* from Person p where p.vr_Id = :vrId",
            nativeQuery = true
    )
    Optional<Person> findPersonByVrId(@Param("vrId") String vrId);

    @Deprecated
    @Modifying
    @Query(value = "delete from Person p where p.vrId = :vrId")
    @Transactional
    int deletePersonByVrId(@Param("vrId") String vrId);
}
