package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import com.mycompany.movieticketbookingapplication.views.ConsoleMovieView;
import java.util.List;

public class SearchController implements ISearchController {
    private final Customer customer;
    private final IMovieRepository movieRepository;

    public SearchController(Customer customer, IMovieRepository movieRepository) {
        this.customer = customer;
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

    @Override
    public void handleMovieSelection(Movie movie) {
        ConsoleMovieView movieView = new ConsoleMovieView(new MovieController(customer, movie, null));
        movieView.runMovieView();
    }
}