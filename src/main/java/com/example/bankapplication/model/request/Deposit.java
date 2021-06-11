package com.example.bankapplication.model.request;

import javax.validation.constraints.*;

public class Deposit {
    @NotBlank(message = "accountNumber is mandatory")
    private String accountNumber;
    @NotNull(message = "amount required")
    @DecimalMax(value = "1000000.0", message = "1,000,000.0 is the maximum")
    @DecimalMin(value = "1.0", message = "1.0 is the minimum")
    private Double amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
