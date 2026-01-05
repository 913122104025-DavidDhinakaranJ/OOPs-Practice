package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import java.util.List;

public interface IMovieRepository {
    List<Movie> getAllMovies();
    List<Movie> getMovies(String title);
    List<Movie> getMovies(Genre genre);
    List<Movie> getMovies(Language language);
    List<Movie> getMovies(Rating rating);

    void addMovie(Movie movie);

    void deleteMovie(Movie movie);
}