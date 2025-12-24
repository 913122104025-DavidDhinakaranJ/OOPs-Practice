package com.mycompany.bankapplication4.views;

import com.mycompany.bankapplication4.controllers.ICardAuthController;
import com.mycompany.bankapplication4.exceptions.IncorrectPinException;
import com.mycompany.bankapplication4.exceptions.CardNotFoundException;
import com.mycompany.bankapplication4.models.cards.Card;
import com.mycompany.bankapplication4.utils.ConsoleIOUtil;

public class ConsoleATMAuthView implements IATMAuthView {
    
    private final ConsoleIOUtil ioHandler;
    private final ICardAuthController cardAuthController;
    
    public ConsoleATMAuthView(ICardAuthController cardAuthController) {
        ioHandler = new ConsoleIOUtil();
        this.cardAuthController = cardAuthController;
    }
    
    @Override
    public Card handleATMAuth() {
        String cardNo = getCardNumber();
        int pin = getPin();
        
        try {
            Card card = cardAuthController.handleCardAuth(cardNo, pin);
            ioHandler.displaySuccess("Using Card...");
            return card;
            
        } catch (CardNotFoundException e) {
            ioHandler.displayError("Card Not Found");
        } catch (IncorrectPinException e) {
            ioHandler.displayError("Incorrect PIN");
        }
        return null;
    }
    
    private String getCardNumber() {
        return ioHandler.readCardNumber("Enter card number: ");
    }

    private int getPin() {
        return ioHandler.readPin("Enter pin: ");
    }
}