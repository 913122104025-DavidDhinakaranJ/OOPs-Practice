package com.mycompany.movieticketbookingapplication.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleInputUtil {
    private final Scanner scanner;
    
    public ConsoleInputUtil() {
        scanner = new Scanner(System.in);
    }
    
    public int readInt(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.next());
                scanner.nextLine();
                return input;
            } catch (NumberFormatException e) {
                displayError("Please enter a valid integer.");
            }
        }
    }
    
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.print(prompt + " (dd-MM-yyyy): ");
            String input = scanner.next().trim();

            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                displayError("Invalid date format or value.");
            }
        }
    }
    
    public LocalDateTime readDateTime(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        while (true) {
            System.out.print(prompt + " (dd-MM-yyyy HH:mm): ");
            String input = scanner.nextLine().trim();

            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                displayError("Invalid date-time.");
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
    
    public boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String input = scanner.next().trim().toLowerCase();

            switch (input) {
                case "yes" -> {
                    return true;
                }
                case "no" -> {
                    return false;
                }
                default -> System.out.println("Error: Please enter yes/no or y/n.");
            }
        }
    }
    
    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        return dateTime.format(formatter);
    }

    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}