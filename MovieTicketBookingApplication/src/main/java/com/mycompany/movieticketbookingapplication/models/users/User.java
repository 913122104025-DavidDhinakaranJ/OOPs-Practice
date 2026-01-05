package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.authlib.users.AuthenticatableUser;
import com.mycompany.movieticketbookingapplication.enums.Role;

public abstract class User implements AuthenticatableUser {    
    private final String username;
    private String password;
    private final String userId;
    private final Role role;
    private boolean isBlocked;

    public User(String username, String password, String userId, Role role) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.role = role;
        this.isBlocked = false;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public Role getRole() {
        return this.role;
    }
    
    public void blockUser() {
        isBlocked = true;
    }
    
    public void unblockUser() {
        isBlocked = false;
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }
}