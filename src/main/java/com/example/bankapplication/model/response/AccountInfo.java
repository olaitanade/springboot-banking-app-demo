package com.example.bankapplication.model.response;


import com.example.bankapplication.model.Account;

public class AccountInfo extends DefaultResponse{
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
