package com.example.bankapplication.service.util;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class PasswordUtil {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt;

    public PasswordUtil(){
        salt = secureRandom.generateSeed(12);
    }

    public String hashPassword(String password){
        try{
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, 10, 512);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();
            String base64Hash = Base64.getMimeEncoder().encodeToString(hash);
            return base64Hash;
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            return null;
        }catch (InvalidKeySpecException invalidKeySpecException){
            return null;
        }
    }
}
