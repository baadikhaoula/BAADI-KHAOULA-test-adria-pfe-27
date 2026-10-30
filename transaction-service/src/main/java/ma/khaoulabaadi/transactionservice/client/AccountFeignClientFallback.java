package ma.khaoulabaadi.transactionservice.client;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AccountFeignClientFallback implements AccountFeignClient {

    @Override
    public AccountDTO getAccount(UUID id) {
        AccountDTO dto = new AccountDTO();
        dto.setStatus("BLOCKED"); // Par défaut, on considère le compte bloqué en cas de panne
        return dto;
    }

    @Override
    public void debit(UUID id, double amount) {
        // Ne rien faire en cas de panne (tolérance aux pannes)
    }

    @Override
    public void credit(UUID id, double amount) {
        // Ne rien faire en cas de panne
    }
}