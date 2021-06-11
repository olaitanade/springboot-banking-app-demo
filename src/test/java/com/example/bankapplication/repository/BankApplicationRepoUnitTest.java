package com.example.bankapplication.repository;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.service.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BankApplicationRepoUnitTest {

    @Test
    void showReturnAccountByNumber(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        assertNotNull(bankApplicationRepo.getAccountByNumber("1234567893"));
    }



}