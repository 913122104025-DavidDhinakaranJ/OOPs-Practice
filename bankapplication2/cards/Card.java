package com.mycompany.bankapplication2.cards;
import java.util.Random;

public abstract class Card {
    private static final Random rand = new Random();
    private final String cardNo;
    private final int pin;

    public Card(int pin) {
        this.cardNo = rand.nextLong(10000000, 100000000) + "";
        this.pin = pin;
    }
    
    public String getCardNo() {
        return cardNo;
    }
    
    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }
}