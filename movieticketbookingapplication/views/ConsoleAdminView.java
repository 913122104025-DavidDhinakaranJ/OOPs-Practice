package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.enums.menuOptions.AdminMenuOption;
import java.util.Scanner;

public class ConsoleAdminView {
    private final Scanner scanner;
    
    private boolean login;
    
    public ConsoleAdminView() {
        this.scanner = new Scanner(System.in);
    }
    
    public void runAdminView() {
        login = true;
        while(login) {
            AdminMenuOption choice = getAdminMenuOption();
            switch(choice) {
                case MANAGE_THEATRES -> handleManageTheatres();
                case MANAGE_SHOWS -> handleManageShows();
                case MANAGE_MOVIES -> handleManageMovies();
                case MANAGE_USERS -> handleManageUsers();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminMenuOption getAdminMenuOption() {
        System.out.println("1. Manage Theatres");
        System.out.println("2. Manage Shows");
        System.out.println("3. Manage Movies");
        System.out.println("4. Manage Users");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> AdminMenuOption.MANAGE_THEATRES;
            case 2 -> AdminMenuOption.MANAGE_SHOWS;
            case 3 -> AdminMenuOption.MANAGE_MOVIES;
            case 4 -> AdminMenuOption.MANAGE_USERS;
            case 0 -> AdminMenuOption.LOGOUT;
            default -> AdminMenuOption.INVALID;
        };
    }
    
    private void handleManageTheatres() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleManageShows() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleManageMovies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleManageUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleLogout() {
        login = false;
        System.out.println("Logged out Successfully.");
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}