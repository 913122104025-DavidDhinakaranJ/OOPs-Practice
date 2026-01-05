package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ManageAdminController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ManageCustomerController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ManageShowController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ManageTheatreController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ManageMovieController;
import com.mycompany.movieticketbookingapplication.enums.Privilege;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminMenuOption;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;

public class ConsoleAdminView {
    private final ConsoleInputUtil inputReader;
    private final ApplicationContext appContext;
    private final Admin admin;
    
    private boolean login;
    
    public ConsoleAdminView() {
        inputReader = new ConsoleInputUtil();
        appContext = ApplicationContext.getInstance();
        admin = (Admin) appContext.getSessionContext().getCurrentUser().orElse(null);
    }
    
    public void runAdminView() {
        login = true;
        while(login) {
            AdminMenuOption choice = getAdminMenuOption();
            switch(choice) {
                case MANAGE_THEATRES -> handleManageTheatres();
                case MANAGE_SHOWS -> handleManageShows();
                case MANAGE_MOVIES -> handleManageMovies();
                case MANAGE_CUSTOMERS -> handleManageCustomers();
                case MANAGE_ADMINS -> handleManageAdmins();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminMenuOption getAdminMenuOption() {
        System.out.println("1. Manage Theatres");
        System.out.println("2. Manage Shows");
        System.out.println("3. Manage Movies");
        System.out.println("4. Manage Customers");
        System.out.println("5. Manage Admins");
        System.out.println("0. Logout");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> AdminMenuOption.MANAGE_THEATRES;
            case 2 -> AdminMenuOption.MANAGE_SHOWS;
            case 3 -> AdminMenuOption.MANAGE_MOVIES;
            case 4 -> AdminMenuOption.MANAGE_CUSTOMERS;
            case 5 -> AdminMenuOption.MANAGE_ADMINS;
            case 0 -> AdminMenuOption.LOGOUT;
            default -> AdminMenuOption.INVALID;
        };
    }
    
    private void handleManageTheatres() {
        if(!admin.hasPrivilege(Privilege.THEATRE)) {
            displayError("You don't have privilege to manage Theatres.");
            return;
        }
        ConsoleManageTheatreView theatreView = new ConsoleManageTheatreView(new ManageTheatreController(appContext.getTheatreRepository()));
        theatreView.runTheatreView();
    }

    private void handleManageShows() {
        if(!admin.hasPrivilege(Privilege.SHOW)) {
            displayError("You don't have privilege to manage Shows.");
            return;
        }
        ConsoleManageShowView showView = new ConsoleManageShowView(new ManageShowController(appContext.getShowRepository(), appContext.getMovieRepository(), appContext.getTheatreRepository()));
        showView.runShowView();
    }

    private void handleManageMovies() {
        if(!admin.hasPrivilege(Privilege.MOVIE)) {
            displayError("You don't have privilege to manage Movies.");
            return;
        }
        ConsoleManageMovieView movieView = new ConsoleManageMovieView(new ManageMovieController(appContext.getMovieRepository()));
        movieView.runMovieView();
    }
    
    private void handleManageCustomers() {
        if(!admin.hasPrivilege(Privilege.CUSTOMER)) {
            displayError("You don't have privilege to manage Customers.");
            return;
        }
        ConsoleManageCustomerView customerView = new ConsoleManageCustomerView(new ManageCustomerController(appContext.getUserRepository()));
        customerView.runManageCustomerView();
    }

    private void handleManageAdmins() {
        if(!admin.hasPrivilege(Privilege.ADMIN)) {
            displayError("You don't have privilege to manage Admins.");
            return;
        }
        ConsoleManageAdminView adminView = new ConsoleManageAdminView(new ManageAdminController(appContext.getUserRepository()));
        adminView.runManageAdminView();
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