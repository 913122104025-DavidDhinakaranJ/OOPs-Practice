package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IAdminController;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleMovieView;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleShowView;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleTheatreView;

public class AdminController implements IAdminController {
    @Override
    public void handleManageMovies() {
        ConsoleMovieView movieView = new ConsoleMovieView(null);
        movieView.runMovieView();
    }

    @Override
    public void handleManageShows() {
        ConsoleShowView showView = new ConsoleShowView(null);
        showView.runShowView();
    }

    @Override
    public void handleManageTheatres() {
        ConsoleTheatreView theatreView = new ConsoleTheatreView(null);
        theatreView.runTheatreView();
    }
}