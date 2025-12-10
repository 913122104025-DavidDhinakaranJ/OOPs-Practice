package com.mycompany.bankapplication3.models.cards;
import com.mycompany.bankapplication3.models.accounts.BankAccount;

public class DebitCard extends Card {
    private final BankAccount account;

    public DebitCard(BankAccount account, int pin) {
        super(pin);
        this.account = account;
    }
    
    public BankAccount getAccount() {
        return account;
    }
    
    @Override
    public boolean use(double amount) {
        return account.deduct(amount);
    }
    
    @Override
    public void pay(double amount) {
        account.deposit(amount);
    }

    @Override
    public double getBalance() {
        return account.getBalance();
    }
}