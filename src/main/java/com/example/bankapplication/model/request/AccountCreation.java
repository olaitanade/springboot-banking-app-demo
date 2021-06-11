package com.example.bankapplication.model.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

public class AccountCreation {
    @NotBlank(message = "AccountName is mandatory")
    private String accountName;
    @NotBlank(message = "AccountPassword is mandatory")
    private String accountPassword;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
