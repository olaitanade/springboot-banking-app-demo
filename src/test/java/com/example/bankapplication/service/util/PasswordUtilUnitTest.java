package com.example.bankapplication.service.util;

import com.example.bankapplication.repository.BankApplicationRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilUnitTest {
    @Test
    void shouldNotBeNullOnHashPassword(){
        PasswordUtil passwordUtil = new PasswordUtil();

        assertNotNull(passwordUtil.hashPassword("123456"));
    }

    @Test
    void shouldBeTrueOnHashSamePassword(){
        PasswordUtil passwordUtil = new PasswordUtil();

        assertTrue(passwordUtil.hashPassword("123456").equals(passwordUtil.hashPassword("123456")));
    }

    @Test
    void shouldBeFalseOnHashDifferentPassword(){
        PasswordUtil passwordUtil = new PasswordUtil();

        assertFalse(passwordUtil.hashPassword("123456").equals(passwordUtil.hashPassword("153456")));
    }
}