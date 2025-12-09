package com.mycompany.bankapplication2;
import com.mycompany.bankapplication2.bankaccounts.BankAccount;
import com.mycompany.bankapplication2.bankaccounts.CurrentAccount;
import com.mycompany.bankapplication2.bankaccounts.SavingsAccount;
import com.mycompany.bankapplication2.cards.Card;
import com.mycompany.bankapplication2.cards.CreditCard;
import java.util.HashMap;
import java.util.Map;

class BankService {    
    private static BankService bankService;
    
    private final Map<String, BankAccount> bankAccounts;
    private final Map<String, Card> cards;

    private BankService() {
        this.cards = new HashMap<>();
        this.bankAccounts = new HashMap<>();
    }

    public static BankService getBankService() {
        if(bankService == null) {
            bankService = new BankService();
        }
        return bankService;
    }

    private void addCard(Card card) {
        this.cards.put(card.getCardNo(), card);
    }
    
    private void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.put(bankAccount.getAccNo(), bankAccount);
    }
    
    public void createSavingsAccount(double initialAmount) {
        BankAccount account = new SavingsAccount(initialAmount);
        addBankAccount(account);
        System.out.println("Savings account with account number \"" + account.getAccNo() + "\" is created.");
    }
    
    public void createCurrentAccount(double initialAmount) {
        BankAccount account = new CurrentAccount(initialAmount);
        addBankAccount(account);
        System.out.println("Current account with account number \"" + account.getAccNo() + "\" is created.");
    }
    
    public void issueCreditCard(int pin) {
        Card card = new CreditCard(pin);
        addCard(card);
        System.out.println("\"" + card.getCardNo() + "\" is your Credit Card number.");
    }
    
    public void issueDebitCard(String accNo, int pin) {
        Card card = bankAccounts.get(accNo).getDebitCard(pin);
        addCard(card);
        System.out.println("\"" + card.getCardNo() + "\" is your Debit Card number.");
    }
    
    public void deposit(String cardNo, double amount) {
        Card card = cards.get(cardNo);
        
        card.pay(amount);
        card.displayBalance();
    }
    
    public void withdraw(String cardNo, double amount) {
        if(!isMultipleOf5(amount)) return;
        
        Card card = cards.get(cardNo);
        
        double transactionCharge = calculateTransactionCharge(amount);
        double totalDeduction = amount + transactionCharge;
        
        if(card.use(totalDeduction)) {
            displayTransactionCharge(transactionCharge);
            card.displayBalance();
        }
    }
    
    public void swipePay(String cardNo, double amount) {
        Card card = cards.get(cardNo);
        
        double cashback = calculateCashBack(amount);
        double totalDeduction = amount - cashback;
        
        if(card.use(totalDeduction)) {
            displayCashback(cashback);
            card.displayBalance();
        }
    }
    
    public boolean isBankAccountExist(String accNo) {
        if(bankAccounts.containsKey(accNo)) {
            return true;
        } else {
            System.out.println("Bank Account with account number \"" + accNo + "\" does not exist.");
            return false;
        }
    }
    
    public boolean isCardExist(String cardNo) {
        if(cards.containsKey(cardNo)) {
            return true;
        } else {
            System.out.println("Card with card number \"" + cardNo + "\" does not exist.");
            return false;
        }
    }
    
    public boolean validatePIN(String cardNo, int pin) {
        if(cards.get(cardNo).validatePin(pin)) {
            return true;
        } else {
            System.out.println("Invalid Pin");
            return false;
        }
    }
    
    private boolean isMultipleOf5(double amount) {
        if(amount % 5 == 0) {
            return true;
        } else {
            System.out.println("Requesting amount must be a multiple of 5.");
            return false;
        }
    }
    
    private double calculateTransactionCharge(double amount) {
        if(amount <= 100) {
            return amount * 2 / 100;
        }
        else {
            return amount * 4 / 100;
        }
    }
    
    private double calculateCashBack(double amount) {
        return amount * 1 / 100;
    }
    
    private void displayTransactionCharge(double transactionCharge) {
        System.out.println("Transaction Charge: USD " + transactionCharge);
    }
    
    private void displayCashback(double cashback) {
        System.out.println("Cashback: USD " + cashback);
    }
}