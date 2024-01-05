package com.fanap.hcm.core.pcn.repository.service.interfaces;


import com.fanap.hcm.core.pcn.repository.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select p from Person p where p.vrId = :vrId")
    List<Person> findPersonByVrIdEquals(@Param("vrId") String vrId);

    @Deprecated
    @Query(
            value = "select p.* from Person p where p.vrId = :vrId",
            nativeQuery = true
    )
    List<Person> findPersonByVrIdNative(@Param("vrId") String vrId);
}
