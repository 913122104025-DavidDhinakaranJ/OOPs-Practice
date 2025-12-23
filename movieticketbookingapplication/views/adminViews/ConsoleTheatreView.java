package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ITheatreController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.util.List;
import java.util.Scanner;

public class ConsoleTheatreView {
    private final Scanner scanner;
    private final ITheatreController theatreController;
    
    private boolean running;
    
    public ConsoleTheatreView(ITheatreController theatreController) {
        this.scanner = new Scanner(System.in);
        this.theatreController = theatreController;
    }
    
    public void runTheatreView() {
        running = true;
        
        while(running) {
            AdminOperationsOption choice = getAdminOperationsOption();
            switch(choice) {
                case ADD -> handleAddTheatre();
                case UPDATE -> handleUpdateTheatre();
                case DELETE -> handleDeleteTheatre();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminOperationsOption getAdminOperationsOption() {
        System.out.println("1. Add Theatre");
        System.out.println("2. Update Theatre");
        System.out.println("3. Remove Theatre");
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
    
    private void handleAddTheatre() {
        String theatreName = getTheatreName();
        String theatreAddress = getTheatreAddress();
        
        theatreController.addTheatre(theatreName, theatreAddress);
    }

    private void handleUpdateTheatre() {
        Theatre theatre = getTheatre();
        if(theatre == null) return;
        
        theatreController.updateTheatre(theatre);
    }

    private void handleDeleteTheatre() {
        Theatre theatre = getTheatre();
        if(theatre == null) return;
        
        theatreController.deleteTheatre(theatre);
    }
    
    private void handleExit() {
        running = false;
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private String getTheatreName() {
        System.out.print("Enter Theatre Name: ");
        return scanner.next();
    }
    
    private String getTheatreAddress() {
        System.out.print("Enter Theatre Address: ");
        return scanner.next();
    }
    
    private Theatre getTheatre() {
        List<Theatre> theatreList = theatreController.getAllTheatres();
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
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}