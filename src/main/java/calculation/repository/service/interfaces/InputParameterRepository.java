package calculation.repository.service.interfaces;

import calculation.repository.entity.InputParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputParameterRepository extends JpaRepository<InputParameter, Long> {
}
