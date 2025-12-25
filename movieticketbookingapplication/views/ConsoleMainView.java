package com.mycompany.movieticketbookingapplication.views;

import com.mycompany.authlib.controller.AuthController;
import com.mycompany.authlib.views.ConsoleAuthView;
import com.mycompany.authlib.views.IAuthView;
import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.CustomerController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.SearchController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.MainMenuOption;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.models.users.User;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleAdminView;
import com.mycompany.movieticketbookingapplication.views.customerViews.ConsoleCustomerView;
import com.mycompany.movieticketbookingapplication.views.customerViews.ConsoleSearchView;

public class ConsoleMainView {
    private final ConsoleInputUtil inputReader;
    private final ApplicationContext appContext;
    
    private boolean running;

    public ConsoleMainView() {
        inputReader = new ConsoleInputUtil();
        appContext = ApplicationContext.getInstance();
    }
    
    public void runMainView() {
        running = true;
        while(running) {
            MainMenuOption choice = getMainMenuOption();
            switch(choice) {
                case REGISTER -> handleRegistration();
                case LOGIN -> handleLogin();
                case SEARCH -> handleSearch();
                case EXIT -> stopMainView();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private MainMenuOption getMainMenuOption() {
        System.out.println("1. New User Registration");
        System.out.println("2. Log in");
        System.out.println("3. Search Movie");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> MainMenuOption.REGISTER;
            case 2 -> MainMenuOption.LOGIN;
            case 3 -> MainMenuOption.SEARCH;
            case 0 -> MainMenuOption.EXIT;
            default -> MainMenuOption.INVALID;
        };
    }

    private void handleRegistration() {
        IAuthView authView = new ConsoleAuthView(new AuthController(appContext.getUserRepository(), appContext.getCustomerFactory()));
        authView.handleRegistration();  
    }

    private void handleLogin() {
        IAuthView authView = new ConsoleAuthView(new AuthController(appContext.getUserRepository(), null));
        User user = (User) authView.handleLogin();
        if(user == null) return;
        
        appContext.getSessionContext().login(user);
        switch(user.getRole()) {
            case CUSTOMER -> {
                ConsoleCustomerView customerView = new ConsoleCustomerView(new CustomerController((Customer) user));
                customerView.runCustomerView();
            }
            case ADMIN -> {
                ConsoleAdminView adminView = new ConsoleAdminView();
                adminView.runAdminView();
            }
        }
        appContext.getSessionContext().logout();
    }

    private void handleSearch() {
        ConsoleSearchView searchView = new ConsoleSearchView(new SearchController(appContext.getMovieRepository()));
        searchView.runSearchView();
    }
    
    public void stopMainView() {
        running = false;
        System.out.println("Exiting the app.");
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}