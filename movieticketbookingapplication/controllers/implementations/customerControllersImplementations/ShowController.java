package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.models.Show;

public class ShowController implements IShowController {
    private final Show show;

    public ShowController(Show show) {
        this.show = show;
    } 
}