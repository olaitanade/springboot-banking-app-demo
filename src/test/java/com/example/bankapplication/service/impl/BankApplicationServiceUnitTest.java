package com.example.bankapplication.service.impl;

import com.example.bankapplication.model.request.AccountCreation;
import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.model.request.Deposit;
import com.example.bankapplication.model.request.Withdraw;
import com.example.bankapplication.repository.BankApplicationRepo;
import com.example.bankapplication.service.util.BankApplicationUtil;
import com.example.bankapplication.service.util.PasswordUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankApplicationServiceUnitTest {

    private static BankApplicationRepo bankApplicationRepo;
    private static PasswordUtil passwordUtil;
    private static BankApplicationUtil bankApplicationUtil;

    @BeforeAll
    public static void init() {
        passwordUtil = new PasswordUtil();
        bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        bankApplicationUtil = new BankApplicationUtil();
    }

    @Test
    void shouldReturn_200_OnGetAccountInfo(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("123456");

        assertEquals(200,bankApplicationService.getAccountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnGetAccountInfo(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("12356");

        assertEquals(401,bankApplicationService.getAccountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnGetAccountInfo(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("123457893");
        credential.setAccountPassword("123456");

        assertEquals(400,bankApplicationService.getAccountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnGetAccountStatement(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("123456");

        assertEquals(200,bankApplicationService.getAccountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnGetAccountStatement(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("12356");

        assertEquals(401,bankApplicationService.getAccountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnGetAccountStatement(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Credential credential = new Credential();
        credential.setAccountNumber("123457893");
        credential.setAccountPassword("123456");

        assertEquals(400,bankApplicationService.getAccountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnDeposit(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Deposit deposit = new Deposit();
        deposit.setAccountNumber("1234567893");
        deposit.setAmount(1000.0);

        assertEquals(200,bankApplicationService.deposit(deposit).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnDeposit(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Deposit deposit = new Deposit();
        deposit.setAccountNumber("1234507893");
        deposit.setAmount(1000.0);

        assertEquals(400,bankApplicationService.deposit(deposit).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnWithdraw(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("1234567893");
        withdraw.setAccountPassword("123456");
        withdraw.setWithdrawnAmount(1000.0);


        assertEquals(200,bankApplicationService.withdraw(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnWithdraw(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("1234567893");
        withdraw.setAccountPassword("12456");
        withdraw.setWithdrawnAmount(1000.0);


        assertEquals(401,bankApplicationService.withdraw(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnWithdraw(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("123457893");
        withdraw.setAccountPassword("123456");
        withdraw.setWithdrawnAmount(1000.0);


        assertEquals(400,bankApplicationService.withdraw(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnCreateAccount(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setAccountName("Elon Musk");
        accountCreation.setAccountPassword("123456");

        assertEquals(200,bankApplicationService.createAccount(accountCreation).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnCreateAccount(){
        BankApplicationService bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setAccountName("John Elon");
        accountCreation.setAccountPassword("123456");

        assertEquals(400,bankApplicationService.createAccount(accountCreation).getStatusCodeValue());
    }

}