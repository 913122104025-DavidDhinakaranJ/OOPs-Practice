package com.mycompany.authlib.views;

import com.mycompany.authlib.users.AuthenticatableUser;

public interface IAuthView {
    
    public void handleRegistration();
    
    public AuthenticatableUser handleLogin();
    
}