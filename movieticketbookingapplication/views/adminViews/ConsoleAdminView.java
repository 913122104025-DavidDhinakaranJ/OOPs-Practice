package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.ShowController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.TheatreController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.MovieController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminMenuOption;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;

public class ConsoleAdminView {
    private final ConsoleInputUtil inputReader;
    private final ApplicationContext appContext;
    
    private boolean login;
    
    public ConsoleAdminView() {
        inputReader = new ConsoleInputUtil();
        appContext = ApplicationContext.getInstance();
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
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> AdminMenuOption.MANAGE_THEATRES;
            case 2 -> AdminMenuOption.MANAGE_SHOWS;
            case 3 -> AdminMenuOption.MANAGE_MOVIES;
            case 0 -> AdminMenuOption.LOGOUT;
            default -> AdminMenuOption.INVALID;
        };
    }
    
    private void handleManageTheatres() {
        ConsoleTheatreView theatreView = new ConsoleTheatreView(new TheatreController(appContext.getTheatreRepository()));
        theatreView.runTheatreView();
    }

    private void handleManageShows() {
        ConsoleShowView showView = new ConsoleShowView(new ShowController(appContext.getShowRepository(), appContext.getMovieRepository(), appContext.getTheatreRepository()));
        showView.runShowView();
    }

    private void handleManageMovies() {
        ConsoleMovieView movieView = new ConsoleMovieView(new MovieController(appContext.getMovieRepository()));
        movieView.runMovieView();
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