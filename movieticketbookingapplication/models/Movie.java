package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Movie {
    private static final AtomicLong idCounter = new AtomicLong();
    
    private final String movieId;
    private final String title;
    private final List<Genre> genres;
    private int durationInMinutes;
    private final List<Language> languages;
    private final Rating rating;
    private Date releaseDate;
    
    public Movie(String title, Rating rating) {
        this.movieId = "MOV" + idCounter.incrementAndGet();
        this.title = title;
        this.genres = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }
    
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public List<Language> getLanguages() {
        return languages;
    }
    
    public void addLanguage(Language language) {
        this.languages.add(language);
    }

    public Rating getRating() {
        return rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}