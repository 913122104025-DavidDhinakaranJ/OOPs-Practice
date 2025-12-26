package com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces;

import java.util.List;

public interface IManageCustomerController {
    List<String> getNonBlockedCustomers();
    List<String> getBlockedCustomers();
    
    void blockCustomer(String username);
    void unblockCustomer(String username);
}