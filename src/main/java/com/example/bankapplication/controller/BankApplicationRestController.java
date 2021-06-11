package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.service.IBankApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
@Validated
public class BankApplicationRestController {
    @Autowired
    private IBankApplicationService bankApplicationService;

    @RequestMapping(value = "/account_info", method = RequestMethod.POST)
    public ResponseEntity<?> accountInfo(@RequestBody Credential credential) {
        return bankApplicationService.getAccountInfo(credential);
    }
}
