package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.exceptions.IncorrectPinException;
import com.mycompany.bankapplication4.exceptions.InvalidCardNumberException;
import com.mycompany.bankapplication4.models.cards.Card;

public interface ICardAuthController {
    public Card handleCardAuth(String cardNo, int pin) throws InvalidCardNumberException, IncorrectPinException;
}