package com.mycompany.bankapplication4.views;

import com.mycompany.bankapplication4.models.users.AuthenticatableUser;

public interface IAuthView {
    
    public void handleRegistration();
    
    public AuthenticatableUser handleLogin();
    
}