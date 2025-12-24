package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.SeatController;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ICinemaHallController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.List;

public class ConsoleCinemaHallView {
    private final ConsoleInputUtil inputReader;
    private final ICinemaHallController cinemaHallController;
    
    private boolean running;
    
    public ConsoleCinemaHallView(ICinemaHallController cinemaHallController) {
        inputReader = new ConsoleInputUtil();
        this.cinemaHallController = cinemaHallController;
    }
    
    public void runCinemaHallView() {
        running = true;
        
        while(running) {
            AdminOperationsOption choice = getAdminOperationsOption();
            switch(choice) {
                case ADD -> handleAddCinemaHall();
                case UPDATE -> handleUpdateCinemaHall();
                case DELETE -> handleDeleteCinemaHall();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminOperationsOption getAdminOperationsOption() {
        System.out.println("1. Add Cinema Hall");
        System.out.println("2. Handle Seats");
        System.out.println("3. Remove Cinema Hall");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> AdminOperationsOption.ADD;
            case 2 -> AdminOperationsOption.UPDATE;
            case 3 -> AdminOperationsOption.DELETE;
            case 0 -> AdminOperationsOption.EXIT;
            default -> AdminOperationsOption.INVALID;
        };
    }

    private void handleAddCinemaHall() {
        String cinemaHallName = getCinemaHallName();
        cinemaHallController.addCinemaHall(cinemaHallName);
    }

    private void handleUpdateCinemaHall() {
        CinemaHall cinemaHall = getCinemaHall();
        if(cinemaHall == null) return;
        
        ConsoleSeatView seatView = new ConsoleSeatView(new SeatController(cinemaHall));
    }

    private void handleDeleteCinemaHall() {
        CinemaHall cinemaHall = getCinemaHall();
        if(cinemaHall == null) return;
        
        cinemaHallController.deleteCinemaHall(cinemaHall);
    }

    private void handleExit() {
        running = false;
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private String getCinemaHallName() {
        return inputReader.readString("Enter Cinema Hall Name: ");
    }
    
    private CinemaHall getCinemaHall() {
        List<CinemaHall> cinemaHallList = cinemaHallController.getCinemaHalls();
        if(cinemaHallList.isEmpty()) {
            System.out.println("No Cinema Hall found.");
            return null;
        }
        
        for(int i = 0;i < cinemaHallList.size();i++) {
            System.out.println(i + 1 + ". " + cinemaHallList.get(i).getName());
        }
        
        while(true) {
            int cinemaHallChoice = inputReader.readInt("Enter CinemaHall Choice: ");

            if(cinemaHallChoice < 1 || cinemaHallChoice > cinemaHallList.size()) {
                displayError("Invalid CinemaHall Choice.");
                continue;
            }

            return cinemaHallList.get(cinemaHallChoice - 1);
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}