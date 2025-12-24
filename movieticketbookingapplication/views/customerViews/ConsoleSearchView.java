package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.MovieController;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.ISearchController;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.customerMenuOptions.SearchMenuOption;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.List;

public class ConsoleSearchView {
    private final ConsoleInputUtil inputReader;
    private final ApplicationContext appContext;
    private final ISearchController searchController;
    
    private boolean searching;
    
    public ConsoleSearchView(ISearchController searchController) {
        inputReader = new ConsoleInputUtil();
        appContext = ApplicationContext.getInstance();
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
        
        return switch(inputReader.readInt("Enter choice: ")) {
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
        
        displayMovies(movies);
        handleMoviesListSelection(movies);
    }

    private void handleSearchByGenre() {
        Genre genre = getGenreChoice();
        if(genre == null) return;
        
        List<Movie> movies = searchController.getMovies(genre);
        
        displayMovies(movies);
        handleMoviesListSelection(movies);
    }

    private void handleSearchByLanguage() {
        Language language = getLanguageChoice();
        if(language == null) return;
        
        List<Movie> movies = searchController.getMovies(language);
        
        displayMovies(movies);
        handleMoviesListSelection(movies); 
    }

    private void handleSearchByRating() {
        Rating rating = getRatingChoice();
        if(rating == null) return;
        
        List<Movie> movies = searchController.getMovies(rating);
        
        displayMovies(movies);
        handleMoviesListSelection(movies);
    }

    private void handleCloseSearch() {
        searching = false;
        System.out.println("Closed Searching.");
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void handleMoviesListSelection(List<Movie> movies) {
        Movie movie = getMovieChoice(movies);
        if(movie == null) return;
        
        ConsoleMovieView movieView = new ConsoleMovieView(new MovieController(movie, appContext.getShowRepository()));
        movieView.runMovieView();
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
        return inputReader.readString("Enter Title: ");
    }
    
    private Genre getGenreChoice() {
        Genre[] genres = Genre.values();
        for(int i = 0; i < genres.length;i++) {
            System.out.println(i + 1 + ". " + genres[i]);
        }
        
        while(true) {
            int genreChoice = inputReader.readInt("Enter Genre Choice: ");

            if(genreChoice < 1 || genreChoice > genres.length) {
                displayError("Invalid Genre Choice.");
                continue;
            }

            return genres[genreChoice - 1];
        }
    }
    
    private Language getLanguageChoice() {
        Language[] languages = Language.values();
        for(int i = 0; i < languages.length;i++) {
            System.out.println(i + 1 + ". " + languages[i]);
        }
        
        while(true) {
            int languageChoice = inputReader.readInt("Enter Language Choice: ");

            if(languageChoice < 1 || languageChoice > languages.length) {
                displayError("Invalid Language Choice.");
                continue;
            }

            return languages[languageChoice - 1];
        }
    }
    
    private Rating getRatingChoice() {
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
    
    private Movie getMovieChoice(List<Movie> movies) {
        System.out.println("0. Back");
        int movieChoice = inputReader.readInt("Enter Movie Choice: ");
        
        if(movieChoice == 0) {
            return null;
        }
        
        while(true) {
            if(movieChoice < 1 || movieChoice > movies.size()) {
                displayError("Invalid Movie Choice.");
                continue;
            }

            return movies.get(movieChoice - 1);
        }
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}