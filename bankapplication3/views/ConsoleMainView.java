package com.mycompany.bankapplication3.views;

import com.mycompany.bankapplication3.enums.MainOption;
import java.util.Scanner;

public class ConsoleMainView implements IMainView {
    Scanner scanner = new Scanner(System.in);

    @Override
    public MainOption getMainChoice() {
        System.out.println("1. New User Registration");
        System.out.println("2. Net Banking Login");
        System.out.println("3. Use Card");
        System.out.println("0. Exit");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> MainOption.REGISTER;
            case 2 -> MainOption.LOGIN;
            case 3 -> MainOption.CARD;
            case 0 -> MainOption.EXIT;
            default -> MainOption.INVALID;
        };     
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    @Override
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}