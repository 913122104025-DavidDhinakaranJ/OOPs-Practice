package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.movieticketbookingapplication.enums.Role;
import java.util.concurrent.atomic.AtomicLong;

public class Customer extends User {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    public Customer(String username, String password) {
        super(username, password, "CUST" + idCounter.incrementAndGet(), Role.CUSTOMER);
    }
}