package com.mycompany.bankapplication3.views;
import com.mycompany.bankapplication3.enums.AccountType;
import com.mycompany.bankapplication3.enums.CardOption;
import com.mycompany.bankapplication3.enums.CardType;
import com.mycompany.bankapplication3.enums.NetBankingOption;
import com.mycompany.bankapplication3.enums.WelcomeOption;
import com.mycompany.bankapplication3.models.accounts.BankAccount;
import java.util.List;

public interface IBankView {
    WelcomeOption getWelcomeChoice();
    CardOption getCardMenuChoice();
    NetBankingOption getNetBankingMenuChoice();
    
    AccountType getAccountType();
    CardType getCardType();
    
    String[] getLoginDetails();
    String getAccountNumber();
    String getCardNumber();
    int getPin();
    
    double getInitialDepositAmount();
    double getTransferFundAmount();
    double getDepositAmount();
    double getWithdrawAmount();
    double getSwipePayAmount();
    
    void displayMessage(String message);
    void displayAccountList(List<BankAccount> accounts);
    void displayTransactionCharge(double transactionCharge);
    void displayCashback(double cashback);
    void displayBalance(double balance);
}