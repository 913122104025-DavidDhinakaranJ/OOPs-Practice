package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import java.util.List;

public interface IShowRepository {
    void addShow(Show show);
    void deleteShow(Show show);
    
    List<Show> getShows(Movie movie);
    List<Show> getAllShows();
}