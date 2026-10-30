package ma.khaoulabaadi.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@FeignClient(name = "account-service", fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {

    @GetMapping("/accounts/{id}")
    AccountDTO getAccount(@PathVariable UUID id);

    @PutMapping("/accounts/{id}/debit")
    void debit(@PathVariable UUID id, @RequestParam double amount);

    @PutMapping("/accounts/{id}/credit")
    void credit(@PathVariable UUID id, @RequestParam double amount);
}