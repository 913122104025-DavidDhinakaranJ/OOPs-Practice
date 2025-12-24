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
                return Integer.parseInt(scanner.next());
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

    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}