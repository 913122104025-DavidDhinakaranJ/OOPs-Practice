package com.mycompany.bankapplication3.repositories;

import com.mycompany.bankapplication3.models.cards.Card;

public interface ICardRepository {
    void saveCard(Card card);
    Card findCard(String cardNo);
}