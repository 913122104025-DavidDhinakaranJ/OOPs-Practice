package com.mycompany.bankapplication4.views;

public interface IATMView {
    
    public void runATMView();

    public void handleDeposit();

    public void handleWithdraw();

    public void handleSwipe();

    public void handleExit();

    public void handleInvalidChoice();
    
}