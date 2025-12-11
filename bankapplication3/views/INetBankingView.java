package com.mycompany.bankapplication3.views;
import com.mycompany.bankapplication3.enums.AccountType;
import com.mycompany.bankapplication3.enums.CardType;
import com.mycompany.bankapplication3.enums.NetBankingOption;
import com.mycompany.bankapplication3.models.accounts.BankAccount;
import java.util.List;

public interface INetBankingView extends IMessageView {
    NetBankingOption getNetBankingMenuChoice();
    
    AccountType getAccountType();
    CardType getCardType();
    
    String getAccountNumber();
    String getCardNumber();
    int getPin();
    
    double getInitialDepositAmount();
    
    String getSenderAccountNumber();
    String getReceiverAccountNumber();
    double getTransferFundAmount();
    
    void displayAccountList(List<BankAccount> accounts);
    void displayBalance(double balance);
}