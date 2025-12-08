package com.mycompany.bankapplication2.bankaccounts;

public class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000;
    private static final double MINIMUM_BALANCE = 200;
    
    private int overdraftUsed;
    public CurrentAccount(double initialAmount) {
        super(initialAmount);
        this.overdraftUsed = 0;
    }
    
    @Override
    public void deposit(double amount) {
        double amountAfterPayOverdraft = payOverdraft(amount);
        if(amountAfterPayOverdraft > 0) {
            balance += amountAfterPayOverdraft;
        }
    }

    @Override
    public boolean deduct(double amount) {
        if(hasMinimumBalance(balance - amount)) {
            balance -= amount;
            return true;
        } else if(useOverdraft(amount - (balance - MINIMUM_BALANCE))) {
            balance = MINIMUM_BALANCE;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean hasMinimumBalance(double amount) {
        return amount >= MINIMUM_BALANCE;
    }
    
    private double payOverdraft(double amount) {
        if(amount > overdraftUsed) {
            overdraftUsed = 0;
            return amount - overdraftUsed;
        } else {
            overdraftUsed -= amount;
            return 0;
        }
    }

    public boolean useOverdraft(double amount) {
        if(overdraftUsed + amount > OVERDRAFT_LIMIT) {
            System.out.println("Overdraft limit of USD " + OVERDRAFT_LIMIT + " exceeded.");
            return false;
        } else {
            overdraftUsed += amount;
            return true;
        }
    }
    
    public double getBalanceOverdraft() {
        return OVERDRAFT_LIMIT - overdraftUsed;
    }
}