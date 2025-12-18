package com.mycompany.bankapplication4.models.users.factory;

import com.mycompany.authlib.users.AuthenticatableUser;
import com.mycompany.authlib.users.factory.AuthenticatableUserFactory;
import com.mycompany.bankapplication4.models.users.User;

public class UserFactory implements AuthenticatableUserFactory {

    @Override
    public AuthenticatableUser createUser(String username, String password) {
        return new User(username, password);
    }
    
}