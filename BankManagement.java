import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class BankManagement {
    public static void main(String[] args) {
        Bank bank = Bank.getBank();
        Scanner sc = new Scanner(System.in);
        System.out.println("0. Exit\n1. Create Bank Account\n2. Get ATM Card\n3. Deposit Cash\n4. Withdraw Cash\n5. Swipe Shopping");
        while(true) {
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            switch(choice) {
                case 0 -> System.exit(0);
                
                case 1 -> {
                    System.out.print("Enter initial amount: ");
                    double initialAmount = sc.nextDouble();
                    bank.createBankAccount(initialAmount);
                }
                
                case 2 -> {
                    System.out.print("Enter bank account number: ");
                    String accNo = sc.next();
                    bank.issueATMCard(accNo);
                }
                
                case 3 -> {
                    System.out.print("Enter ATM Card Number: ");
                    String cardNo = sc.next();
                    if(bank.isAtmCardExist(cardNo)) {
                        System.out.print("Enter Amount to Deposit: ");
                        double amount = sc.nextDouble();
                        bank.deposit(cardNo, amount);
                    }
                }
                
                case 4 -> {
                    System.out.print("Enter ATM Card Number: ");
                    String cardNo = sc.next();
                    if(bank.isAtmCardExist(cardNo)) {
                        System.out.print("Enter Amount to Withdraw: ");
                        double amount = sc.nextDouble();
                        bank.withdraw(cardNo, amount);
                    }
                }
                
                case 5 -> {
                    System.out.print("Enter ATM Card Number: ");
                    String cardNo = sc.next();
                    if(bank.isAtmCardExist(cardNo)) {
                        System.out.print("Enter Amount to Pay: ");
                        double amount = sc.nextDouble();
                        bank.swipePay(cardNo, amount);
                    }
                }
                
                default -> System.out.println("Invalid Choice.");
            }
        }
    }
}

class Bank {    
    private static Bank bank;
    private static final double MINIMUM_BALANCE = 100;
    
    private final Map<String, BankAccount> bankAccounts;
    private final Map<String, ATMCard> atmCards;

    private Bank() {
        this.atmCards = new HashMap<>();
        this.bankAccounts = new HashMap<>();
    }

    public static Bank getBank() {
        if(bank == null) {
            bank = new Bank();
        }
        return bank;
    }

    private void addAtmCard(ATMCard atmCard) {
        this.atmCards.put(atmCard.getCardNo(), atmCard);
    }
    
    private void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.put(bankAccount.getAccNo(), bankAccount);
    }
    
    public void createBankAccount(double initialAmount) {
        if(hasMinimumBalance(initialAmount)) {
            BankAccount account = new BankAccount(initialAmount);
            addBankAccount(account);
            System.out.println("Bank account with account number \"" + account.getAccNo() + "\" is created.");
        } 
    }
    
    public void issueATMCard(String accNo) {
        if(bankAccounts.containsKey(accNo)) {
            ATMCard atmCard = bankAccounts.get(accNo).getATMCard();
            addAtmCard(atmCard);
            System.out.println("\"" + atmCard.getCardNo() + "\" is your ATM Card number.");
        }
        else {
            System.out.println("Bank account with account number \"" + accNo + "\" does not exist.");
        }
    }
    
    public void deposit(String cardNo, double amount) {
        BankAccount account = atmCards.get(cardNo).getAccount();
        account.deposit(amount);
        System.out.println("Balance Amount: USD " + account.getBalance());
    }
    
    public void withdraw(String cardNo, double amount) {
        if(!isMultipleOf5(amount)) return;
        
        ATMCard atmCard = atmCards.get(cardNo);
        BankAccount account = atmCard.getAccount();
        
        double transactionCharge = calculateTransactionCharge(amount);
        double totalDeduction = amount + transactionCharge;
        
        if(hasMinimumBalance(account.getBalance() - totalDeduction)) {
            account.deduct(totalDeduction);
            System.out.println("Transaction Charges: USD " + transactionCharge + "\nBalance Amount: USD " + account.getBalance());
        }
    }
    
    public void swipePay(String cardNo, double amount) {        
        ATMCard atmCard = atmCards.get(cardNo);
        BankAccount account = atmCard.getAccount();
        
        double cashBack = calculateCashBack(amount);
        double totalDeduction = amount - cashBack;
        
        if(hasMinimumBalance(account.getBalance() - totalDeduction)) {
            account.deduct(totalDeduction);
            System.out.println("Cashback: USD " + cashBack + "\nBalance Amount: USD " + account.getBalance());
        }
    }
    
    public boolean isAtmCardExist(String cardNo) {
        if(!atmCards.containsKey(cardNo)) {
            System.out.println("ATM Card with card number \"" + cardNo + "\" does not exist.");
            return false;
        }
        return true;
    }
    
    private boolean isMultipleOf5(double amount) {
        if(amount % 5 != 0) {
            System.out.println("Requesting amount must be a multiple of 5.");
            return false;
        }
        return true;
    }
    
    private boolean hasMinimumBalance(double amount) {
        if(amount < MINIMUM_BALANCE) {
            System.out.println("Minimum Balance of USD " + MINIMUM_BALANCE + " has to be maintained.");
            return false;
        }
        return true;
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
}

class ATMCard {
    private static final Random rand = new Random();
    private final String cardNo;
    private final BankAccount account;

    public ATMCard(BankAccount account) {
        this.cardNo = rand.nextLong(10000000, 100000000) + "";
        this.account = account;
    }
    
    public String getCardNo() {
        return cardNo;
    }
    
    public BankAccount getAccount() {
        return account;
    }
}

class BankAccount {
    private static final Random rand = new Random();
    private final String accNo;
    private double balance;
    private ATMCard atmCard;
    
    public BankAccount(double initialAmount) {
        this.accNo = rand.nextLong(100000, 1000000) + "";
        this.balance = initialAmount;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public ATMCard getATMCard() {
        if(atmCard == null) atmCard = new ATMCard(this);
        return atmCard;
    }
    
    public void deduct(double amount) {
        balance -= amount;
    }
}
