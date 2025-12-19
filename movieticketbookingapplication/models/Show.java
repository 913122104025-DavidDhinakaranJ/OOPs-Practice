package com.mycompany.movieticketbookingapplication.models;

import java.util.Date;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private Date startDate;
    private Date endDate;
    private final CinemaHall cinemaHall;
    private final List<ShowSeat> showSeats;
    
    public Show(String showId, Movie movie, CinemaHall cinemaHall, List<ShowSeat> showSeats) {
        this.showId = showId;
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.showSeats = showSeats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }
}