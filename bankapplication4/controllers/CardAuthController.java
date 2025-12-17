package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.cards.Card;
import com.mycompany.bankapplication4.repositories.ICardRepository;

public class CardAuthController implements ICardAuthController {
    private final ICardRepository cards;

    public CardAuthController(ICardRepository cardRepository) {
        this.cards = cardRepository;
    }

    @Override
    public Card handleCardAuth(String cardNo, int pin) throws Exception {
        Card card = cards.findCard(cardNo);
        
        if(card == null) {
            throw new Exception("Invalid Card Number");
        } else if(!card.validatePin(pin)) {
            throw new Exception("Incorrect PIN");
        }
        
        return card;
    }    
}