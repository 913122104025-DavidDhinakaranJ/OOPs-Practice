package com.mycompany.authlib.controller;

import com.mycompany.authlib.users.AuthenticatableUser;

public interface IAuthController {
    public void handleRegistration(String username, String password) throws Exception;
    public AuthenticatableUser handleLogin(String username, String password) throws Exception;
}