package com.mycompany.bankapplication3.views;

public interface IAuthView extends IMessageView {
    String[] getRegistrationDetails();
    String[] getLoginDetails();
}