package com.mycompany.movieticketbookingapplication.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Show {
    private static final AtomicLong idCounter = new AtomicLong(10000);
    
    private final String showId;
    private final Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final Theatre theatre;
    private final CinemaHall cinemaHall;
    private final List<ShowSeat> showSeats;
    
    public Show(Movie movie, CinemaHall cinemaHall, Theatre theatre) {
        this.showId = "SHOW" + idCounter.incrementAndGet();
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.theatre = theatre;
        
        this.showSeats = new ArrayList<>();
        cinemaHall.getSeats().forEach(seat -> {
            showSeats.add(new ShowSeat(this.showId + seat.getSeatId(), seat, this));
        });
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public Theatre getTheatre() {
        return theatre;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }
}