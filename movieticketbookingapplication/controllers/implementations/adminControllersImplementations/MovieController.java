package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class MovieController implements IMovieController {
    private final IMovieRepository movieRepository;
    
    public MovieController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    @Override
    public void addMovie(String title, Set<Genre> genres, Set<Language> languages, Rating rating, int duration, LocalDate date) {
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
    
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public void updateMovieGenres(Movie movie, Set<Genre> addGenres, Set<Genre> removeGenres) {
        for(Genre genre : addGenres) {
            movie.addGenre(genre);
        }
        
        for(Genre genre : removeGenres) {
            movie.removeGenre(genre);
        }
    }

    @Override
    public void updateMovieLanguages(Movie movie, Set<Language> addLanguages, Set<Language> removeLanguages) {
        for(Language language : addLanguages) {
            movie.addLanguage(language);
        }
        
        for(Language language : removeLanguages) {
            movie.removeLanguage(language);
        }
    }

    @Override
    public void updateMovieDuration(Movie movie, int duration) {
        movie.setDurationInMinutes(duration);
    }

    @Override
    public void updateMovieReleaseDate(Movie movie, LocalDate releaseDate) {
        movie.setReleaseDate(releaseDate);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie);
    }
}