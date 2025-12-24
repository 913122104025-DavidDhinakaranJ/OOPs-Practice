package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.ISearchController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import java.util.List;

public class SearchController implements ISearchController {
    private final IMovieRepository movieRepository;

    public SearchController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovies(String title) {
        return movieRepository.getMovies(title);
    }

    @Override
    public List<Movie> getMovies(Genre genre) {
        return movieRepository.getMovies(genre);
    }

    @Override
    public List<Movie> getMovies(Language language) {
        return movieRepository.getMovies(language);
    }

    @Override
    public List<Movie> getMovies(Rating rating) {
        return movieRepository.getMovies(rating);
    }
}