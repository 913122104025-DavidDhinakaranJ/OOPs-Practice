package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.users.AuthenticatableUser;

public interface IAuthController {
    public void handleRegistration(String username, String password) throws Exception;
    public AuthenticatableUser handleLogin(String username, String password) throws Exception;
}