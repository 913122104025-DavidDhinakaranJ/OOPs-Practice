package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.ShowSeat;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.repositories.IBookingRepository;
import java.util.List;

public class ShowController implements IShowController {
    private final Show show;
    private final IBookingRepository bookingRepository;

    public ShowController(Show show, IBookingRepository bookingRepository) {
        this.show = show;
        this.bookingRepository = bookingRepository;
    } 

    @Override
    public String getMovie() {
        return show.getMovie().getTitle();
    }

    @Override
    public String getTheatre() {
        return show.getTheatre().getName();
    }

    @Override
    public String getCinemaHall() {
        return show.getCinemaHall().getName();
    }

    @Override
    public String getStartingTime() {
        return show.getStartTime().toString();
    }

    @Override
    public String getEndingTime() {
        return show.getEndTime().toString();
    }

    @Override
    public List<ShowSeat> getAvailableSeats() {
        return show.getAvailableSeats();
    }

    @Override
    public Booking createBooking(Customer customer, List<ShowSeat> showSeats) {
        Booking booking = new Booking(customer, show, showSeats);
        bookingRepository.saveBooking(booking);
        customer.addBooking(booking);
        return booking;
    }

    @Override
    public double getBasePrice() {
        return show.getPrice();
    }
}