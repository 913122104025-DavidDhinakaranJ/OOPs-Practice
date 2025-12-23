package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IAdminController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminMenuOption;
import java.util.Scanner;

public class ConsoleAdminView {
    private final Scanner scanner;
    private final IAdminController adminController;
    
    private boolean login;
    
    public ConsoleAdminView(IAdminController adminController) {
        this.scanner = new Scanner(System.in);
        this.adminController = adminController;
    }
    
    public void runAdminView() {
        login = true;
        while(login) {
            AdminMenuOption choice = getAdminMenuOption();
            switch(choice) {
                case MANAGE_THEATRES -> handleManageTheatres();
                case MANAGE_SHOWS -> handleManageShows();
                case MANAGE_MOVIES -> handleManageMovies();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminMenuOption getAdminMenuOption() {
        System.out.println("1. Manage Theatres");
        System.out.println("2. Manage Shows");
        System.out.println("3. Manage Movies");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> AdminMenuOption.MANAGE_THEATRES;
            case 2 -> AdminMenuOption.MANAGE_SHOWS;
            case 3 -> AdminMenuOption.MANAGE_MOVIES;
            case 0 -> AdminMenuOption.LOGOUT;
            default -> AdminMenuOption.INVALID;
        };
    }
    
    private void handleManageTheatres() {
        adminController.handleManageTheatres();
    }

    private void handleManageShows() {
        adminController.handleManageShows();
    }

    private void handleManageMovies() {
        adminController.handleManageMovies();
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