package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.repositories.ITheatreRepository;
import java.util.List;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageTheatreController;

public class ManageTheatreController implements IManageTheatreController {
    private final ITheatreRepository theatreRepository;
    
    public ManageTheatreController(ITheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public void addTheatre(String theatreName, String theatreAddress) {
        theatreRepository.addTheatre(new Theatre(theatreName, theatreAddress));
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