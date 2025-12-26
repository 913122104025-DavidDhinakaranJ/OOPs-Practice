package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageCustomerController;
import com.mycompany.movieticketbookingapplication.repositories.IUserRepository;
import java.util.List;

public class ManageCustomerController implements IManageCustomerController {
    private final IUserRepository userRepository;
    
    public ManageCustomerController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getNonBlockedCustomers() {
        return userRepository.getNonBlockedCustomers();
    }

    @Override
    public List<String> getBlockedCustomers() {
        return userRepository.getBlockedCustomers();
    }

    @Override
    public void blockCustomer(String username) {
        userRepository.getUser(username).blockUser();
    }

    @Override
    public void unblockCustomer(String username) {
        userRepository.getUser(username).unblockUser();
    }
    
}