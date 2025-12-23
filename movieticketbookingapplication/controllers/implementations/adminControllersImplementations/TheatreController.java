package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ITheatreController;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.repositories.ITheatreRepository;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleCinemaHallView;
import java.util.List;

public class TheatreController implements ITheatreController {
    private final ITheatreRepository theatreRepository;
    
    public TheatreController(ITheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public void addTheatre(String theatreName, String theatreAddress) {
        theatreRepository.addTheatre(new Theatre(theatreName, theatreAddress));
    }
    
    @Override
    public void updateTheatre(Theatre theatre) {
        ConsoleCinemaHallView cinemaHallView = new ConsoleCinemaHallView(new CinemaHallController(theatre));
        cinemaHallView.runCinemaHallView();
    }
    
    @Override
    public void deleteTheatre(Theatre theatre) {
        theatreRepository.deleteTheatre(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.getAllTheatres();
    }
}