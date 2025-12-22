package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.views.ConsoleSearchView;
import java.util.List;

public class CustomerController implements ICustomerController {
    private final Customer customer;

    public CustomerController(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void handleSearchMovie() {
        ConsoleSearchView searchView = new ConsoleSearchView(new SearchController(customer, null));
        searchView.runSearchView();
    }

    @Override
    public List<Booking> getBookingHistory() {
        return customer.getBookings();
    }
}