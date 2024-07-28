package calculation.repository.service.interfaces;

import calculation.repository.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICalculationRepository extends JpaRepository<Calculation, Long> {
}
