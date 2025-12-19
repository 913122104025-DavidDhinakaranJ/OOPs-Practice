package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.authlib.users.AuthenticatableUser;
import com.mycompany.movieticketbookingapplication.enums.Role;

public abstract class User implements AuthenticatableUser {    
    private final String username;
    private final String password;
    private final String userId;
    private final Role role;

    public User(String username, String password, String userId, Role role) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public Role getRole() {
        return this.role;
    }
}