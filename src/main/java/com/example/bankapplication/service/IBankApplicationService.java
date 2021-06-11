package com.example.bankapplication.service;

import com.example.bankapplication.model.request.AccountCreation;
import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.model.request.Deposit;
import com.example.bankapplication.model.request.Withdraw;
import org.springframework.http.ResponseEntity;

public interface IBankApplicationService {
    public abstract void initializeData();


    public abstract ResponseEntity<?> getAccountInfo(Credential credential);

    public abstract ResponseEntity<?> getAccountStatement(Credential credential);

    public abstract ResponseEntity<?> deposit (Deposit deposit);

    public abstract ResponseEntity<?> withdraw (Withdraw withdraw);

    public abstract ResponseEntity<?> createAccount(AccountCreation accountCreation);
}
