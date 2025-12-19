package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.enums.menuOptions.CustomerMenuOption;
import java.util.Scanner;

public class ConsoleCustomerView {
    private final Scanner scanner;
    
    private boolean login;
    
    public ConsoleCustomerView() {
        this.scanner = new Scanner(System.in);
    }
    
    public void runCustomerView() {
        login = true;
        while(login) {
            CustomerMenuOption choice = getCustomerMenuOption();
            switch(choice) {
                case SEARCH_MOVIE -> handleSearchMovie();
                case VIEW_BOOKING_HISTORY -> handleViewBookHistory();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private CustomerMenuOption getCustomerMenuOption() {
        System.out.println("1. Search Movie");
        System.out.println("2. View Booking History");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> CustomerMenuOption.SEARCH_MOVIE;
            case 2 -> CustomerMenuOption.VIEW_BOOKING_HISTORY;
            case 0 -> CustomerMenuOption.LOGOUT;
            default -> CustomerMenuOption.INVALID;
        };
    }

    private void handleSearchMovie() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleViewBookHistory() {
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