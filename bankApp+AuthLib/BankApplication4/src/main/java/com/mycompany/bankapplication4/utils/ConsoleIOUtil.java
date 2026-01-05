package com.mycompany.bankapplication4.utils;

import java.util.Scanner;

public class ConsoleIOUtil {
    private final Scanner scanner;
    
    public ConsoleIOUtil() {
        scanner = new Scanner(System.in);
    }
    
    public int readInt(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                displayError("Please enter a valid integer.");
            }
        }
    }
    
    public double readAmount(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if(amount > 0) return amount;
                displayError("Amount must be greater than Zero.");
            } catch (NumberFormatException e) {
                displayError("Please enter a valid amount.");
            }
        }
    }
    
    public String readAccountNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String accNo = scanner.nextLine().trim();

            if (accNo.matches("^ACC[0-9]{5,}$")) return accNo;
            displayError("Invalid account number.");
        }
    }
    
    public String readCardNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String cardNo = scanner.nextLine().trim();

            if (cardNo.matches("^[1-9][0-9]{7,}$")) return cardNo;
            
            displayError("Invalid Card Number.");
        }
    }
    
    public int readPin(String prompt) {
        while (true) {
            int pin = readInt(prompt);
            if (pin >= 1000 && pin <= 9999) return pin;
            System.out.println("Error: PIN must be a 4-digit number.");
        }
    }
    
    public void displaySuccess(String message) {
        System.out.println("Success: " + message);
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}