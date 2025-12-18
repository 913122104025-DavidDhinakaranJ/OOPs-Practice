package com.mycompany.authlib.users.factory;

import com.mycompany.authlib.users.AuthenticatableUser;

public interface AuthenticatableUserFactory {
    public AuthenticatableUser createUser(String username, String password);
}