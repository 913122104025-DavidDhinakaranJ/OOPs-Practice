package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.models.Booking;
import java.util.List;

public interface ICustomerController {
    void handleSearchMovie();
    List<Booking> getBookingHistory();
}