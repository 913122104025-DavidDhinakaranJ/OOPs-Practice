package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.util.List;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageCinemaHallController;

public class ManageCinemaHallController implements IManageCinemaHallController {
    private final Theatre theatre;
    
    public ManageCinemaHallController(Theatre theatre) {
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