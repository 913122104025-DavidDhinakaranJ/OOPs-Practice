package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.enums.SeatType;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Seat;
import java.util.List;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageSeatController;

public class ManageSeatController implements IManageSeatController {
    private final CinemaHall cinemaHall;

    public ManageSeatController(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    @Override
    public void addSeat(String row, int seatNumber, SeatType type) {
        cinemaHall.addSeat(row, seatNumber, type);
    }

    @Override
    public List<Seat> getSeats() {
        return cinemaHall.getSeats();
    }

    @Override
    public void updateSeat(Seat seat, SeatType type) {
        seat.changeSeatType(type);
    }

    @Override
    public void deleteSeat(Seat seat) {
        cinemaHall.removeSeat(seat);
    }
    
}