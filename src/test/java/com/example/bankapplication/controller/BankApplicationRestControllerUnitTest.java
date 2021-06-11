package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.AccountCreation;
import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.model.request.Deposit;
import com.example.bankapplication.model.request.Withdraw;
import com.example.bankapplication.repository.BankApplicationRepo;
import com.example.bankapplication.service.impl.BankApplicationService;
import com.example.bankapplication.service.util.BankApplicationUtil;
import com.example.bankapplication.service.util.PasswordUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BankApplicationRestControllerUnitTest {

    private static BankApplicationRepo bankApplicationRepo;
    private static PasswordUtil passwordUtil;
    private static BankApplicationUtil bankApplicationUtil;
    private static BankApplicationService bankApplicationService;

    @BeforeAll
    public static void init() {
        passwordUtil = new PasswordUtil();
        bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        bankApplicationUtil = new BankApplicationUtil();
        bankApplicationService = new BankApplicationService(bankApplicationRepo,passwordUtil,bankApplicationUtil);
    }

    @Test
    void shouldReturn_200_OnAccountInfo(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("123456");
        assertEquals(200,bankApplicationRestController.accountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnAccountInfo(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("123456893");
        credential.setAccountPassword("123456");
        assertEquals(400,bankApplicationRestController.accountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnAccountInfo(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("12356");
        assertEquals(401,bankApplicationRestController.accountInfo(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnAccountStatement(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("123456");
        assertEquals(200,bankApplicationRestController.accountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnAccountStatement(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("123456893");
        credential.setAccountPassword("123456");
        assertEquals(400,bankApplicationRestController.accountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnAccountStatement(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Credential credential = new Credential();
        credential.setAccountNumber("1234567893");
        credential.setAccountPassword("12356");
        assertEquals(401,bankApplicationRestController.accountStatement(credential).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnDeposit(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Deposit deposit = new Deposit();
        deposit.setAccountNumber("1234567893");
        deposit.setAmount(1000.0);
        assertEquals(200,bankApplicationRestController.deposit(deposit).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnDeposit(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Deposit deposit = new Deposit();
        deposit.setAccountNumber("1234507893");
        deposit.setAmount(1000.0);
        assertEquals(400,bankApplicationRestController.deposit(deposit).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnWithdraw(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("1234567893");
        withdraw.setAccountPassword("123456");
        withdraw.setWithdrawnAmount(1000.0);
        assertEquals(200,bankApplicationRestController.withdrawal(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnWithdraw(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("123457893");
        withdraw.setAccountPassword("123456");
        withdraw.setWithdrawnAmount(1000.0);
        assertEquals(400,bankApplicationRestController.withdrawal(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_401_OnWithdraw(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountNumber("1234567893");
        withdraw.setAccountPassword("12456");
        withdraw.setWithdrawnAmount(1000.0);
        assertEquals(401,bankApplicationRestController.withdrawal(withdraw).getStatusCodeValue());
    }

    @Test
    void shouldReturn_200_OnCreateAccount(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setAccountName("Elon Musk");
        accountCreation.setAccountPassword("123456");
        assertEquals(200,bankApplicationRestController.createAccount(accountCreation).getStatusCodeValue());
    }

    @Test
    void shouldReturn_400_OnCreateAccount(){
        BankApplicationRestController bankApplicationRestController = new BankApplicationRestController(bankApplicationService);
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setAccountName("John Elon");
        accountCreation.setAccountPassword("123456");
        assertEquals(400,bankApplicationRestController.createAccount(accountCreation).getStatusCodeValue());
    }
}