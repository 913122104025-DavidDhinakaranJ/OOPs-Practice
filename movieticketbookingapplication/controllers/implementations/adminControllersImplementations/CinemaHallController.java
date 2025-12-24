package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.ICinemaHallController;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.util.List;

public class CinemaHallController implements ICinemaHallController {
    private final Theatre theatre;
    
    public CinemaHallController(Theatre theatre) {
        this.theatre = theatre;
    }

    @Override
    public void addCinemaHall(String cinemaHallName) {
        theatre.addHall(cinemaHallName);
    }

    @Override
    public List<CinemaHall> getCinemaHalls() {
        return theatre.getHalls();
    }

    @Override
    public void deleteCinemaHall(CinemaHall cinemaHall) {
        theatre.removeHall(cinemaHall);
    }
}