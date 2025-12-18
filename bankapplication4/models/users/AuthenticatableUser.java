package com.mycompany.bankapplication4.models.users;

public interface AuthenticatableUser {
    public String getUsername();
    public boolean validatePassword(String enteredPassword);
}