package com.mycompany.movieticketbookingapplication.contexts;

import com.mycompany.movieticketbookingapplication.models.users.User;
import java.util.Optional;

public class SessionContext {

    private User currentUser;

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }
}