package com.mycompany.bankapplication3.models.cards;
import com.mycompany.bankapplication3.models.User;

public class CreditCard extends Card {
    private final User user;
    private static final double CREDIT_LIMIT = 5000;
    private double usedAmount;
    
    public CreditCard(int pin, User user) {
        super(pin);
        this.user = user;
        this.usedAmount = 0;
    }
    
    @Override
    public boolean use(double amount) {
        if(usedAmount + amount <= CREDIT_LIMIT) {
            usedAmount += amount;
            return true;
        }
        return false;
    }
    
    @Override
    public void pay(double amount) {
        usedAmount -= amount;
        if(usedAmount < 0) {
            usedAmount = 0;
        }
    }

    @Override
    public double getBalance() {
        return CREDIT_LIMIT - usedAmount;
    }
}