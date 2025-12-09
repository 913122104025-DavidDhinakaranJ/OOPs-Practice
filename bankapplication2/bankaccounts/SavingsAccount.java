package com.mycompany.bankapplication2.bankaccounts;

public class SavingsAccount extends BankAccount {
    private static final double MINIMUM_BALANCE = 100;
    
    public SavingsAccount(double initialAmount) {
        super(initialAmount);
    }
    
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    
    @Override
    public boolean deduct(double amount) {
        if(hasMinimumBalance(balance - amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasMinimumBalance(double amount) {
        if(amount < MINIMUM_BALANCE) {
            System.out.println("Minimum Balance of USD " + MINIMUM_BALANCE + " has to be maintained.");
            return false;
        }
        return true;
    }

    @Override
    public void displayBalance() {
        System.out.println("Balance Amount: USD " + getBalance());
    }
}