package com.mycompany.bankapplication3.controllers;

import com.mycompany.bankapplication3.models.User;
import com.mycompany.bankapplication3.repositories.IUserRepository;
import com.mycompany.bankapplication3.views.IAuthView;
import com.mycompany.bankapplication3.views.IMainView;

public class AuthController {
    private final IUserRepository users;
    private final IAuthView authView;

    public AuthController(IAuthView authView, IUserRepository users) {
        this.users = users;
        this.authView = authView;
    }
    
    public void handleRegistration() {
        String[] credentials = authView.getRegistrationDetails();
        String username = credentials[0];
        String password = credentials[1];
        
        if(users.isUsernameTaken(username)) {
            authView.displayError("Username " + username + " has already taken");
            return;
        }
        
        User newUser = new User(username, password);
        users.saveUser(newUser);
        
        authView.displayMessage("Registration Sucessful. User Id: " + newUser.getUserId());
    }
    
    public User handleLogin() {
        String[] credentials = authView.getRegistrationDetails();
        String username = credentials[0];
        String password = credentials[1];
        
        User user = users.findUser(username);
        if(user == null) {
            authView.displayError("User with username '" + username + "' not found.");
        } else if(user.validatePassword(password)) {
            authView.displayMessage("Login Sucessful.");
            return user;
        } 
        
        return null;
    }
}