package ma.khaoulabaadi.accountservice.web.rest;

import ma.khaoulabaadi.accountservice.domain.Account;
import ma.khaoulabaadi.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ✅ Lister tous les comptes (GET)
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    //  Récupérer un compte par son ID (GET)
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }

    // Créer un nouveau compte (POST)
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    //  Créditer un compte (PUT)
    @PutMapping("/{id}/credit")
    public Account creditAccount(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.credit(id, amount);
    }

    //  Débiter un compte (PUT)
    @PutMapping("/{id}/debit")
    public Account debitAccount(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.debit(id, amount);
    }
}