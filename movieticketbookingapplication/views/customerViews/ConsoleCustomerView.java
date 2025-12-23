package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.ICustomerController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.CustomerMenuOption;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.Seat;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.ShowSeat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCustomerView {
    private final Scanner scanner;
    private final ICustomerController customerController;
    
    private boolean login;
    
    public ConsoleCustomerView(ICustomerController customerController) {
        this.scanner = new Scanner(System.in);
        this.customerController = customerController;
    }
    
    public void runCustomerView() {
        login = true;
        while(login) {
            CustomerMenuOption choice = getCustomerMenuOption();
            switch(choice) {
                case SEARCH_MOVIE -> handleSearchMovie();
                case VIEW_BOOKING_HISTORY -> handleViewBookHistory();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private CustomerMenuOption getCustomerMenuOption() {
        System.out.println("1. Search Movie");
        System.out.println("2. View Booking History");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> CustomerMenuOption.SEARCH_MOVIE;
            case 2 -> CustomerMenuOption.VIEW_BOOKING_HISTORY;
            case 0 -> CustomerMenuOption.LOGOUT;
            default -> CustomerMenuOption.INVALID;
        };
    }

    private void handleSearchMovie() {
        customerController.handleSearchMovie();
    }

    private void handleViewBookHistory() {
        List<Booking> bookings = customerController.getBookingHistory();
        if(bookings.isEmpty()) {
            displayMessage("No Booking History Found.");
        } else {
            displayMessage("My Bookings:");
            displayBookHistory(bookings);
        }
    }

    private void handleLogout() {
        login = false;
        displayMessage("Logged out Successfully.");
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayMessage(String message) {
        System.out.println(message);
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }

    private void displayBookHistory(List<Booking> bookings) {
        for(Booking booking : bookings) {
            List<String> seats = new ArrayList<>();
            for(ShowSeat showSeat : booking.getSeats()) {
                Seat seat = showSeat.getSeat();
                seats.add(seat.getRow() + seat.getSeatNumber() + " - " + seat.getSeatType());
            }
            
            Show show = booking.getShow();
            System.out.println("Date:" + booking.getBookingDate().toString()
                    + " Movie: " + show.getMovie().getTitle()
                    + " Theatre: " + show.getTheatre().getName()
                    + " Cinema Hall: " + show.getCinemaHall().getName()
                    + " Seats: " + String.join(", ", seats) 
                    + " Status: " + booking.getBookingStatus());
        }
    }
}