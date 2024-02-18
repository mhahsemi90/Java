package com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces;


import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IElementRepository extends JpaRepository<Element, Long> {

    @Query(
            value = "select e from Element e where e.vrId = :vrId and e.elementType.code = :elementTypeCode"
    )
    List<Element> findElementByVrIdAndByElementTypeCode(
            @Param("vrId") String vrId,
            @Param("elementTypeCode") String elementTypeCode
    );
}
