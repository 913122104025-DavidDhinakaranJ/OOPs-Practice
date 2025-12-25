package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IBookingController;
import com.mycompany.movieticketbookingapplication.enums.BookingStatus;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.ShowSeat;
import java.time.LocalDateTime;

public class BookingController implements IBookingController {
    private final Booking booking;

    public BookingController(Booking booking) {
        this.booking = booking;
    }

    @Override
    public Booking getBooking() {
        return booking;
    }

    @Override
    public boolean isShowExpired() {
        return booking.getShow().getStartTime().isAfter(LocalDateTime.now());
    }

    @Override
    public BookingStatus getBookingStatus() {
        return booking.getBookingStatus();
    }

    @Override
    public double cancelBooking() {
        booking.updateStatusToCancelled();
        for(ShowSeat seat : booking.getSeats()) {
            seat.cancelSeat();
        }
        
        booking.getPayment().updateStatusToRefunded();
        return booking.getTotalPrice();
    }
    
}