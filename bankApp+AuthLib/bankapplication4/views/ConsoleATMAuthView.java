package com.mycompany.bankapplication4.views;

import com.mycompany.bankapplication4.controllers.ICardAuthController;
import com.mycompany.bankapplication4.models.cards.Card;
import java.util.Scanner;

public class ConsoleATMAuthView implements IATMAuthView {
    
    private final Scanner scanner;
    private final ICardAuthController cardAuthController;
    
    public ConsoleATMAuthView(ICardAuthController cardAuthController) {
        scanner = new Scanner(System.in);
        this.cardAuthController = cardAuthController;
    }
    
    @Override
    public Card handleATMAuth() {
        String cardNo = getCardNumber();
        int pin = getPin();
        
        try {
            Card card = cardAuthController.handleCardAuth(cardNo, pin);
            displayMessage("Using Card...");
            return card;
            
        } catch (Exception e) {
            displayError(e.getMessage());
        }
        return null;
    }
    
    private String getCardNumber() {
        System.out.print("Enter card number: ");
        return scanner.next();
    }

    private int getPin() {
        System.out.print("Enter pin: ");
        return scanner.nextInt();
    }
    
    private void displayMessage(String message) {
        System.out.println("Success: " + message);
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}