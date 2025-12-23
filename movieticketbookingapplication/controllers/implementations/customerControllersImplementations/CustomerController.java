package com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.ICustomerController;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.views.customerViews.ConsoleSearchView;
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