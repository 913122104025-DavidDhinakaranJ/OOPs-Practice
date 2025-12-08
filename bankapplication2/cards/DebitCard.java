package com.mycompany.bankapplication2.cards;
import com.mycompany.bankapplication2.bankaccounts.*;

public class DebitCard extends Card {
    
    private final BankAccount account;

    public DebitCard(BankAccount account, int pin) {
        super(pin);
        this.account = account;
    }
    
    public BankAccount getAccount() {
        return account;
    }
}