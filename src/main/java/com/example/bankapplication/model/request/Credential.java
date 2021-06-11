package com.example.bankapplication.model.request;

import javax.validation.constraints.NotBlank;

public class Credential {
    @NotBlank(message = "accountNumber is mandatory")
    private String accountNumber;
    @NotBlank(message = "accountPassword is mandatory")
    private String accountPassword;

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
}
