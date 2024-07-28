package calculation.repository.service.interfaces;

import calculation.repository.entity.ElementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IElementTypeRepository extends JpaRepository<ElementType, Long> {

    @Query(value = "select et from ElementType et where et.code = :code")
    List<ElementType> findElementTypeByCode(@Param("code") String code);
}
