package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageCustomerController;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.CustomerManageOption;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.List;

public class ConsoleManageCustomerView {
    private final ConsoleInputUtil inputReader;
    private final IManageCustomerController manageCustomerController;
    
    private boolean running;
    
    public ConsoleManageCustomerView(IManageCustomerController manageCustomerController) {
        inputReader = new ConsoleInputUtil();
        this.manageCustomerController = manageCustomerController;
    }
    
    public void runManageCustomerView() {
        running = true;
        while(running) {
            CustomerManageOption choice = getCustomerManageOption();
            switch(choice) {
                case BLOCK -> handleBlockCustomer();
                case UNBLOCK -> handleUnblockCustomer();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private CustomerManageOption getCustomerManageOption() {
        System.out.println("1. Block Customer");
        System.out.println("2. Unblock Customer");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> CustomerManageOption.BLOCK;
            case 2 -> CustomerManageOption.UNBLOCK;
            case 0 -> CustomerManageOption.EXIT;
            default -> CustomerManageOption.INVALID;
        };
    }
    
    private void handleBlockCustomer() {
        List<String> nonBlockedCustomers = manageCustomerController.getNonBlockedCustomers();
        
        if(nonBlockedCustomers.isEmpty()) {
            System.out.println("No non blocked customer exist.");
            return;
        }
        
        for(int i = 0; i < nonBlockedCustomers.size();i++) {
            System.out.println(i + 1 + ". " + nonBlockedCustomers.get(i));
        }
        
        while(true) {
            int blockChoice = inputReader.readInt("Enter Choice to block customer: ");

            if(blockChoice <= 0 || blockChoice > nonBlockedCustomers.size()) {
                displayError("Invalid Customer Choice");
                continue;
            }
            
            manageCustomerController.blockCustomer(nonBlockedCustomers.get(blockChoice - 1));
            return;
        }
    }

    private void handleUnblockCustomer() {
        List<String> blockedCustomers = manageCustomerController.getBlockedCustomers();
        
        if(blockedCustomers.isEmpty()) {
            System.out.println("No blocked customer exist.");
            return;
        }
        
        for(int i = 0; i < blockedCustomers.size();i++) {
            System.out.println(i + 1 + ". " + blockedCustomers.get(i));
        }
        
        while(true) {
            int unblockChoice = inputReader.readInt("Enter Choice to unblock customer: ");

            if(unblockChoice <= 0 || unblockChoice > blockedCustomers.size()) {
                displayError("Invalid Customer Choice");
                continue;
            }
            
            manageCustomerController.unblockCustomer( blockedCustomers.get(unblockChoice - 1));
            return;
        }
    }

    private void handleExit() {
        running = false;
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}