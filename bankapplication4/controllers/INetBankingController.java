package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.exceptions.AccountNotFoundException;
import com.mycompany.bankapplication4.exceptions.AccountNotOwnedByUserException;
import com.mycompany.bankapplication4.exceptions.InsufficientAmountException;
import com.mycompany.bankapplication4.models.accounts.BankAccount;
import java.util.List;

public interface INetBankingController {
    public List<BankAccount> getAccountsList();
    
    public String createSavingsAccount(double initialAmount);
    
    public String createCurrentAccount(double initialAmount);
    
    public String issueCreditCard(int pin);
    
    public String issueDebitCard(String accNo, int pin) throws AccountNotOwnedByUserException;
    
    public void transferFund(String from, String to, double transferAmount) throws AccountNotOwnedByUserException, AccountNotFoundException, InsufficientAmountException;
    
    public double getBalance(String accNo);
}