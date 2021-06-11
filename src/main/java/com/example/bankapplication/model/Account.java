package com.example.bankapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private String accountName;
    private String accountNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accountPassword;
    private Double balance;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
