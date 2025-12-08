package com.mycompany.bankapplication2;
import java.util.Scanner;

public class BankManagement2 {
    public static void main(String[] args) {
        BankService bankService = BankService.getBankService();
        Scanner sc = new Scanner(System.in);
        System.out.println("0. Exit\n1. Create Bank Account\n2. Get ATM Card\n3. Deposit Cash\n4. Withdraw Cash\n5. Swipe Shopping");
        while(true) {
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            switch(choice) {
                case 0 -> System.exit(0);
                
                case 1 -> {
                    System.out.println("1. Savings Account\n2. Current Account");
                    System.out.print("Enter type of account (1/2): ");
                    int accChoice = sc.nextInt();
                    while(accChoice != 1 && accChoice != 2) {
                        System.out.println("Invalid Choice.");
                        System.out.print("Enter type of account (1/2): ");
                        accChoice = sc.nextInt();
                    }
                    System.out.print("Enter initial amount: ");
                    double initialAmount = sc.nextDouble();
                    switch(accChoice) {
                        case 1 -> bankService.createSavingsAccount(initialAmount);
                        case 2 -> bankService.createCurrentAccount(initialAmount);
                    }
                }
                
                case 2 -> {
                    System.out.println("1. Credit Card\n2. Debit Card");
                    System.out.print("Enter choice of card (1/2): ");
                    int cardChoice = sc.nextInt();
                    while(cardChoice != 1 && cardChoice != 2) {
                        System.out.println("Invalid Choice.");
                        System.out.print("Enter choice of card (1/2): ");
                        cardChoice = sc.nextInt();
                    }
                    switch(cardChoice) {
                        case 1 -> {
                            System.out.print("Set pin for Credit Card: ");
                            int pin = sc.nextInt();
                            bankService.issueCreditCard(pin);
                        }
                        case 2 -> {
                            System.out.print("Enter bank account number: ");
                            String accNo = sc.next();
                            if(bankService.isBankAccountExist(accNo)) {
                                System.out.print("Set pin for Debit Card: ");
                                int pin = sc.nextInt();
                                bankService.issueDebitCard(accNo, pin);
                            }
                        }
                    }
                }
                
                case 3 -> {
                    System.out.print("Enter Card Number: ");
                    String cardNo = sc.next();
                    System.out.print("Enter pin: ");
                    int pin = sc.nextInt();
                    if(bankService.isCardExist(cardNo) && bankService.validatePIN(cardNo, pin)) {
                        System.out.print("Enter Amount to Deposit: ");
                        double amount = sc.nextDouble();
                        bankService.deposit(cardNo, amount);
                    }
                }
                
                case 4 -> {
                    System.out.print("Enter Card Number: ");
                    String cardNo = sc.next();
                    System.out.print("Enter pin: ");
                    int pin = sc.nextInt();
                    if(bankService.isCardExist(cardNo) && bankService.validatePIN(cardNo, pin)) {
                        System.out.print("Enter Amount to Withdraw: ");
                        double amount = sc.nextDouble();
                        bankService.withdraw(cardNo, amount);
                    }
                }
                
                case 5 -> {
                    System.out.print("Enter Card Number: ");
                    String cardNo = sc.next();
                    System.out.print("Enter pin: ");
                    int pin = sc.nextInt();
                    if(bankService.isCardExist(cardNo) && bankService.validatePIN(cardNo, pin)) {
                        System.out.print("Enter Amount to Pay: ");
                        double amount = sc.nextDouble();
                        bankService.swipePay(cardNo, amount);
                    }
                }
                
                default -> System.out.println("Invalid Choice.");
            }
        }
    }
}