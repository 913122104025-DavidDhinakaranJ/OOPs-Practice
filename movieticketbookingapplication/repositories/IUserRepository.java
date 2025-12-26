package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.authlib.repositories.IAuthenticatableUserRepository;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import com.mycompany.movieticketbookingapplication.models.users.User;
import java.util.List;

public interface IUserRepository extends IAuthenticatableUserRepository {
    List<String> getNonBlockedCustomers();
    List<String> getBlockedCustomers();
    List<String> getNonBlockedAdmins();
    List<String> getBlockedAdmins();
    
    List<Admin> getAllAdmins();
    
    User getUser(String username);
}