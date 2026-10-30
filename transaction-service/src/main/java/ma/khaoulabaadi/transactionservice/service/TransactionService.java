package ma.khaoulabaadi.transactionservice.service;

import ma.khaoulabaadi.transactionservice.client.AccountFeignClient;
import ma.khaoulabaadi.transactionservice.domain.Transaction;
import ma.khaoulabaadi.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountFeignClient accountFeignClient;

    public TransactionService(TransactionRepository transactionRepository, AccountFeignClient accountFeignClient) {
        this.transactionRepository = transactionRepository;
        this.accountFeignClient = accountFeignClient;
    }
    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaction introuvable : " + id));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(UUID accountId, BigDecimal amount, String currency, String merchantCategory, String country) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setMerchantCategory(merchantCategory);
        transaction.setCountry(country);
        transaction.setCreatedAt(Instant.now());
        return transactionRepository.save(transaction);
    }
}