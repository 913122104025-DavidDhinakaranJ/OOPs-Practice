package com.mycompany.movieticketbookingapplication.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final Theatre theatre;
    private final CinemaHall cinemaHall;
    private final List<ShowSeat> showSeats;
    
    public Show(String showId, Movie movie, CinemaHall cinemaHall, List<ShowSeat> showSeats, Theatre theatre) {
        this.showId = showId;
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.showSeats = showSeats;
        this.theatre = theatre;
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