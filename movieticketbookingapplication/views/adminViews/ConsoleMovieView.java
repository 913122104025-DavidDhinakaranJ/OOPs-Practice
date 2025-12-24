package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IMovieController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminOperationsOption;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.MovieUpdateOption;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsoleMovieView {
    private final ConsoleInputUtil inputReader;
    private final IMovieController movieController;
    
    private boolean running;
    
    public ConsoleMovieView(IMovieController movieController) {
        inputReader = new ConsoleInputUtil();
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

        return switch(inputReader.readInt("Enter choice: ")) {
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
        return inputReader.readString("Enter Movie Title: ");
    }
    
    private Set<Genre> getGenres(Genre[] genres) {
        for(int i = 0; i < genres.length;i++) {
            System.out.println(i + 1 + ". " + genres[i]);
        }
        
        String genresChoice = inputReader.readString("Enter Genre Choices (Space Seperated): ");
        
        Set<Genre> genreList = new HashSet<>();
        for(String choice : genresChoice.trim().split("\\s+")) {
            try {
                int index = Integer.parseInt(choice) - 1;

                if(index < 0 || index >= genres.length) {
                    displayError("Invalid Genre Choice: " + index + " Omitted");
                }
                genreList.add(genres[index]);
            } catch(NumberFormatException e) {
                displayError("Invalid input");
            }
        }
        
        return genreList;
    }
    
    private Set<Language> getLanguages(Language[] languages) {
        for(int i = 0; i < languages.length;i++) {
            System.out.println(i + 1 + ". " + languages[i]);
        }
        
        String languagesChoice = inputReader.readString("Enter Langauge Choices (Space Seperated): ");
        
        Set<Language> languageList = new HashSet<>();
        for(String choice : languagesChoice.split(" ")) {
            try {
                int index = Integer.parseInt(choice) - 1;

                if(index < 0 || index >= languages.length) {
                    displayError("Invalid Language Choice: " + index + " Omitted");
                }
                languageList.add(languages[index]);
            } catch(NumberFormatException e) {
                displayError("Invalid input");
            }
        }
        
        return languageList;
    }
    
    private Rating getRating() {
        Rating[] ratings = Rating.values();
        for(int i = 0; i < ratings.length;i++) {
            System.out.println(i + 1 + ". " + ratings[i]);
        }
        
        while(true) {
            int ratingChoice = inputReader.readInt("Enter Rating Choice: ");

            if(ratingChoice < 1 || ratingChoice > ratings.length) {
                displayError("Invalid Rating Choice.");
                continue;
            }
            
            return ratings[ratingChoice - 1];
        }
    }
    
    private int getDuration() {
        return inputReader.readInt("Enter duration (in Minutes): ");
    }
    
    private LocalDate getReleaseDate() {
        return inputReader.readDate("Enter Movie Release Date: ");
    }
    
    private MovieUpdateOption getMovieUpdateOption() {
        System.out.println("1. Update Genres");
        System.out.println("2. Update Languages");
        System.out.println("3. Update Duration");
        System.out.println("4. Update Release Date");
                
        return switch(inputReader.readInt("Enter Choice: ")) {
            case 1 -> MovieUpdateOption.UPDATE_GENRES;
            case 2 -> MovieUpdateOption.UPDATE_LANGUAGES;
            case 3 -> MovieUpdateOption.UPDATE_DURATION;
            case 4 -> MovieUpdateOption.UPDATE_RELEASE_DATE;
            default -> MovieUpdateOption.INVALID;
        };
    }
    
    private Movie getMovie() {
        List<Movie> movieList = movieController.getAllMovies();
        if(movieList.isEmpty()) {
            System.out.println("No Movie found.");
            return null;
        }
        
        for(int i = 0;i < movieList.size();i++) {
            System.out.println(i + 1 + ". " + movieList.get(i).getTitle());
        }
        
        while(true) {
            int movieChoice = inputReader.readInt("Enter Movie Choice: ");
        
            if(movieChoice < 1 || movieChoice > movieList.size()) {
                displayError("Invalid Movie Choice.");
                continue;
            }

            return movieList.get(movieChoice - 1);
        }
    }
    
    private void handleUpdateGenres(Movie movie) {
        System.out.println("Add Genres: ");
        Set<Genre> addGenres = getGenres(Genre.values());
                
        System.out.println("Remove Genres: ");
        Set<Genre> removeGenres = getGenres(movie.getGenres().toArray(Genre[]::new));
        
        movieController.updateMovieGenres(movie, addGenres, removeGenres);
    }

    private void handleUpdateLanguages(Movie movie) {
        System.out.println("Add Languages: ");
        Set<Language> addLanguages = getLanguages(Language.values());
                
        System.out.println("Remove Languages: ");
        Set<Language> removeLanguages = getLanguages(movie.getLanguages().toArray(Language[]::new));
        
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