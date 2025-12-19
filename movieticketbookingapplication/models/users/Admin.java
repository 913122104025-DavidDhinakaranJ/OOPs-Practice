package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.movieticketbookingapplication.enums.Role;
import java.util.concurrent.atomic.AtomicLong;

public class Admin extends User {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    public Admin(String username, String password) {
        super(username, password, "AD" + idCounter.incrementAndGet(), Role.ADMIN);
    }
}