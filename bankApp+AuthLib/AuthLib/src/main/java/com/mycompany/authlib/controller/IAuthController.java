package com.mycompany.authlib.controller;

import com.mycompany.authlib.exceptions.IncorrectPasswordException;
import com.mycompany.authlib.exceptions.UserAlreadyExistException;
import com.mycompany.authlib.exceptions.UserNotFoundException;
import com.mycompany.authlib.users.AuthenticatableUser;

public interface IAuthController {
    public void handleRegistration(String username, String password) throws UserAlreadyExistException;
    public AuthenticatableUser handleLogin(String username, String password) throws UserNotFoundException, IncorrectPasswordException;
}