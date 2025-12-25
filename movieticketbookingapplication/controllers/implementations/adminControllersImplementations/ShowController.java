package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.models.CinemaHall;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import com.mycompany.movieticketbookingapplication.repositories.IShowRepository;
import com.mycompany.movieticketbookingapplication.repositories.ITheatreRepository;
import java.time.LocalDateTime;
import java.util.List;

public class ShowController implements IShowController {
    private final IShowRepository showRepository;
    private final IMovieRepository movieRepository;
    private final ITheatreRepository theatreRepository;
    
    public ShowController(IShowRepository showRepository, IMovieRepository movieRepository, ITheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    @Override
    public void addShow(Movie movie, CinemaHall cinemaHall, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime, double basePrice) {
        Show show = new Show(movie, cinemaHall, theatre, basePrice);
        
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        
        showRepository.addShow(show);
    }
    
    @Override
    public void updateShow(Show show, LocalDateTime startTime, LocalDateTime endTime) {
        show.setStartTime(startTime);
        show.setEndTime(endTime);
    }
    
    @Override
    public void deleteShow(Show show) {
        showRepository.deleteShow(show);
    }
    
    @Override
    public List<Show> getAllShows() {
        return showRepository.getAllShows();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.getAllTheatres();
    }

    @Override
    public List<CinemaHall> getCinemaHalls(Theatre theatre) {
        return theatre.getHalls();
    }
}