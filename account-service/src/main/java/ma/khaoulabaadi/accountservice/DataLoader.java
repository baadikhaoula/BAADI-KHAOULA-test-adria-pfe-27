package ma.khaoulabaadi.accountservice;

import ma.khaoulabaadi.accountservice.domain.Account;
import ma.khaoulabaadi.accountservice.domain.AccountStatus;
import ma.khaoulabaadi.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public DataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ✅ Compte 1 : Client avec 5000€
        Account account1 = new Account();
        account1.setCustomerReference("CUST-001");
        account1.setStatus(AccountStatus.ACTIVE);
        account1.setCurrency("EUR");
        account1.setBalance(5000.0);
        accountRepository.save(account1);

        // ✅ Compte 2 : Client avec 10000$
        Account account2 = new Account();
        account2.setCustomerReference("CUST-002");
        account2.setStatus(AccountStatus.ACTIVE);
        account2.setCurrency("USD");
        account2.setBalance(10000.0);
        accountRepository.save(account2);

        // ✅ Compte 3 : Client bloqué avec 100 MAD
        Account account3 = new Account();
        account3.setCustomerReference("CUST-003");
        account3.setStatus(AccountStatus.BLOCKED);
        account3.setCurrency("MAD");
        account3.setBalance(100.0);
        accountRepository.save(account3);

        System.out.println("✅ 3 comptes de test créés avec succès !");
    }
}