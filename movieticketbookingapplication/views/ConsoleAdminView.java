package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.controllers.IAdminController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.AdminMenuOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleAdminView {
    private final Scanner scanner;
    private final IAdminController adminController;
    
    private boolean login;
    
    public ConsoleAdminView(IAdminController adminController) {
        this.scanner = new Scanner(System.in);
        this.adminController = adminController;
    }
    
    public void runAdminView() {
        login = true;
        while(login) {
            AdminMenuOption choice = getAdminMenuOption();
            switch(choice) {
                case MANAGE_THEATRES -> handleManageTheatres();
                case MANAGE_SHOWS -> handleManageShows();
                case MANAGE_MOVIES -> handleManageMovies();
                case MANAGE_USERS -> handleManageUsers();
                case LOGOUT -> handleLogout();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminMenuOption getAdminMenuOption() {
        System.out.println("1. Manage Theatres");
        System.out.println("2. Manage Shows");
        System.out.println("3. Manage Movies");
        System.out.println("4. Manage Users");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> AdminMenuOption.MANAGE_THEATRES;
            case 2 -> AdminMenuOption.MANAGE_SHOWS;
            case 3 -> AdminMenuOption.MANAGE_MOVIES;
            case 4 -> AdminMenuOption.MANAGE_USERS;
            case 0 -> AdminMenuOption.LOGOUT;
            default -> AdminMenuOption.INVALID;
        };
    }
    
    private void handleManageTheatres() {
        System.out.println("1. Add Theatre");
        System.out.println("2. Update Theatre");
        System.out.println("3. Remove Theatre");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1 -> handleAddTheatre();
            case 2 -> handleUpdateTheatre();
            case 3 -> handleDeleteTheatre();
        }
    }

    private void handleManageShows() {
        System.out.println("1. Add Show");
        System.out.println("2. Update Show");
        System.out.println("3. Remove Show");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1 -> handleAddShow();
            case 2 -> handleUpdateShow();
            case 3 -> handleDeleteShow();
        }
    }

    private void handleManageMovies() {
        System.out.println("1. Add Movie");
        System.out.println("2. Update Movie");
        System.out.println("3. Remove Movie");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1 -> handleAddMovie();
            case 2 -> handleUpdateMovie();
            case 3 -> handleDeleteMovie();
        }
    }

    private void handleManageUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleLogout() {
        login = false;
        System.out.println("Logged out Successfully.");
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }

    private void handleAddTheatre() {
        System.out.print("Enter Theatre Name: ");
    }

    private void handleUpdateTheatre() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void handleDeleteTheatre() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleAddShow() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleUpdateShow() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleDeleteShow() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleAddMovie() {
        String title = getMovieTitle();
        List<Genre> genres = getGenres();
        List<Language> languages = getLanguages();
        Rating rating = getRating();
        int duration = getDuration();
        LocalDate date = getReleaseDate();
        
        adminController.addMovie(title, genres, languages, rating, duration, date);
    }

    private void handleUpdateMovie() {
        System.out.println("1. Update Genres");
        System.out.println("2. Update Languages");
        System.out.println("3. Update Duration");
        System.out.println("4. Update Release Date");
        System.out.println("0. Exit");
        
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();
    }

    private void handleDeleteMovie() {
        
    }
    
    private String getMovieTitle() {
        System.out.print("Enter Movie Title: ");
        return scanner.nextLine();
    }
    
    private List<Genre> getGenres() {
        Genre[] genres = Genre.values();
        for(int i = 0; i < genres.length;i++) {
            System.out.println(i + 1 + ". " + genres[i]);
        }
        
        System.out.print("Enter Genres (Space Seperated): ");
        String genresChoice = scanner.nextLine();
        
        List<Genre> genreList = new ArrayList<>();
        for(String choice : genresChoice.split(" ")) {
            try {
                int index = Integer.parseInt(choice) - 1;
                
                if(index < 0 || index >= genres.length) {
                    displayError("Invalid Genre Choice: " + index);
                }
                genreList.add(genres[index]);
            } catch(NumberFormatException e) {
                displayError("Invalid input: " + choice);
            }
        }
        
        return genreList;
    }
    
    private List<Language> getLanguages() {
        Language[] languages = Language.values();
        for(int i = 0; i < languages.length;i++) {
            System.out.println(i + 1 + ". " + languages[i]);
        }
        
        System.out.print("Enter Langauges (Space Seperated): ");
        String languagesChoice = scanner.nextLine();
        
        List<Language> languageList = new ArrayList<>();
        for(String choice : languagesChoice.split(" ")) {
            try {
                int index = Integer.parseInt(choice) - 1;
                
                if(index < 0 || index >= languages.length) {
                    displayError("Invalid Language Choice: " + index);
                }
                languageList.add(languages[index]);
            } catch(NumberFormatException e) {
                displayError("Invalid input: " + choice);
            }
        }
        
        return languageList;
    }
    
    private Rating getRating() {
        Rating[] ratings = Rating.values();
        for(int i = 0; i < ratings.length;i++) {
            System.out.println(i + 1 + ". " + ratings[i]);
        }
        
        System.out.print("Enter Rating Choice: ");
        int ratingChoice = scanner.nextInt();
        
        if(ratingChoice < 1 || ratingChoice > ratings.length) {
            displayError("Invalid Rating Choice.");
            return null;
        }
        
        return ratings[ratingChoice - 1];
    }
    
    private int getDuration() {
        System.out.print("Enter duration (in Minutes): ");
        return scanner.nextInt();
    }
    
    private LocalDate getReleaseDate() {
        System.out.print("Enter Movie Release Date: ");
        try {
            LocalDate date = LocalDate.parse(scanner.nextLine());
            return date;
        } catch(DateTimeParseException e) {
            displayError("Invalid Date Format.");
        }
        return null;
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}