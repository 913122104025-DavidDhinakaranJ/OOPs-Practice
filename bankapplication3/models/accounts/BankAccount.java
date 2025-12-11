package com.mycompany.bankapplication3.models.accounts;

import com.mycompany.bankapplication3.enums.AccountType;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BankAccount {
    private static final AtomicLong accCounter = new AtomicLong(10000);
    
    private final String accNo;
    private final String userId;
    protected double balance;
    
    public BankAccount(double initialAmount, String userId) {
        this.accNo = "ACC" + accCounter.incrementAndGet();
        this.balance = initialAmount;
        this.userId = userId;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public abstract boolean deduct(double amount);
    
    public abstract AccountType getAccountType();
    
}