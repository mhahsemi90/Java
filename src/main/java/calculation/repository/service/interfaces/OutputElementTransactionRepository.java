package calculation.repository.service.interfaces;

import calculation.repository.entity.OutputElementTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputElementTransactionRepository extends JpaRepository<OutputElementTransaction, Long> {
}
