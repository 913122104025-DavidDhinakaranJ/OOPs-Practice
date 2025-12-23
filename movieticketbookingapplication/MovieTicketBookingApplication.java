package com.mycompany.movieticketbookingapplication;

import com.mycompany.movieticketbookingapplication.controllers.implementations.MainController;
import com.mycompany.movieticketbookingapplication.repositories.InMemoryRepository;
import com.mycompany.movieticketbookingapplication.views.ConsoleMainView;

public class MovieTicketBookingApplication {
    public static void main(String[] args) {
        final InMemoryRepository inMemoryRepository = InMemoryRepository.getInMemoryRepository();  
        ConsoleMainView mainView = new ConsoleMainView(new MainController(inMemoryRepository));
        
        mainView.runMainView();
    }
}