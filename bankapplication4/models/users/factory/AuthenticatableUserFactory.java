package com.mycompany.bankapplication4.models.users.factory;

import com.mycompany.bankapplication4.models.users.AuthenticatableUser;

public interface AuthenticatableUserFactory {
    public AuthenticatableUser createUser(String username, String password);
}