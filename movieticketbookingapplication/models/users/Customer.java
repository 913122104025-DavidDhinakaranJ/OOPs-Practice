package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.movieticketbookingapplication.enums.Role;
import com.mycompany.movieticketbookingapplication.models.Booking;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Customer extends User {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    private final List<Booking> bookings;
    
    public Customer(String username, String password) {
        super(username, password, "CUST" + idCounter.incrementAndGet(), Role.CUSTOMER);
        this.bookings = new ArrayList<>();
    }
    
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
}