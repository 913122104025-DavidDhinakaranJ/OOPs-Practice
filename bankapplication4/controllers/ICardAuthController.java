package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.cards.Card;

public interface ICardAuthController {
    public Card handleCardAuth(String cardNo, int pin) throws Exception;
}