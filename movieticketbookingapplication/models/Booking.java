package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.BookingStatus;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Booking {
    private static final AtomicLong idCounter = new AtomicLong(10000);
    
    private final String bookingId;
    private final LocalDateTime bookingDate;
    private BookingStatus status;
    private final Customer customer;
    private final Show show;
    private final List<ShowSeat> seats;    
    
    public Booking(Customer customer, Show show, List<ShowSeat> seats) {
        this.bookingId = "BOOK" + idCounter.incrementAndGet();
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.PENDING;
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookingStatus getBookingStatus() {
        return this.status;
    }
    
    public void updateStatusToConfirmed() {
        this.status = BookingStatus.CONFIRMED;
        for(ShowSeat seat : seats) {
            seat.bookSeat();
        }
    }
    
    public void updateStatusToCancelled() {
        this.status = BookingStatus.CANCELLED;
        for(ShowSeat seat : seats) {
            seat.cancelSeat();
        }
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return new ArrayList<>(seats);
    }
}