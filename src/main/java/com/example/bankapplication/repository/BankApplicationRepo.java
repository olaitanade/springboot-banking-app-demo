package com.example.bankapplication.repository;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.model.type.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BankApplicationRepo {
    private List<Account> accounts = new ArrayList<Account>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Autowired
    private PasswordUtil passwordUtil;

    public void initialize(){
        Account customer1 = new Account();
        customer1.setAccountName("John Elon");
        customer1.setAccountNumber("1234567893");
        customer1.setAccountPassword(passwordUtil.hashPassword("123456"));
        customer1.setBalance(4000.0);
        accounts.add(customer1);

        Account customer2 = new Account();
        customer2.setAccountName("Mercy Johnson");
        customer2.setAccountNumber("1231345654");
        customer2.setAccountPassword(passwordUtil.hashPassword("uiopty"));
        customer2.setBalance(3000.0);
        accounts.add(customer2);

        Account customer3 = new Account();
        customer3.setAccountName("Peter Soul");
        customer3.setAccountNumber("0192837465");
        customer3.setAccountPassword(passwordUtil.hashPassword("qwerty"));
        customer3.setBalance(2500.0);
        accounts.add(customer3);

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setNarration("Savings");
        transaction.setAmount(2500.0);
        transaction.setAccountNumber("0192837465");
        transaction.setAccountBalance(2500.0);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transactions.add(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionDate(new Date());
        transaction1.setTransactionType(TransactionType.DEPOSIT);
        transaction1.setNarration("Savings");
        transaction1.setAmount(3000.0);
        transaction1.setAccountNumber("1231345654");
        transaction1.setAccountBalance(3000.0);
        transaction1.setTransactionId(UUID.randomUUID().toString());
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionDate(new Date());
        transaction2.setTransactionType(TransactionType.DEPOSIT);
        transaction2.setNarration("Savings");
        transaction2.setAmount(4000.0);
        transaction2.setAccountNumber("1234567893");
        transaction2.setAccountBalance(4000.0);
        transaction2.setTransactionId(UUID.randomUUID().toString());
        transactions.add(transaction2);
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public Account getAccountByNumber(String accountNumber){
        return accounts.stream().filter(x -> x.getAccountNumber().equals(accountNumber)).findAny().orElse(null);
    }

    public List<Transaction> getTransactionByNumber(String accountNumber){
        return transactions.stream().filter(x -> x.getAccountNumber().equals(accountNumber)).collect(Collectors.toList());
    }

    public boolean insertTransaction(Transaction transaction) {
        transactions.add(transaction);
        return true;
    }

    public boolean updateAccount(Account account) {
        int index = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(account.getAccountNumber())) {
                index = i;
                break;
            }
        }
        accounts.set(index,account);
        return true;
    }

    public String insertAccount(Account account){
        for (int i = 0; i < accounts.size(); i++) {
            Account tempAccount = accounts.get(i);
            if (tempAccount.getAccountNumber().equals(account.getAccountNumber())) {
                return "Account Number already exist";
            }else if(tempAccount.getAccountName().equals(account.getAccountName())){
                return "Account Name already exist";
            }
        }
        accounts.add(account);
        return null;
    }
}
