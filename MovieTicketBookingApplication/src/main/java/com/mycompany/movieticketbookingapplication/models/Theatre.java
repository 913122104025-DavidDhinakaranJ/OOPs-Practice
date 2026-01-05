package com.mycompany.movieticketbookingapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Theatre {

    private static final AtomicLong idCounter = new AtomicLong();
    private final AtomicLong hallIdCounter;
    
    private final String theatreId;
    private final String name;
    private final String address;
    private final List<CinemaHall> halls;
    
    public Theatre(String name, String address) {
        this.theatreId = "T" + idCounter.incrementAndGet();
        this.name = name;
        this.address = address;
        this.halls = new ArrayList<>();
        this.hallIdCounter = new AtomicLong();
    }
    
    public String getTheatreId() {
        return theatreId;
    }
    
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<CinemaHall> getHalls() {
        return new ArrayList<>(halls);
    }

    public void addHall(String cinemaHallName) {
        this.halls.add(new CinemaHall(this.theatreId + "H" + hallIdCounter.incrementAndGet(), cinemaHallName));
    }
    
    public void removeHall(CinemaHall cinemaHall) {
        halls.remove(cinemaHall);
    }
}