package com.mycompany.bankapplication4.views;

public interface INetBankingView {
    
    public void runNetBankingView();
    
    public void handleListAccounts();
    
    public void handleCreateAccount();
    
    public void handleCreateSavingsAccount();
    
    public void handleCreateCurrentAccount();
    
    public void handleInvalidAccountChoice();
    
    public void handleIssueCard();
    
    public void handleIssueCreditCard();
    
    public void handleIssueDebitCard();
    
    public void handleInvalidCardChoice();
    
    public void handleTransferFund();
    
    public void handleLogout();
    
    public void handleInvalidChoice();
    
}