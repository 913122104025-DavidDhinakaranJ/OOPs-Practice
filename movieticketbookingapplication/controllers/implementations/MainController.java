package com.mycompany.movieticketbookingapplication.controllers.implementations;

import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.SearchController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.customerControllersImplementations.CustomerController;
import com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations.AdminController;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.IMainController;
import com.mycompany.authlib.controller.AuthController;
import com.mycompany.authlib.views.ConsoleAuthView;
import com.mycompany.authlib.views.IAuthView;
import com.mycompany.movieticketbookingapplication.models.users.Customer;
import com.mycompany.movieticketbookingapplication.models.users.User;
import com.mycompany.movieticketbookingapplication.models.users.userFactories.CustomerFactory;
import com.mycompany.movieticketbookingapplication.repositories.IUserRepository;
import com.mycompany.movieticketbookingapplication.views.adminViews.ConsoleAdminView;
import com.mycompany.movieticketbookingapplication.views.customerViews.ConsoleCustomerView;
import com.mycompany.movieticketbookingapplication.views.customerViews.ConsoleSearchView;

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
                ConsoleCustomerView customerView = new ConsoleCustomerView(new CustomerController((Customer) user));
                customerView.runCustomerView();
            }
            case ADMIN -> {
                ConsoleAdminView adminView = new ConsoleAdminView(new AdminController());
                adminView.runAdminView();
            }
        }
    }

    @Override
    public void handleSearch() {
        ConsoleSearchView searchView = new ConsoleSearchView(new SearchController(null, null));
        searchView.runSearchView();
    }
}