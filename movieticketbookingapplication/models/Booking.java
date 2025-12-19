package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.BookingStatus;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Booking {
    private static final AtomicLong idCounter = new AtomicLong(10000);
    
    private final String bookingId;
    private final Date bookingDate;
    private BookingStatus status;
    private final Customer customer;
    private final Show show;
    private final List<ShowSeat> seats;    
    
    public Booking(Customer customer, Show show, List<ShowSeat> seats) {
        this.bookingId = "BOOK" + idCounter.incrementAndGet();
        this.bookingDate = new Date(System.currentTimeMillis());
        this.status = BookingStatus.PENDING;
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    public Date getBookingDate() {
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
    }
    
    public void updateStatusToCancelled() {
        this.status = BookingStatus.CANCELLED;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }
}