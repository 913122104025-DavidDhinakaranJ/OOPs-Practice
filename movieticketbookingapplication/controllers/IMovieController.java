package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.models.Show;
import java.time.LocalDate;
import java.util.List;

public interface IMovieController {
    String getTitle();
    String[] getGenres();
    int getDuration();
    String[] getLanguages();
    String getRating();
    LocalDate getReleaseDate();

    List<Show> getShows();
}