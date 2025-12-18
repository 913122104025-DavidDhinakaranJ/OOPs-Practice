package com.mycompany.authlib.users;

public interface AuthenticatableUser {
    public String getUsername();
    public boolean validatePassword(String enteredPassword);
}