package com.example.bankapplication.service.util;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.request.Withdraw;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankApplicationUtilUnitTest {
    @Test
    void shouldReturn_AccountDoesNotExist_OnAuthorize(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();

        assertEquals("Account does not exist",bankApplicationUtil.authorize(null,"123456").getMessage());
        assertEquals("Account does not exist",bankApplicationUtil.authorize(null).getMessage());
    }

    @Test
    void shouldReturn_AccountPasswordIncorrect_OnAuthorize(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();
        PasswordUtil passwordUtil = new PasswordUtil();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("378347683476");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        assertEquals("Account password incorrect",bankApplicationUtil.authorize(customer1,"123456").getMessage());
    }

    @Test
    void shouldReturn_Successful_OnAuthorize(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();
        PasswordUtil passwordUtil = new PasswordUtil();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("378347683476");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);

        assertEquals("Successful",bankApplicationUtil.authorize(customer1).getMessage());
        assertEquals("Successful",bankApplicationUtil.authorize(customer1,passwordUtil.hashPassword("123456")).getMessage());
    }

    @Test
    void shouldReturn_True_OnCanWithdraw(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();
        PasswordUtil passwordUtil = new PasswordUtil();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("378347683476");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        Withdraw withdraw = new Withdraw();
        withdraw.setWithdrawnAmount(100.0);
        withdraw.setAccountNumber("378347683476");
        withdraw.setAccountPassword(passwordUtil.hashPassword("123456"));

        assertTrue(bankApplicationUtil.canWithDraw(withdraw,customer1));
    }

    @Test
    void shouldReturn_False_OnCanWithdraw(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();
        PasswordUtil passwordUtil = new PasswordUtil();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("378347683476");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        Withdraw withdraw = new Withdraw();
        withdraw.setWithdrawnAmount(5000.0);
        withdraw.setAccountNumber("378347683476");
        withdraw.setAccountPassword(passwordUtil.hashPassword("123456"));

        assertFalse(bankApplicationUtil.canWithDraw(withdraw,customer1));
    }

    @Test
    void shouldReturn_10LengthString_OnGenerateAccountNumber(){
        BankApplicationUtil bankApplicationUtil = new BankApplicationUtil();

        assertTrue(bankApplicationUtil.generateAccountNumber().length()==10);
    }
}