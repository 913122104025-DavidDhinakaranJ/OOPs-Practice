package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.ShowController;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.MovieMenuOption;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.List;

public class ConsoleMovieView {
    private final ConsoleInputUtil inputReader;
    private final IMovieController movieController;
    private final ApplicationContext appContext;

    private boolean running;

    public ConsoleMovieView(IMovieController movieController) {
        inputReader = new ConsoleInputUtil();
        this.movieController = movieController;
        this.appContext = ApplicationContext.getInstance();
    }

    public void runMovieView() {
        running = true;
        while(running) {
            MovieMenuOption choice = getMovieMenuOption();
            switch(choice) {
                case VIEW_DETAILS -> handleViewDetails();
                case VIEW_SHOWS -> handleViewShows();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }

    private MovieMenuOption getMovieMenuOption() {
        System.out.println("1. View Movie Details");
        System.out.println("2. View Movie Shows");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> MovieMenuOption.VIEW_DETAILS;
            case 2 -> MovieMenuOption.VIEW_SHOWS;
            case 0 -> MovieMenuOption.EXIT;
            default -> MovieMenuOption.INVALID;
        };
    }

    private void handleViewDetails() {
        System.out.println("Title: " + movieController.getTitle());
        System.out.println("Genres: " + String.join(", ", movieController.getGenres()));
        System.out.println("Duration: " + movieController.getDuration() + " Minutes");
        System.out.println("Languages: " + String.join(", ", movieController.getLanguages()));
        System.out.println("Rating: " + movieController.getRating());
        System.out.println("Release Date: " + movieController.getReleaseDate().toString());
    }

    private void handleViewShows() {
        List<Show> shows = movieController.getShows();
        
        if(shows == null) {
            System.out.print("No Shows Found.");
            return;
        }
        displayShows(shows);
        handleShowListSelection(shows);
    }

    private void handleExit() {
        running = false;
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void handleShowListSelection(List<Show> shows) {
        Show show = getShowChoice(shows);
        if(show == null) return;
        
        ConsoleShowView showView = new ConsoleShowView(new ShowController(show, appContext.getBookingRepository()));
        showView.runShowView();
    }
    
    private void displayShows(List<Show> shows) {
        for(int i = 0;i < shows.size();i++) {
            Show show = shows.get(i);
            System.out.println(i + 1 + ". Theatre: " + show.getTheatre().getName() 
                    + "\tCinema Hall: " + show.getCinemaHall().getName()
                    + "\tTiming: " + show.getStartTime() + " to " + show.getEndTime());
        }
    }
    
    private Show getShowChoice(List<Show> shows) {
        System.out.println("0. Back");
        int showChoice = inputReader.readInt("Enter Show Choice: ");
        
        if(showChoice == 0) {
            return null;
        }
        
        while(true) {
            if(showChoice < 1 || showChoice > shows.size()) {
                displayError("Invalid Show Choice.");
                continue;
            }

            return shows.get(showChoice - 1);
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}