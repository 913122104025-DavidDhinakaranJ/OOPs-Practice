package com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IMovieController {
    void addMovie(String title, Set<Genre> genres, Set<Language> languages, Rating rating, int duration, LocalDate date);
    List<Movie> getAllMovies();
    
    void updateMovieGenres(Movie movie, Set<Genre> addGenres, Set<Genre> removeGenres);
    void updateMovieLanguages(Movie movie, Set<Language> addLanguages, Set<Language> removeLanguages);
    void updateMovieDuration(Movie movie, int duration);
    void updateMovieReleaseDate(Movie movie, LocalDate releaseDate);

    void deleteMovie(Movie movie);
}