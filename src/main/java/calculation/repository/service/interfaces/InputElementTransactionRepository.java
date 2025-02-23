package calculation.repository.service.interfaces;

import calculation.repository.entity.InputElementTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputElementTransactionRepository extends JpaRepository<InputElementTransaction, Long> {
}
