package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.AccountCreation;
import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.model.request.Deposit;
import com.example.bankapplication.model.request.Withdraw;
import com.example.bankapplication.service.IBankApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bank")
@Validated
public class BankApplicationRestController {
    @Autowired
    private IBankApplicationService bankApplicationService;

    @RequestMapping(value = "/account_info", method = RequestMethod.POST)
    public ResponseEntity<?> accountInfo(@Valid @RequestBody Credential credential) {
        return bankApplicationService.getAccountInfo(credential);
    }

    @RequestMapping(value = "/account_statement", method = RequestMethod.POST)
    public ResponseEntity<?> accountStatement(@Valid @RequestBody Credential credential) {
        return bankApplicationService.getAccountStatement(credential);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public ResponseEntity<?> deposit(@Valid @RequestBody Deposit deposit) {
        return bankApplicationService.deposit(deposit);
    }

    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public ResponseEntity<?> withdrawal(@Valid @RequestBody Withdraw withdraw) {
        return bankApplicationService.withdraw(withdraw);
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreation accountCreation) {
        return bankApplicationService.createAccount(accountCreation);
    }
}
