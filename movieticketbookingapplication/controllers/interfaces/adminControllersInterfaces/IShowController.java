package com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces;

import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import java.time.LocalDateTime;
import java.util.List;

public interface IShowController {
    void addShow(Movie movie, CinemaHall cinemaHall, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime);
    void updateShow(Show show, LocalDateTime startTime, LocalDateTime endTime);
    void deleteShow(Show show);
    
    List<Show> getAllShows();
    List<Movie> getAllMovies();
    List<Theatre> getAllTheatres();
    List<CinemaHall> getCinemaHalls(Theatre theatre);
}