package com.mycompany.bankapplication2.cards;

public class CreditCard extends Card {
    private static final double CREDIT_LIMIT = 5000;
    private double usedAmount;
    
    public CreditCard(int pin) {
        super(pin);
        this.usedAmount = 0;
    }
    
    @Override
    public boolean use(double amount) {
        if(usedAmount + amount <= CREDIT_LIMIT) {
            usedAmount += amount;
            return true;
        }
        System.out.println("You don't have enough credit remaining to use.");
        return false;
    }
    
    @Override
    public void pay(double amount) {
        usedAmount -= amount;
        if(usedAmount < 0) {
            System.out.println("You have paid USD " + -usedAmount + " more than you have used.");
            usedAmount = 0;
        }
    }

    @Override
    public void displayBalance() {
        System.out.println("Balance Credit: USD " + getBalanceCredit());
    }
    
    private double getBalanceCredit() {
        return CREDIT_LIMIT - usedAmount;
    }
}