package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.repositories.IShowRepository;
import java.time.LocalDate;
import java.util.List;

public class MovieController implements IMovieController {
    private final Movie movie;
    private final IShowRepository showRepository;
    
    public MovieController(Movie movie, IShowRepository showRepository) {
        this.movie = movie;
        this.showRepository = showRepository;
    }

    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    @Override
    public String[] getGenres() {
        return movie.getGenres().stream().toArray(String[]::new);
    }

    @Override
    public int getDuration() {
        return movie.getDurationInMinutes();
    }

    @Override
    public String[] getLanguages() {
        return movie.getLanguages().stream().toArray(String[]::new);
    }

    @Override
    public String getRating() {
        return movie.getRating().name();
    }

    @Override
    public LocalDate getReleaseDate() {
        return movie.getReleaseDate();
    }

    @Override
    public List<Show> getShows() {
        return showRepository.getShows(movie);
    }
}