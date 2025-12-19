package com.mycompany.movieticketbookingapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Theatre {
    private static final AtomicLong idCounter = new AtomicLong();
    private final AtomicLong hallIdCounter;
    
    private final String theatreId;
    private final String address;
    private final List<CinemaHall> halls;
    
    public Theatre(String address) {
        this.theatreId = "T" + idCounter.incrementAndGet();
        this.address = address;
        this.halls = new ArrayList<>();
        this.hallIdCounter = new AtomicLong();
    }
    
    public String getTheatreId() {
        return theatreId;
    }

    public String getAddress() {
        return address;
    }

    public List<CinemaHall> getHalls() {
        return halls;
    }

    public void addHall(String name) {
        this.halls.add(new CinemaHall(this.theatreId + "H" + hallIdCounter.incrementAndGet(), name));
    }
}