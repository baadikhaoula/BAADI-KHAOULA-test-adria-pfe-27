package ma.khaoulabaadi.transactionservice.client;

import java.util.UUID;

public class AccountDTO {
    private UUID id;
    private double balance;
    private String currency;
    private String status;

    // Getters et setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}