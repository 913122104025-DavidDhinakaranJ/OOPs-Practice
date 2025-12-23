package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.SeatType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CinemaHall {
    private final AtomicLong seatIdCounter = new AtomicLong();
    
    private final String hallId;
    private final String name;
    private int totalSeats;
    private final List<Seat> seats;
    
    public CinemaHall(String hallId, String name) {
        this.hallId = hallId;
        this.name = name;
        this.totalSeats = 0;
        this.seats = new ArrayList<>();
    }

    public String getHallId() {
        return hallId;
    }
    
    public String getName() {
        return name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
    
    public void addSeat(String row, int seatNumber, SeatType type) {
        this.seats.add(new Seat(hallId + "S" + seatIdCounter.incrementAndGet(), row, seatNumber, type));
        totalSeats++;
    }
    
    public void removeSeat(String row, int seatNumber) {
        for(Seat seat : seats) {
            if(seat.getRow().equals(row) && seat.getSeatNumber() == seatNumber) {
                seats.remove(seat);
                return;
            }
        }
    }
}