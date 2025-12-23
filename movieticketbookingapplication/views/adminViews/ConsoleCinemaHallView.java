package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ICinemaHallController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import java.util.List;
import java.util.Scanner;

public class ConsoleCinemaHallView {
    private final Scanner scanner;
    private final ICinemaHallController cinemaHallController;
    
    private boolean running;
    
    public ConsoleCinemaHallView(ICinemaHallController cinemaHallController) {
        this.scanner = new Scanner(System.in);
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
        System.out.println("2. Update Cinema Hall");
        System.out.println("3. Remove Cinema Hall");
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

    private void handleAddCinemaHall() {
        String cinemaHallName = getCinemaHallName();
        
        cinemaHallController.addCinemaHall(cinemaHallName);
    }

    private void handleUpdateCinemaHall() {
        CinemaHall cinemaHall = getCinemaHall();
        if(cinemaHall == null) return;
    }

    private void handleDeleteCinemaHall() {
        CinemaHall cinemaHall = getCinemaHall();
        if(cinemaHall == null) return;
        
        //cinemaHallController.deleteCinemaHall(cinemaHall);
    }

    private void handleExit() {
        running = false;
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private String getCinemaHallName() {
        System.out.print("Enter Cinema Hall Name: ");
        return scanner.next();
    }
    
    private CinemaHall getCinemaHall() {
        List<CinemaHall> cinemaHallList = cinemaHallController.getCinemaHalls();
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
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}