package com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces;

import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.util.List;

public interface ITheatreController {
    void addTheatre(String theatreName, String theatreAddress);
    void updateTheatre(Theatre theatre);
    void deleteTheatre(Theatre theatre);
    
    List<Theatre> getAllTheatres();
}