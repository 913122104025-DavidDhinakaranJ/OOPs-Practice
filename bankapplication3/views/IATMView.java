package com.mycompany.bankapplication3.views;
import com.mycompany.bankapplication3.enums.ATMOption;

public interface IATMView extends IMessageView {
    ATMOption getCardMenuChoice();
    
    String getCardNumber();
    int getPin();
    
    double getDepositAmount();
    double getWithdrawAmount();
    double getSwipePayAmount();
    
    void displayTransactionCharge(double transactionCharge);
    void displayCashback(double cashback);
    void displayBalance(double balance);
}