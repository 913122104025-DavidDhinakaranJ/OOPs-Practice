package com.mycompany.bankapplication3.views;

import java.util.Scanner;

public class ConsoleAuthView implements IAuthView {
    Scanner scanner = new Scanner(System.in);
    
    @Override
    public String getUsername() {
        System.out.print("Enter username: ");
        return scanner.next();    
    }

    @Override
    public String getPassword() {
        System.out.print("Enter password: ");
        return scanner.next();
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