package com.mycompany.bankapplication3.models;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    private final String username;
    private final String password;
    private final String userId;
    private final List<String> accountNumbers;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userId = "CUST" + idCounter.incrementAndGet();
        this.accountNumbers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
    
    public boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
    
    public void addAccount(String accNo) {
        accountNumbers.add(accNo);
    }
    
    public boolean hasAccount(String accNo) {
        return accountNumbers.contains(accNo);
    }

    public List<String> getAccountNumbers() {
        return new ArrayList<>(accountNumbers);
    }
}