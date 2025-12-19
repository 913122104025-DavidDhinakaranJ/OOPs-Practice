package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.movieticketbookingapplication.enums.menuOptions.SearchMenuOption;
import java.util.Scanner;

public class ConsoleSearchView {
    private final Scanner scanner;
    
    private boolean searching;
    
    public ConsoleSearchView() {
        this.scanner = new Scanner(System.in);
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleSearchByGenre() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleSearchByLanguage() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleSearchByRating() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void handleCloseSearch() {
        searching = false;
        System.out.println("Closed Searching.");
    }
    
    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}