package calculation.repository.service.interfaces;

import calculation.repository.entity.OutputElementValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputElementValueRepository extends JpaRepository<OutputElementValue, Long> {
}
