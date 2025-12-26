package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.contexts.SessionContext;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.ShowMenuOption;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.Seat;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.ShowSeat;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsoleShowView {
    private final ConsoleInputUtil inputReader;
    private final IShowController showController;
    private final ApplicationContext appContext;
    
    private boolean running;

    public ConsoleShowView(IShowController showController) {
        inputReader = new ConsoleInputUtil();
        this.showController = showController;
        this.appContext = ApplicationContext.getInstance();
    }
    
    public void runShowView() {
        running = true;
        while(running) {
            ShowMenuOption choice = getShowMenuOption();
            switch(choice) {
                case VIEW_DETAILS -> handleViewDetails();
                case BOOK_SHOW -> handleBookShow();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private ShowMenuOption getShowMenuOption() {
        System.out.println("1. View Show Details");
        System.out.println("2. Book Ticket For Show");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> ShowMenuOption.VIEW_DETAILS;
            case 2 -> ShowMenuOption.BOOK_SHOW;
            case 0 -> ShowMenuOption.EXIT;
            default -> ShowMenuOption.INVALID;
        };
    }

    private void handleViewDetails() {
        Show show = showController.getShow();
        System.out.println("Movie: " + show.getMovie().getTitle());
        System.out.println("Theatre: " + show.getTheatre().getName());
        System.out.println("Cinema Hall: " + show.getCinemaHall().getName());
        System.out.println("Starting Time: " + inputReader.formatDateTime(show.getStartTime()));
        System.out.println("Ending Time: " + inputReader.formatDateTime(show.getEndTime()));
        System.out.println("Base Price: " + show.getPrice());
        System.out.println("Available Seats: ");
        List<ShowSeat> availableSeats = show.getAvailableSeats();
        int seatCount = 0;
        for(ShowSeat showSeat : availableSeats) {
            Seat seat = showSeat.getSeat();
            System.out.print(seat.getRow() + seat.getSeatNumber() + "-" + seat.getSeatType() + "\t");
            if(++seatCount % 5 == 0) System.out.println();
        }
        
        if(seatCount % 5 != 0) System.out.println();
    }

    private void handleBookShow() {
        SessionContext sessionContext = appContext.getSessionContext();
        Customer customer = (Customer) sessionContext.getCurrentUser().orElse(null);

        if(customer == null) {
            System.out.println("Log in to book ticket.");
            return;
        }
        
        List<ShowSeat> availableSeats = showController.getAvailableSeats();
        
        int availableSeatCount = 0;
        for(ShowSeat showSeat : availableSeats) {
            Seat seat = showSeat.getSeat();
            System.out.println(++availableSeatCount + ". " + seat.getRow() + seat.getSeatNumber() + "-" + seat.getSeatType());
        }
        
        int wantedSeatCount = inputReader.readInt("Enter number of seats that you want to book: ");
        
        if(wantedSeatCount <= 0) {
            displayError("Number of seats must be positive value.");
            return;
        }
            
        if(wantedSeatCount > availableSeatCount) {
            displayError("There is not enough available seats to book.");
            return;
        }
        
        Set<ShowSeat> selectedSeats = new HashSet<>();
        for(int i = 0;i < wantedSeatCount;) {
            int seatChoice = inputReader.readInt("Enter Seat Choice: ");

            if(seatChoice < 1 || seatChoice > availableSeatCount) {
                displayError("Invalid Seat Choice.");
                continue;
            }
            
            if(!selectedSeats.add(availableSeats.get(seatChoice - 1))) {
                displayError("You have already selected that seat.");
                continue;
            }

            i++;
        }
        
        Booking booking = showController.createBooking(customer, availableSeats);
        ConsolePaymentView paymentView = new ConsolePaymentView(booking.getPayment());
        if(paymentView.handlePayment(booking.getTotalPrice())) {
            booking.updateStatusToConfirmed();
        } else {
            booking.updateStatusToCancelled();
        }
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