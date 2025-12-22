package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.controllers.ISearchController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.SearchMenuOption;
import com.mycompany.movieticketbookingapplication.models.Movie;
import java.util.List;
import java.util.Scanner;

public class ConsoleSearchView {
    private final Scanner scanner;
    private final ISearchController searchController;
    
    private boolean searching;
    
    public ConsoleSearchView(ISearchController searchController) {
        this.scanner = new Scanner(System.in);
        this.searchController = searchController;
    }
    
    public void runSearchView() {
        searching = true;
        while(searching) {
            SearchMenuOption choice = getSearchMenuOption();
            switch(choice) {
                case SEARCH_BY_TITLE -> handleSearchByTitle();
                case SEARCH_BY_GENRE -> handleSearchByGenre();
                case SEARCH_BY_LANGUAGE -> handleSearchByLanguage();
                case SEARCH_BY_RATING -> handleSearchByRating();
                case CLOSE_SEARCH -> handleCloseSearch();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private SearchMenuOption getSearchMenuOption() {
        System.out.println("1. Search By Title");
        System.out.println("2. Search By Genre");
        System.out.println("3. Search By Language");
        System.out.println("4. Search By Rating");
        System.out.println("0. Close Search");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> SearchMenuOption.SEARCH_BY_TITLE;
            case 2 -> SearchMenuOption.SEARCH_BY_GENRE;
            case 3 -> SearchMenuOption.SEARCH_BY_LANGUAGE;
            case 4 -> SearchMenuOption.SEARCH_BY_RATING;
            case 0 -> SearchMenuOption.CLOSE_SEARCH;
            default -> SearchMenuOption.INVALID;
        };
    }

    private void handleSearchByTitle() {
        String title = getTitleChoice();
        List<Movie> movies = searchController.getMovies(title);
        handleMoviesList(movies);
    }

    private void handleSearchByGenre() {
        Genre genre = getGenreChoice();
        if(genre == null) return;
        List<Movie> movies = searchController.getMovies(genre);
        handleMoviesList(movies);
    }

    private void handleSearchByLanguage() {
        Language language = getLanguageChoice();
        if(language == null) return;
        List<Movie> movies = searchController.getMovies(language);
        handleMoviesList(movies); 
    }

    private void handleSearchByRating() {
        Rating rating = getRatingChoice();
        if(rating == null) return;
        List<Movie> movies = searchController.getMovies(rating);
        handleMoviesList(movies);
    }

    private void handleCloseSearch() {
        searching = false;
        System.out.println("Closed Searching.");
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void handleMoviesList(List<Movie> movies) {
        displayMovies(movies);
        Movie movie = getMovieChoice(movies);
        if(movie == null) return;
        searchController.handleMovieSelection(movie);
    }
    
    private void displayMovies(List<Movie> movies) {
        if(movies.isEmpty()) {
            System.out.println("No movies found");
            return;
        }
        
        for(int i = 0;i < movies.size();i++) {
            System.out.println(i + 1 + ". " + movies.get(i).getTitle());
        }
    }
    
    private String getTitleChoice() {
        System.out.print("Enter Title: ");
        return scanner.nextLine();
    }
    
    private Genre getGenreChoice() {
        Genre[] genres = Genre.values();
        for(int i = 0; i < genres.length;i++) {
            System.out.println(i + 1 + ". " + genres[i]);
        }
        
        System.out.print("Enter Genre Choice: ");
        int genreChoice = scanner.nextInt();
        
        if(genreChoice < 1 || genreChoice > genres.length) {
            displayError("Invalid Genre Choice.");
            return null;
        }
        
        return genres[genreChoice - 1];
    }
    
    private Language getLanguageChoice() {
        Language[] languages = Language.values();
        for(int i = 0; i < languages.length;i++) {
            System.out.println(i + 1 + ". " + languages[i]);
        }
        
        System.out.print("Enter Language Choice: ");
        int languageChoice = scanner.nextInt();
        
        if(languageChoice < 1 || languageChoice > languages.length) {
            displayError("Invalid Language Choice.");
            return null;
        }
        
        return languages[languageChoice - 1];
    }
    
    private Rating getRatingChoice() {
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
    
    private Movie getMovieChoice(List<Movie> movies) {
        System.out.println("0. Back");
        System.out.print("Enter Movie Choice: ");
        int movieChoice = scanner.nextInt();
        if(movieChoice == 0) {
            return null;
        }
        
        if(movieChoice < 1 || movieChoice > movies.size()) {
            displayError("Invalid Movie Choice.");
            return null;
        }
        
        return movies.get(movieChoice - 1);
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}