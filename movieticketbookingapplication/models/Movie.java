package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Movie {
    private static final AtomicLong idCounter = new AtomicLong();
    
    private final String movieId;
    private final String title;
    private final Set<Genre> genres;
    private int durationInMinutes;
    private final Set<Language> languages;
    private final Rating rating;
    private LocalDate releaseDate;
    
    public Movie(String title, Rating rating) {
        this.movieId = "MOV" + idCounter.incrementAndGet();
        this.title = title;
        this.genres = new HashSet<>();
        this.languages = new HashSet<>();
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return new ArrayList<>(genres);
    }
    
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
    
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public List<Language> getLanguages() {
        return new ArrayList<>(languages);
    }
    
    public void addLanguage(Language language) {
        this.languages.add(language);
    }
    
    public void removeLanguage(Language language) {
        this.languages.remove(language);
    }

    public Rating getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}