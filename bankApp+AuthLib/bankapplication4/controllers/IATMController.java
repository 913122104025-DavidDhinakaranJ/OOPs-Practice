package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.exceptions.InsufficientAmountException;
import com.mycompany.bankapplication4.exceptions.NotAMultipleOf5Exception;

public interface IATMController {
    
    public double getBalance();
    
    public void handleDeposit(double amount);
    
    public void handleWithdraw(double amount, double transactionCharge) throws NotAMultipleOf5Exception, InsufficientAmountException;
    
    public void handleSwipe(double amount, double cashback) throws InsufficientAmountException;
    
    public double calculateTransactionCharge(double amount);
    
    public double calculateCashBack(double amount);
}