package com.mycompany.bankapplication2.bankaccounts;
import com.mycompany.bankapplication2.cards.*;
import java.util.Random;

public abstract class BankAccount {
    private static final Random rand = new Random();
    
    private final String accNo;
    protected double balance;
    private DebitCard debitCard;
    
    public BankAccount(double initialAmount) {
        this.accNo = rand.nextLong(100000, 1000000) + "";
        this.balance = initialAmount;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }
    
    public Card getDebitCard(int pin) {
        if(debitCard == null) {
            debitCard = new DebitCard(this, pin);
        }
        return debitCard;
    }
    
    public abstract void deposit(double amount);
    
    public abstract boolean deduct(double amount);
    
    public abstract boolean hasMinimumBalance(double amount);
}