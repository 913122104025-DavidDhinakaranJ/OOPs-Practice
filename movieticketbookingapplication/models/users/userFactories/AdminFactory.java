package com.mycompany.movieticketbookingapplication.models.users.userFactories;

import com.mycompany.authlib.users.AuthenticatableUser;
import com.mycompany.authlib.users.factory.AuthenticatableUserFactory;
import com.mycompany.movieticketbookingapplication.models.users.Admin;

public class AdminFactory implements AuthenticatableUserFactory {

    @Override
    public AuthenticatableUser createUser(String username, String password) {
        return new Admin(username, password);
    }
    
}