package com.mycompany.bankapplication3.repositories;
import com.mycompany.bankapplication3.models.User;

public interface IUserRepository {
    void saveUser(User user);
    User findUser(String username);
    boolean isUsernameTaken(String username);
}