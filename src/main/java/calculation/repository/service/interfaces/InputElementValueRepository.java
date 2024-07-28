package calculation.repository.service.interfaces;

import calculation.repository.entity.InputElementValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputElementValueRepository extends JpaRepository<InputElementValue, Long> {
}
