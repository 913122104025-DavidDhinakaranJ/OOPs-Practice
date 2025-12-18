package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.exceptions.IncorrectPinException;
import com.mycompany.bankapplication4.exceptions.InvalidCardNumberException;
import com.mycompany.bankapplication4.models.cards.Card;
import com.mycompany.bankapplication4.repositories.ICardRepository;

public class CardAuthController implements ICardAuthController {
    private final ICardRepository cards;

    public CardAuthController(ICardRepository cardRepository) {
        this.cards = cardRepository;
    }

    @Override
    public Card handleCardAuth(String cardNo, int pin) throws InvalidCardNumberException, IncorrectPinException {
        Card card = cards.findCard(cardNo);
        
        if(card == null) {
            throw new InvalidCardNumberException();
        } else if(!card.validatePin(pin)) {
            throw new IncorrectPinException();
        }
        
        return card;
    }    
}