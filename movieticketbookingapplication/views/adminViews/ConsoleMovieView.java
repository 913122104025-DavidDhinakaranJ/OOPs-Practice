package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.MovieUpdateOption;
import com.mycompany.movieticketbookingapplication.models.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleMovieView {
    private final Scanner scanner;
    private final IMovieController movieController;
    
    private boolean running;
    
    public ConsoleMovieView(IMovieController movieController) {
        this.scanner = new Scanner(System.in);
        this.movieController = movieController;
    }
    
    public void runMovieView() {
        running = true;
        
        while(running) {
            AdminOperationsOption choice = getAdminOperationsOption();
            switch(choice) {
                case ADD -> handleAddMovie();
                case UPDATE -> handleUpdateMovie();
                case DELETE -> handleDeleteMovie();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminOperationsOption getAdminOperationsOption() {
        System.out.println("1. Add Movie");
        System.out.println("2. Update Movie");
        System.out.println("3. Remove Movie");
        System.out.println("0. Exit");

        System.out.print("Enter Choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> AdminOperationsOption.ADD;
            case 2 -> AdminOperationsOption.UPDATE;
            case 3 -> AdminOperationsOption.DELETE;
            case 0 -> AdminOperationsOption.EXIT;
            default -> AdminOperationsOption.INVALID;
        };
    }
    
    private void handleAddMovie() {
        String title = getMovieTitle();
        Set<Genre> genres = getGenres(Genre.values());
        Set<Language> languages = getLanguages(Language.values());
        Rating rating = getRating();
        int duration = getDuration();
        LocalDate date = getReleaseDate();
        
        movieController.addMovie(title, genres, languages, rating, duration, date);
    }

    private void handleUpdateMovie() {
        Movie movie = getMovie();
        if(movie == null) return;
        
        MovieUpdateOption choice = getMovieUpdateOption();
        switch(choice) {
            case UPDATE_GENRES -> handleUpdateGenres(movie);
            case UPDATE_LANGUAGES -> handleUpdateLanguages(movie);
            case UPDATE_DURATION -> handleUpdateDuration(movie);
            case UPDATE_RELEASE_DATE -> handleUpdateReleaseDate(movie);
            case INVALID -> handleInvalidChoice();
        }
    }

    private void handleDeleteMovie() {
        Movie movie = getMovie();
        if(movie == null) return;
        
        movieController.deleteMovie(movie);
    }
    
    private void handleExit() {
        running = false;
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private String getMovieTitle() {
        System.out.print("Enter Movie Title: ");
        return scanner.nextLine();
    }
    
    private Set<Genre> getGenres(Genre[] genres) {
        for(int i = 0; i < genres.length;i++) {
            System.out.println(i + 1 + ". " + genres[i]);
        }
        
        System.out.print("Enter Genres (Space Seperated): ");
        String genresChoice = scanner.nextLine();
        
        Set<Genre> genreList = new HashSet<>();
        for(String choice : genresChoice.split(" ")) {
            int index = Integer.parseInt(choice) - 1;

            if(index < 0 || index >= genres.length) {
                displayError("Invalid Genre Choice: " + index);
            }
            genreList.add(genres[index]);
        }
        
        return genreList;
    }
    
    private Set<Language> getLanguages(Language[] languages) {
        for(int i = 0; i < languages.length;i++) {
            System.out.println(i + 1 + ". " + languages[i]);
        }
        
        System.out.print("Enter Langauges (Space Seperated): ");
        String languagesChoice = scanner.nextLine();
        
        Set<Language> languageList = new HashSet<>();
        for(String choice : languagesChoice.split(" ")) {
            int index = Integer.parseInt(choice) - 1;

            if(index < 0 || index >= languages.length) {
                displayError("Invalid Language Choice: " + index);
            }
            languageList.add(languages[index]);
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
    
    private MovieUpdateOption getMovieUpdateOption() {
        System.out.println("1. Update Genres");
        System.out.println("2. Update Languages");
        System.out.println("3. Update Duration");
        System.out.println("4. Update Release Date");
        
        System.out.print("Enter Choice: ");
        scanner.nextInt();
        
        return switch(scanner.nextInt()) {
            case 1 -> MovieUpdateOption.UPDATE_GENRES;
            case 2 -> MovieUpdateOption.UPDATE_LANGUAGES;
            case 3 -> MovieUpdateOption.UPDATE_DURATION;
            case 4 -> MovieUpdateOption.UPDATE_RELEASE_DATE;
            default -> MovieUpdateOption.INVALID;
        };
    }
    
    private Movie getMovie() {
        List<Movie> movieList = movieController.getAllMovies();
        for(int i = 0;i < movieList.size();i++) {
            System.out.println(i + 1 + ". " + movieList.get(i).getTitle());
        }
        
        System.out.print("Enter Movie Choice: ");
        int movieChoice = scanner.nextInt();
        
        if(movieChoice < 1 || movieChoice > movieList.size()) {
            displayError("Invalid Movie Choice.");
            return null;
        }
        
        return movieList.get(movieChoice - 1);
    }
    
    private void handleUpdateGenres(Movie movie) {
        System.out.println("Add Genres: ");
        Set<Genre> addGenres = getGenres(Genre.values());
                
        System.out.println("Remove Genres: ");
        Set<Genre> removeGenres = getGenres(movie.getGenres().toArray(new Genre[0]));
        
        movieController.updateMovieGenres(movie, addGenres, removeGenres);
    }

    private void handleUpdateLanguages(Movie movie) {
        System.out.println("Add Languages: ");
        Set<Language> addLanguages = getLanguages(Language.values());
                
        System.out.println("Remove Languages: ");
        Set<Language> removeLanguages = getLanguages(movie.getLanguages().toArray(new Language[0]));
        
        movieController.updateMovieLanguages(movie, addLanguages, removeLanguages);
    }

    private void handleUpdateDuration(Movie movie) {
        int duration = getDuration();
        movieController.updateMovieDuration(movie, duration);
    }

    private void handleUpdateReleaseDate(Movie movie) {
        LocalDate date = getReleaseDate();
        movieController.updateMovieReleaseDate(movie, date);
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}