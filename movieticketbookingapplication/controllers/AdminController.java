package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import java.time.LocalDate;
import java.util.List;

public class AdminController implements IAdminController {
    private final IMovieRepository movieRepository;
    
    public AdminController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void addMovie(String title, List<Genre> genres, List<Language> languages, Rating rating, int duration, LocalDate date) {
        Movie movie = new Movie(title, rating);
        
        for(Genre genre : genres) {
            movie.addGenre(genre);
        }
        
        for(Language language : languages) {
            movie.addLanguage(language);
        }
        
        movie.setDurationInMinutes(duration);
        movie.setReleaseDate(date);
        
        movieRepository.addMovie(movie);
    }
}