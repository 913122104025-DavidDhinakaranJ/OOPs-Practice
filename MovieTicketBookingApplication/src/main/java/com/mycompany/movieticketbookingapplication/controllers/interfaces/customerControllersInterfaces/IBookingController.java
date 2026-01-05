package com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces;

import com.mycompany.movieticketbookingapplication.enums.BookingStatus;
import com.mycompany.movieticketbookingapplication.models.Booking;

public interface IBookingController {
    Booking getBooking();
    boolean isShowExpired();

    BookingStatus getBookingStatus();

    double cancelBooking();
}