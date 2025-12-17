package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.User;

public interface IAuthController {
    public void handleRegistration(String username, String password) throws Exception;
    public User handleLogin(String username, String password) throws Exception;
}