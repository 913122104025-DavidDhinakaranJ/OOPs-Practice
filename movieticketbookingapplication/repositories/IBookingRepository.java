package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.movieticketbookingapplication.models.Booking;

public interface IBookingRepository {
    void saveBooking(Booking booking);
    
}