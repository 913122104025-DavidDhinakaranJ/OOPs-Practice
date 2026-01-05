package com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces;

import com.mycompany.movieticketbookingapplication.models.Booking;
import java.util.List;

public interface ICustomerController {
    List<Booking> getBookingHistory();

    void changePassword(String newPassword);
}