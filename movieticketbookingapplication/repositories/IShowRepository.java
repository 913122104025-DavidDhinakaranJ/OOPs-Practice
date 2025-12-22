package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import java.util.List;

public interface IShowRepository {
    List<Show> getShows(Movie movie);
}