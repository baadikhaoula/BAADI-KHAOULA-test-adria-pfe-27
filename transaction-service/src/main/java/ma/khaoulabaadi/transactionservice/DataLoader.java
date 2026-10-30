package ma.khaoulabaadi.transactionservice;

import ma.khaoulabaadi.transactionservice.domain.Transaction;
import ma.khaoulabaadi.transactionservice.repository.TransactionRepository;
import ma.khaoulabaadi.transactionservice.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final TransactionRepository transactionRepository;

    public DataLoader(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ⚠️ Remplacez ces UUIDs par les vrais IDs de vos comptes dans account-service
        UUID account1Id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000"); // CUST-001
        UUID account2Id = UUID.fromString("223e4567-e89b-12d3-a456-426614174001"); // CUST-002

        // Transaction 1 : Achat de nourriture
        Transaction t1 = new Transaction();
        t1.setId(UUID.randomUUID());
        t1.setAccountId(account1Id);
        t1.setAmount(new BigDecimal("250.00"));
        t1.setCurrency("EUR");
        t1.setMerchantCategory("FOOD");
        t1.setCountry("MA");
        t1.setCreatedAt(Instant.now());
        transactionRepository.save(t1);

        // Transaction 2 : Achat en ligne
        Transaction t2 = new Transaction();
        t2.setId(UUID.randomUUID());
        t2.setAccountId(account2Id);
        t2.setAmount(new BigDecimal("1500.00"));
        t2.setCurrency("USD");
        t2.setMerchantCategory("ONLINE");
        t2.setCountry("US");
        t2.setCreatedAt(Instant.now());
        transactionRepository.save(t2);

        System.out.println("✅ 2 transactions de test créées !");
    }
}