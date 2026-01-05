package com.mycompany.authlib.util;

import java.util.Scanner;

public class ConsoleAuthInputUtil {
    private final Scanner scanner;
    
    public ConsoleAuthInputUtil() {
        scanner = new Scanner(System.in);
    }
    
    public String getUsername() {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.next().trim();

            if (username.matches("^[a-zA-Z0-9]{4,20}$")) return username;
            
            displayError("Username must be 4–20 characters, letters and digits only.");
        }
    }
    
    public String getPassword() {
        int MIN_LENGTH = 8;
        int MAX_LENGTH = 64;
        String SPECIALS = "@$!%*?&";
        while (true) {
            System.out.print("Enter password: ");
            String password = scanner.next().trim();

            if (password == null || password.isEmpty()) {
                displayError("Password cannot be empty.");
            } else if (password.length() < MIN_LENGTH) {
                displayError("Password must be at least 8 characters long.");
            } else if (password.length() > MAX_LENGTH) {
                displayError("Password must not exceed 64 characters.");
            } else if (!password.chars().anyMatch(Character::isLowerCase)) {
                displayError("Password must contain at least one lowercase letter.");
            } else if (!password.chars().anyMatch(Character::isUpperCase)) {
                displayError("Password must contain at least one uppercase letter.");
            } else if (!password.chars().anyMatch(Character::isDigit)) {
                displayError("Password must contain at least one digit.");
            } else if (password.chars().noneMatch(c -> SPECIALS.indexOf(c) >= 0)) {
                displayError("Password must contain at least one special character (" + SPECIALS + ").");
            } else if (password.chars().anyMatch(Character::isWhitespace)) {
                displayError("Password must not contain spaces.");
            } else {
                return password;
            }
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}