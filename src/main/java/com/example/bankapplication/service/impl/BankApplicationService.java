package com.example.bankapplication.service.impl;

import com.example.bankapplication.repository.BankApplicationRepo;
import com.example.bankapplication.service.IBankApplicationService;
import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.model.request.AccountCreation;
import com.example.bankapplication.model.request.Credential;
import com.example.bankapplication.model.request.Deposit;
import com.example.bankapplication.model.request.Withdraw;
import com.example.bankapplication.model.response.AccountInfo;
import com.example.bankapplication.model.response.DefaultResponse;
import com.example.bankapplication.model.type.TransactionType;
import com.example.bankapplication.service.util.BankApplicationUtil;
import com.example.bankapplication.service.util.PasswordUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BankApplicationService implements IBankApplicationService {

    @Autowired
    private BankApplicationRepo bankApplicationRepo;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private BankApplicationUtil bankApplicationUtil;

    @Override
    public void initializeData() {
        bankApplicationRepo.initialize();
    }

    @Override
    public ResponseEntity<?> getAccountInfo(@NotNull Credential credential) {
        Account account = bankApplicationRepo.getAccountByNumber(credential.getAccountNumber());
        DefaultResponse authStatus = bankApplicationUtil.authorize(account,passwordUtil.hashPassword(credential.getAccountPassword()));

        if(!authStatus.isSuccess()){
            return new ResponseEntity<>(authStatus, HttpStatus.resolve(authStatus.getResponseCode()));
        }else{
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAccount(account);
            return new ResponseEntity<>(accountInfo, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getAccountStatement(@NotNull Credential credential) {
        Account account = bankApplicationRepo.getAccountByNumber(credential.getAccountNumber());
        DefaultResponse authStatus = bankApplicationUtil.authorize(account,passwordUtil.hashPassword(credential.getAccountPassword()));

        if(!authStatus.isSuccess()){
            return new ResponseEntity<>(authStatus, HttpStatus.resolve(authStatus.getResponseCode()));
        }else{
            List<Transaction> transactionList = bankApplicationRepo.getTransactionByNumber(account.getAccountNumber());
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> deposit(@NotNull Deposit deposit) {
        if(deposit.getAmount()>1000000.0 || deposit.getAmount()<1.0){
            return new ResponseEntity<>(new DefaultResponse(400,false,"Account password incorrect"), HttpStatus.resolve(400));
        }else{
            Account account = bankApplicationRepo.getAccountByNumber(deposit.getAccountNumber());
            DefaultResponse defaultResponse = bankApplicationUtil.authorize(account);

            if(!defaultResponse.isSuccess()){
                return new ResponseEntity<>(defaultResponse, HttpStatus.resolve(defaultResponse.getResponseCode()));
            }else{
                Transaction transaction = new Transaction();
                transaction.setTransactionType(TransactionType.DEPOSIT);
                transaction.setTransactionDate(new Date());
                transaction.setAccountBalance(account.getBalance()+deposit.getAmount());
                transaction.setAmount(deposit.getAmount());
                transaction.setAccountNumber(account.getAccountNumber());
                transaction.setNarration("Deposit");
                transaction.setTransactionId(UUID.randomUUID().toString());
                bankApplicationRepo.insertTransaction(transaction);

                account.setBalance(transaction.getAccountBalance());
                bankApplicationRepo.updateAccount(account);
                return new ResponseEntity<>(new DefaultResponse(), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<?> withdraw(@NotNull Withdraw withdraw) {
        if(withdraw.getWithdrawnAmount()<1.0){
            return new ResponseEntity<>(new DefaultResponse(400,false,"Amount cannot be less than 1.0 Naira"), HttpStatus.resolve(400));
        } else {
            Account account = bankApplicationRepo.getAccountByNumber(withdraw.getAccountNumber());
            DefaultResponse defaultResponse = bankApplicationUtil.authorize(account,passwordUtil.hashPassword(withdraw.getAccountPassword()));

            if(!defaultResponse.isSuccess()){
                return new ResponseEntity<>(defaultResponse, HttpStatus.resolve(defaultResponse.getResponseCode()));
            }else{
                if(bankApplicationUtil.canWithDraw(withdraw,account)){
                    Transaction transaction = new Transaction();
                    transaction.setTransactionType(TransactionType.WITHDRAWAL);
                    transaction.setTransactionDate(new Date());
                    transaction.setAccountBalance(account.getBalance()-withdraw.getWithdrawnAmount());
                    transaction.setAmount(withdraw.getWithdrawnAmount());
                    transaction.setAccountNumber(account.getAccountNumber());
                    transaction.setTransactionId(UUID.randomUUID().toString());
                    transaction.setNarration("Withdrawal");
                    bankApplicationRepo.insertTransaction(transaction);

                    account.setBalance(transaction.getAccountBalance());
                    bankApplicationRepo.updateAccount(account);

                    return new ResponseEntity<>(new DefaultResponse(), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(new DefaultResponse(400,false,"You need at least 500 Naira to be left in you account after withdrawal"), HttpStatus.resolve(400));
                }
            }
        }
    }


    @Override
    public ResponseEntity<?> createAccount(@NotNull AccountCreation accountCreation) {
        Account newAccount = new Account();
        newAccount.setAccountName(accountCreation.getAccountName());
        newAccount.setAccountPassword(passwordUtil.hashPassword(accountCreation.getAccountPassword()));
        newAccount.setAccountNumber(bankApplicationUtil.generateAccountNumber());
        newAccount.setBalance(0.0);
        String statusMessage = bankApplicationRepo.insertAccount(newAccount);
        if(statusMessage!=null){
            return new ResponseEntity<>(new DefaultResponse(400,false,statusMessage), HttpStatus.resolve(400));
        }else{
            return new ResponseEntity<>(new DefaultResponse(String.format("Account successfully created with account number %s",newAccount.getAccountNumber())), HttpStatus.OK);
        }
    }
}
