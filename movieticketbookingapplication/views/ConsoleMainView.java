package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.controllers.MainController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.MainMenuOption;
import java.util.Scanner;

public class ConsoleMainView {
    private final Scanner scanner;
    private final MainController mainController;
    
    private boolean running;

    public ConsoleMainView(MainController mainController) {
        this.scanner = new Scanner(System.in);
        this.mainController = mainController;
    }
    
    public void runMainView() {
        running = true;
        while(running) {
            MainMenuOption choice = getMainMenuOption();
            switch(choice) {
                case REGISTER -> handleRegistration();
                case LOGIN -> handleLogin();
                case SEARCH -> handleSearch();
                case EXIT -> stopMainView();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private MainMenuOption getMainMenuOption() {
        System.out.println("1. New User Registration");
        System.out.println("2. Log in");
        System.out.println("3. Search Movie");
        System.out.println("0. Exit");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> MainMenuOption.REGISTER;
            case 2 -> MainMenuOption.LOGIN;
            case 3 -> MainMenuOption.SEARCH;
            case 0 -> MainMenuOption.EXIT;
            default -> MainMenuOption.INVALID;
        };
    }

    private void handleRegistration() {
        mainController.handleRegistration();
    }

    private void handleLogin() {
        mainController.handleLogin();
    }

    private void handleSearch() {
        mainController.handleSearch();
    }
    
    public void stopMainView() {
        running = false;
        System.out.println("Exiting the app.");
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}