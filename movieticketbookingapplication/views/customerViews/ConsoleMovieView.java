package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.MovieMenuOption;
import com.mycompany.movieticketbookingapplication.models.Show;
import java.util.List;
import java.util.Scanner;

public class ConsoleMovieView {
    private final Scanner scanner;
    private final IMovieController movieController;

    private boolean running;

    public ConsoleMovieView(IMovieController movieController) {
        this.scanner = new Scanner(System.in);
        this.movieController = movieController;
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
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
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
        displayShows(shows);
    }

    private void handleExit() {
        running = false;
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayShows(List<Show> shows) {
        for(int i = 0;i < shows.size();i++) {
            Show show = shows.get(i);
            System.out.println(i + 1 + ". Theatre: " + show.getTheatre().getName() 
                    + "Cinema Hall: " + show.getCinemaHall().getName()
                    + "Timing: " + show.getStartTime() + " to " + show.getEndTime());
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}