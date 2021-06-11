package com.example.bankapplication.repository;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.service.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BankApplicationRepoUnitTest {

    @Test
    void shouldReturnAccountByNumber(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        assertNotNull(bankApplicationRepo.getAccountByNumber("1234567893"));
    }

    @Test
    void shouldNotReturnAccountByNumber(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        assertNull(bankApplicationRepo.getAccountByNumber("12345674834"));
    }

    @Test
    void shouldReturnTransactionsByNumber(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        assertNotNull(bankApplicationRepo.getTransactionByNumber("1234567893"));
    }

    @Test
    void shouldNotReturnTransactionsByNumber(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        assertTrue(bankApplicationRepo.getTransactionByNumber("12345674834").isEmpty());
    }

    @Test
    void shouldReturnTrueOnUpdateAccount(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        when(passwordUtil.hashPassword("123456")).thenReturn("qwertyuiop");
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("1234567893");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);

        assertTrue(bankApplicationRepo.updateAccount(customer1));
    }

    @Test
    void shouldReturnFalseOnUpdateAccount(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        when(passwordUtil.hashPassword("123456")).thenReturn("qwertyuiop");
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("378347683476");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);

        assertFalse(bankApplicationRepo.updateAccount(customer1));
    }

    @Test
    void shouldReturnNullOnInsertAccount(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        Account customer1 = new Account();
        customer1.setAccountName("Bola Davids");
        customer1.setAccountNumber("456778901");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        assertNull(bankApplicationRepo.insertAccount(customer1));
    }

    @Test
    void shouldReturn_AccountNumberAlreadyExist_OnInsertAccount(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        Account customer1 = new Account();
        customer1.setAccountName("Bola Davids");
        customer1.setAccountNumber("1234567893");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        assertEquals("Account Number already exist",bankApplicationRepo.insertAccount(customer1));
    }

    @Test
    void shouldReturn_AccountNameAlreadyExist_OnInsertAccount(){
        PasswordUtil passwordUtil = Mockito.mock(PasswordUtil.class);
        BankApplicationRepo bankApplicationRepo = new BankApplicationRepo(passwordUtil);
        bankApplicationRepo.initialize();
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("4536637263772");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        assertEquals("Account Name already exist",bankApplicationRepo.insertAccount(customer1));
    }

}