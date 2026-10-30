package ma.khaoulabaadi.accountservice.service;

import ma.khaoulabaadi.accountservice.domain.Account;
import ma.khaoulabaadi.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec l'ID : " + id));
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account credit(UUID id, double amount) {
        Account account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account debit(UUID id, double amount) {
        Account account = getAccountById(id);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Solde insuffisant !");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}