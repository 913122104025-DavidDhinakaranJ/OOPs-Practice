package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IBookingController;
import com.mycompany.movieticketbookingapplication.enums.BookingStatus;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.BookingMenuOption;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.Seat;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.ShowSeat;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.ArrayList;
import java.util.List;

public class ConsoleBookingView {
    private final ConsoleInputUtil inputReader;
    private final IBookingController bookingController;
    
    private boolean running;
    
    public ConsoleBookingView(IBookingController bookingController) {
        inputReader = new ConsoleInputUtil();
        this.bookingController = bookingController;
    }
    
    public void runBookingView() {
        running = true;
        while(running) {
            BookingMenuOption choice = getBookingMenuOption();
            switch(choice) {
                case VIEW_DETAILS -> handleViewDetails();
                case CANCEL_BOOKING -> handleCancelBooking();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private BookingMenuOption getBookingMenuOption() {
        System.out.println("1. View Booking Details");
        System.out.println("2. Cancel Booking");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> BookingMenuOption.VIEW_DETAILS;
            case 2 -> BookingMenuOption.CANCEL_BOOKING;
            case 0 -> BookingMenuOption.EXIT;
            default -> BookingMenuOption.INVALID;
        };
    }

    private void handleViewDetails() {
        Booking booking = bookingController.getBooking();
        
        List<String> seats = new ArrayList<>();
        for(ShowSeat showSeat : booking.getSeats()) {
            Seat seat = showSeat.getSeat();
            seats.add(seat.getRow() + seat.getSeatNumber() + " - " + seat.getSeatType());
        }

        Show show = booking.getShow();
        System.out.println("Date:" + booking.getBookingDate().toString());
        System.out.println("Movie: " + show.getMovie().getTitle());
        System.out.println("Theatre: " + show.getTheatre().getName());
        System.out.println("Cinema Hall: " + show.getCinemaHall().getName());
        System.out.println("Seats: " + String.join(", ", seats));
        System.out.println("Status: " + booking.getBookingStatus());
        System.out.println("Total Price: " + booking.getTotalPrice());
    }

    private void handleCancelBooking() {
        BookingStatus status = bookingController.getBookingStatus();
        if(status == BookingStatus.CANCELLED) {
            System.out.println("This booking has already cancelled.");
            return;
        }
        
        if(bookingController.isShowExpired()) {
            System.out.println("The Show has already started. Unable to cancel.");
            return;
        }
        
        double amount = bookingController.cancelBooking();
        System.out.println("Amount of " + amount + " has been refunded.");
    }

    private void handleExit() {
        running = false;
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}