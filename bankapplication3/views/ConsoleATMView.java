package com.mycompany.bankapplication3.views;

import com.mycompany.bankapplication3.enums.ATMOption;
import java.util.Scanner;

public class ConsoleATMView implements IATMView {
    Scanner scanner = new Scanner(System.in);

    @Override
    public ATMOption getCardMenuChoice() {
        System.out.println("1. Deposit Cash");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Swipe Shopping");
        System.out.println("0. Exit From ATM");
        
        System.out.print("Enter choice: ");
        return switch(scanner.nextInt()) {
            case 1 -> ATMOption.DEPOSIT;
            case 2 -> ATMOption.WITHDRAW;
            case 3 -> ATMOption.SWIPE;
            case 0 -> ATMOption.EXIT;
            default -> ATMOption.INVALID;
        };
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

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    @Override
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}