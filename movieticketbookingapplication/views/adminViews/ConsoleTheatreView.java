package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.CinemaHallController;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ITheatreController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.List;

public class ConsoleTheatreView {
    private final ConsoleInputUtil inputReader;
    private final ITheatreController theatreController;
    
    private boolean running;
    
    public ConsoleTheatreView(ITheatreController theatreController) {
        inputReader = new ConsoleInputUtil();
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
        System.out.println("2. Handle Cinema Halls");
        System.out.println("3. Remove Theatre");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
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
        
        ConsoleCinemaHallView cinemaHallView = new ConsoleCinemaHallView(new CinemaHallController(theatre));
        cinemaHallView.runCinemaHallView();
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
        return inputReader.readString("Enter Theatre Name: ");
    }
    
    private String getTheatreAddress() {
        return inputReader.readString("Enter Theatre Address: ");
    }
    
    private Theatre getTheatre() {
        List<Theatre> theatreList = theatreController.getAllTheatres();
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
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}