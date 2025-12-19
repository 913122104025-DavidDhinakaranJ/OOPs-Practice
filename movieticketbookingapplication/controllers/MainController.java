package com.mycompany.movieticketbookingapplication.controllers;

import com.mycompany.authlib.controller.AuthController;
import com.mycompany.authlib.views.ConsoleAuthView;
import com.mycompany.authlib.views.IAuthView;
import com.mycompany.movieticketbookingapplication.models.users.User;
import com.mycompany.movieticketbookingapplication.models.users.userFactories.CustomerFactory;
import com.mycompany.movieticketbookingapplication.repositories.IUserRepository;
import com.mycompany.movieticketbookingapplication.views.ConsoleAdminView;
import com.mycompany.movieticketbookingapplication.views.ConsoleCustomerView;
import com.mycompany.movieticketbookingapplication.views.ConsoleSearchView;

public class MainController implements IMainController {
    private final IUserRepository userRepo;
    
    public MainController(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void handleRegistration() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepo, new CustomerFactory()));
        authView.handleRegistration();   
    }

    @Override
    public void handleLogin() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepo, null));
        User user = (User) authView.handleLogin();
        if(user == null) return;
        switch(user.getRole()) {
            case CUSTOMER -> {
                ConsoleCustomerView customerView = new ConsoleCustomerView();
                customerView.runCustomerView();
            }
            case ADMIN -> {
                ConsoleAdminView adminView = new ConsoleAdminView();
                adminView.runAdminView();
            }
        }
    }

    @Override
    public void handleSearch() {
        ConsoleSearchView searchView = new ConsoleSearchView();
        searchView.runSearchView();
    }
}