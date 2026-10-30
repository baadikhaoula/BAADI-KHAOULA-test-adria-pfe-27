package ma.khaoulabaadi.accountservice.web.rest;

import ma.khaoulabaadi.accountservice.domain.Account;
import ma.khaoulabaadi.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}/debit")
    public Account debit(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.debit(id, amount);
    }

    @PutMapping("/{id}/credit")
    public Account credit(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.credit(id, amount);
    }
}