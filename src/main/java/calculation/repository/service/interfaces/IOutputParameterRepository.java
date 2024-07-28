package calculation.repository.service.interfaces;

import calculation.repository.entity.OutputParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutputParameterRepository extends JpaRepository<OutputParameter, Long> {
}
