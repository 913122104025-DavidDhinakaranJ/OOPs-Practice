package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import java.time.LocalDate;
import java.util.List;

public interface IAdminController {

    void addMovie(String title, List<Genre> genres, List<Language> languages, Rating rating, int duration, LocalDate date);
    
}