package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.time.LocalDateTime;
import java.util.List;

public class ConsoleShowView {
    private final ConsoleInputUtil inputReader;
    private final IShowController showController;
    
    private boolean running;
    
    public ConsoleShowView(IShowController showController) {
        inputReader = new ConsoleInputUtil();
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
        System.out.println("2. Update Show Timing");
        System.out.println("3. Remove Show");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> AdminOperationsOption.ADD;
            case 2 -> AdminOperationsOption.UPDATE;
            case 3 -> AdminOperationsOption.DELETE;
            case 0 -> AdminOperationsOption.EXIT;
            default -> AdminOperationsOption.INVALID;
        };
    }
    
    private void handleAddShow() {
        Theatre theatre = getTheatre();
        if(theatre == null) return;
        
        CinemaHall cinemaHall = getCinemaHall(theatre);
        if(cinemaHall == null) return;
        
        Movie movie = getMovie();
        if(movie == null) return;
        
        LocalDateTime[] showTime = getShowTime();
        
        showController.addShow(movie, cinemaHall, theatre, showTime[0], showTime[1]);
    }

    private void handleUpdateShow() {
        Show show = getShow();
        if(show == null) return;
        
        LocalDateTime[] showTime = getShowTime();
        
        showController.updateShow(show, showTime[0], showTime[1]);
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
    
    private Show getShow() {
        List<Show> showList = showController.getAllShows();
        for(int i = 0;i < showList.size();i++) {
            Show show = showList.get(i);
            System.out.println(i + 1 + ". Theatre: " + show.getTheatre().getName()
                    + " Cinema Hall: " + show.getCinemaHall().getName()
                    + " Movie: " + show.getMovie().getTitle()
                    + " Time: " + show.getStartTime() + "-" + show.getEndTime());
        }
        
        while(true) {
            int showChoice = inputReader.readInt("Enter Show Choice: ");

            if(showChoice < 1 || showChoice > showList.size()) {
                displayError("Invalid Show Choice.");
                continue;
            }

            return showList.get(showChoice - 1);
        }
    }
    
    private Movie getMovie() {
        List<Movie> movieList = showController.getAllMovies();
        if(movieList.isEmpty()) {
            System.out.println("No Movie found.");
            return null;
        }
        
        for(int i = 0;i < movieList.size();i++) {
            System.out.println(i + 1 + ". " + movieList.get(i).getTitle());
        }
        
        while(true) {
            int movieChoice = inputReader.readInt("Enter Movie Choice: ");
        
            if(movieChoice < 1 || movieChoice > movieList.size()) {
                displayError("Invalid Movie Choice.");
                continue;
            }

            return movieList.get(movieChoice - 1);
        }
    }
    
    private Theatre getTheatre() {
        List<Theatre> theatreList = showController.getAllTheatres();
        if(theatreList.isEmpty()) {
            System.out.println("No Theatre found.");
            return null;
        }
        
        for(int i = 0;i < theatreList.size();i++) {
            System.out.println(i + 1 + ". " + theatreList.get(i).getName());
        }
        
        while(true) {
            int theatreChoice = inputReader.readInt("Enter Theatre Choice: ");

            if(theatreChoice < 1 || theatreChoice > theatreList.size()) {
                displayError("Invalid Theatre Choice.");
                continue;
            }

            return theatreList.get(theatreChoice - 1);
        }
    }

    private CinemaHall getCinemaHall(Theatre theatre) {
        List<CinemaHall> cinemaHallList = showController.getCinemaHalls(theatre);
        if(cinemaHallList.isEmpty()) {
            System.out.println("No Cinema Hall found.");
            return null;
        }
        
        for(int i = 0;i < cinemaHallList.size();i++) {
            System.out.println(i + 1 + ". " + cinemaHallList.get(i).getName());
        }
        
        while(true) {
            int cinemaHallChoice = inputReader.readInt("Enter Cinema Hall Choice: ");

            if(cinemaHallChoice < 1 || cinemaHallChoice > cinemaHallList.size()) {
                displayError("Invalid CinemaHall Choice.");
                continue;
            }

            return cinemaHallList.get(cinemaHallChoice - 1);
        }
    }
    
    private LocalDateTime[] getShowTime() {
        while(true) {
            LocalDateTime startTime = inputReader.readDateTime("Enter Start Time: ");
            LocalDateTime endTime = inputReader.readDateTime("Enter End Time: ");
            
            if(startTime.isBefore(endTime)) return new LocalDateTime[] {startTime, endTime};
            displayError("Start Time must be the time before End Time");
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}