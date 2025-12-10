package com.mycompany.bankapplication3.views;

import com.mycompany.bankapplication3.enums.AccountType;
import com.mycompany.bankapplication3.enums.CardOption;
import com.mycompany.bankapplication3.enums.CardType;
import com.mycompany.bankapplication3.enums.NetBankingOption;
import com.mycompany.bankapplication3.enums.WelcomeOption;
import com.mycompany.bankapplication3.models.accounts.BankAccount;
import java.util.List;
import java.util.Scanner;

public class ConsoleBankView implements IBankView {
    Scanner scanner = new Scanner(System.in);

    @Override
    public WelcomeOption getWelcomeChoice() {
        System.out.println("1. New User Registration");
        System.out.println("2. Net Banking");
        System.out.println("3. Use Card");
        System.out.println("0. Exit");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> WelcomeOption.REGISTER;
            case 2 -> WelcomeOption.NETBANKING;
            case 3 -> WelcomeOption.CARD;
            case 4 -> WelcomeOption.EXIT;
            default -> WelcomeOption.INVALID;
        };     
    }

    @Override
    public CardOption getCardMenuChoice() {
        System.out.println("1. Deposit Cash");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Swipe Shopping");
        System.out.println("0. Exit");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> CardOption.DEPOSIT;
            case 2 -> CardOption.WITHDRAW;
            case 3 -> CardOption.SWIPE;
            case 4 -> CardOption.EXIT;
            default -> CardOption.INVALID;
        };  
    }

    @Override
    public NetBankingOption getNetBankingMenuChoice() {
        System.out.println("1. My Accounts");
        System.out.println("2. Create Account");
        System.out.println("3. Get ATM Card");
        System.out.println("4. Transfer Funds");
        System.out.println("0. Logout");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> NetBankingOption.LIST_ACCOUNTS;
            case 2 -> NetBankingOption.CREATE_ACCOUNT;
            case 3 -> NetBankingOption.GET_CARD;
            case 4 -> NetBankingOption.TRANSFER_FUND;
            case 5 -> NetBankingOption.LOGOUT;
            default -> NetBankingOption.INVALID;
        };  
    }

    @Override
    public AccountType getAccountType() {
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        
        System.out.print("Enter choice for account: ");
        return switch(scanner.nextInt()) {
            case 1 -> AccountType.SAVINGS;
            case 2 -> AccountType.CURRENT;
            default -> AccountType.DEFAULT;
        };
    }

    @Override
    public CardType getCardType() {
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        
        System.out.print("Enter choice for card: ");
        return switch(scanner.nextInt()) {
            case 1 -> CardType.CREDIT;
            case 2 -> CardType.DEBIT;
            default -> CardType.DEFAULT;
        };
    }

    @Override
    public String[] getLoginDetails() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        return new String[]{username, password};
    }
    
    @Override
    public String getAccountNumber() {
        System.out.print("Enter account number: ");
        return scanner.next();
    }
    
    @Override
    public String getCardNumber() {
        System.out.print("Enter card number: ");
        return scanner.next();
    }

    @Override
    public int getPin() {
        System.out.print("Enter pin: ");
        return scanner.nextInt();
    }

    @Override
    public double getInitialDepositAmount() {
        System.out.print("Enter initial deposit amount: ");
        return scanner.nextDouble();
    }

    @Override
    public double getTransferFundAmount() {
        System.out.print("Enter transfer fund amount: ");
        return scanner.nextDouble();    
    }

    @Override
    public double getDepositAmount() {
        System.out.print("Enter amount to deposit: ");
        return scanner.nextDouble();
    }

    @Override
    public double getWithdrawAmount() {
        System.out.print("Enter amount to withdraw: ");
        return scanner.nextDouble();
    }

    @Override
    public double getSwipePayAmount() {
        System.out.print("Enter amount to swipe pay: ");
        return scanner.nextDouble();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayAccountList(List<BankAccount> accounts) {
        for(BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccNo() + " Account Type: " + account.getAccountType());
        }
    }

    @Override
    public void displayTransactionCharge(double transactionCharge) {
        System.out.println("Transaction Charge: " + transactionCharge);
    }

    @Override
    public void displayCashback(double cashback) {
        System.out.println("Cashback: " + cashback);
    }

    @Override
    public void displayBalance(double balance) {
        System.out.println("Balance: " + balance);
    }
}