package ma.khaoulabaadi.transactionservice.repository;

import ma.khaoulabaadi.transactionservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}