package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.User;

public interface IAuthController {
    void handleRegistration(String username, String password) throws Exception;
    public User handleLogin(String username, String password) throws Exception;
}