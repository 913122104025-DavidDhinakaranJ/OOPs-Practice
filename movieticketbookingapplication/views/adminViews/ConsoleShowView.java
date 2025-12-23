package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleShowView {
    private final Scanner scanner;
    private final IShowController showController;
    
    private boolean running;
    
    public ConsoleShowView(IShowController showController) {
        this.scanner = new Scanner(System.in);
        this.showController = showController;
    }
    
    public void runShowView() {
        running = true;
        
        while(running) {
            AdminOperationsOption choice = getAdminOperationsOption();
            switch(choice) {
                case ADD -> handleAddShow();
                case UPDATE -> handleUpdateShow();
                case DELETE -> handleDeleteShow();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminOperationsOption getAdminOperationsOption() {
        System.out.println("1. Add Show");
        System.out.println("2. Update Show");
        System.out.println("3. Remove Show");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> AdminOperationsOption.ADD;
            case 2 -> AdminOperationsOption.UPDATE;
            case 3 -> AdminOperationsOption.DELETE;
            case 0 -> AdminOperationsOption.EXIT;
            default -> AdminOperationsOption.INVALID;
        };
    }
    
    private void handleAddShow() {
        Theatre theatre = getTheatre();
        CinemaHall cinemaHall = getCinemaHall(theatre);
        Movie movie = getMovie();
        
        System.out.println("Start Time: ");
        LocalDateTime startTime = getTime();
        
        System.out.println("End Time: ");
        LocalDateTime endTime = getTime();
        
        showController.addShow(movie, cinemaHall, theatre, startTime, endTime);
    }

    private void handleUpdateShow() {
        Show show = getShow();
        if(show == null) return;
        
        System.out.println("Updated Start Time: ");
        LocalDateTime startTime = getTime();
        
        System.out.println("Updated End Time: ");
        LocalDateTime endTime = getTime();
        
        showController.updateShow(show, startTime, endTime);
    }

    private void handleDeleteShow() {
        Show show = getShow();
        if(show == null) return;
        
        showController.deleteShow(show);
    }
    
    private void handleExit() {
        running = false;
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
    
    private Show getShow() {
        List<Show> showList = showController.getAllShows();
        for(int i = 0;i < showList.size();i++) {
            Show show = showList.get(i);
            System.out.println(i + 1 + ". Theatre: " + show.getTheatre().getName()
                    + " Cinema Hall: " + show.getCinemaHall().getName()
                    + " Movie: " + show.getMovie().getTitle()
                    + " Time: " + show.getStartTime() + "-" + show.getEndTime());
        }
        
        System.out.print("Enter Movie Choice: ");
        int movieChoice = scanner.nextInt();
        
        if(movieChoice < 1 || movieChoice > showList.size()) {
            displayError("Invalid Movie Choice.");
            return null;
        }
        
        return showList.get(movieChoice - 1);
    }
    
    private Movie getMovie() {
        List<Movie> movieList = showController.getAllMovies();
        for(int i = 0;i < movieList.size();i++) {
            System.out.println(i + 1 + ". " + movieList.get(i).getTitle());
        }
        
        System.out.print("Enter Movie Choice: ");
        int movieChoice = scanner.nextInt();
        
        if(movieChoice < 1 || movieChoice > movieList.size()) {
            displayError("Invalid Movie Choice.");
            return null;
        }
        
        return movieList.get(movieChoice - 1);
    }
    
    private Theatre getTheatre() {
        List<Theatre> theatreList = showController.getAllTheatres();
        for(int i = 0;i < theatreList.size();i++) {
            System.out.println(i + 1 + ". " + theatreList.get(i).getName());
        }
        
        System.out.print("Enter Theatre Choice: ");
        int theatreChoice = scanner.nextInt();
        
        if(theatreChoice < 1 || theatreChoice > theatreList.size()) {
            displayError("Invalid Theatre Choice.");
            return null;
        }
        
        return theatreList.get(theatreChoice - 1);
    }

    private CinemaHall getCinemaHall(Theatre theatre) {
        List<CinemaHall> cinemaHallList = showController.getCinemaHalls(theatre);
        for(int i = 0;i < cinemaHallList.size();i++) {
            System.out.println(i + 1 + ". " + cinemaHallList.get(i).getName());
        }
        
        System.out.print("Enter CinemaHall Choice: ");
        int cinemaHallChoice = scanner.nextInt();
        
        if(cinemaHallChoice < 1 || cinemaHallChoice > cinemaHallList.size()) {
            displayError("Invalid CinemaHall Choice.");
            return null;
        }
        
        return cinemaHallList.get(cinemaHallChoice - 1);
    }

    private LocalDateTime getTime() {
        try {
            System.out.print("Enter Date: ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Time: ");
            LocalTime time = LocalTime.parse(scanner.nextLine());
            return LocalDateTime.of(date, time);
        } catch(DateTimeParseException e) {
            displayError("Invalid Date Format.");
        }
        return null;
    }
}