package com.mycompany.bankapplication4.views;

import com.mycompany.bankapplication4.models.User;

public interface IAuthView {
    
    public void handleRegistration();
    
    public User handleLogin();
    
}