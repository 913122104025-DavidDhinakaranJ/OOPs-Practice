package com.mycompany.movieticketbookingapplication.views.adminViews;

import com.mycompany.authlib.controller.AuthController;
import com.mycompany.authlib.views.ConsoleAuthView;
import com.mycompany.authlib.views.IAuthView;
import com.mycompany.movieticketbookingapplication.contexts.ApplicationContext;
import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageAdminController;
import com.mycompany.movieticketbookingapplication.enums.Privilege;
import com.mycompany.movieticketbookingapplication.enums.menuOptions.adminMenuOptions.AdminManageOption;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ConsoleManageAdminView {
    private final ConsoleInputUtil inputReader;
    private final ApplicationContext appContext;
    private final IManageAdminController manageAdminController;
    
    private boolean running;
    
    public ConsoleManageAdminView(IManageAdminController manageAdminController) {
        this.inputReader = new ConsoleInputUtil();
        this.appContext = ApplicationContext.getInstance();
        this.manageAdminController = manageAdminController;
    }
    
    public void runManageAdminView() {
        running = true;
        while(running) {
            AdminManageOption choice = getAdminManageOption();
            switch(choice) {
                case ADD_ADMIN -> handleAddAdmin();
                case MANAGE_PRIVILEGES -> handleManagePrivileges();
                case BLOCK_ADMIN -> handleBlockAdmin();
                case UNBLOCK_ADMIN -> handleUnblockAdmin();
                case EXIT -> handleExit();
                case INVALID -> handleInvalidChoice();
            }
        }
    }
    
    private AdminManageOption getAdminManageOption() {
        System.out.println("1. Add new admin");
        System.out.println("2. Manage Privileges");
        System.out.println("3. Block Admin");
        System.out.println("4. Unblock Admin");
        System.out.println("0. Exit");
        
        return switch(inputReader.readInt("Enter choice: ")) {
            case 1 -> AdminManageOption.ADD_ADMIN;
            case 2 -> AdminManageOption.MANAGE_PRIVILEGES;
            case 3 -> AdminManageOption.BLOCK_ADMIN;
            case 4 -> AdminManageOption.UNBLOCK_ADMIN;
            case 0 -> AdminManageOption.EXIT;
            default -> AdminManageOption.INVALID;
        };
    }

    private void handleAddAdmin() {
        IAuthView authView = new ConsoleAuthView(new AuthController(appContext.getUserRepository(), appContext.getAdminFactory()));
        authView.handleRegistration();
    }

    private void handleManagePrivileges() {
        Admin admin = getAdmin();
        
        List<Privilege> existingPrivileges = admin.getPrivileges();
        List<Privilege> nonExistingPrivileges = Arrays.stream(Privilege.values()).filter(p -> !existingPrivileges.contains(p)).toList();
        
        System.out.println("Grant Privileges: ");
        Set<Privilege> grantPrivileges = getPrivileges(nonExistingPrivileges);
                
        System.out.println("Revoke Privileges: ");
        Set<Privilege> revokePrivileges = getPrivileges(existingPrivileges);
        
        manageAdminController.updatePrivileges(admin, grantPrivileges, revokePrivileges);
        
        System.out.println("Privileges updated Successfully.");
    }

    private void handleBlockAdmin() {
        List<String> nonBlockedAdmins = manageAdminController.getNonBlockedAdmins();
        
        for(int i = 0; i < nonBlockedAdmins.size();i++) {
            System.out.println(i + 1 + ". " + nonBlockedAdmins.get(i));
        }
        
        while(true) {
            int blockChoice = inputReader.readInt("Enter Choice to block Admin: ");

            if(blockChoice <= 0 || blockChoice > nonBlockedAdmins.size()) {
                displayError("Invalid Admin Choice");
                continue;
            }
            
            manageAdminController.blockAdmin(nonBlockedAdmins.get(blockChoice - 1));
            return;
        }
    }

    private void handleUnblockAdmin() {
        List<String> blockedAdmins = manageAdminController.getBlockedAdmins();
        
        if(blockedAdmins.isEmpty()) {
            System.out.println("No blocked Admin exist.");
            return;
        }
        
        for(int i = 0; i < blockedAdmins.size();i++) {
            System.out.println(i + 1 + ". " + blockedAdmins.get(i));
        }
        
        while(true) {
            int unblockChoice = inputReader.readInt("Enter Choice to unblock Admin: ");

            if(unblockChoice <= 0 || unblockChoice > blockedAdmins.size()) {
                displayError("Invalid Admin Choice");
                continue;
            }
            
            manageAdminController.unblockAdmin( blockedAdmins.get(unblockChoice - 1));
            return;
        }
    }

    private void handleExit() {
        running = false;
    }

    private void handleInvalidChoice() {
        displayError("Invalid Choice");
    }
    
    private Admin getAdmin() {
        List<Admin> admins = manageAdminController.getAllAdmins();
        
        for(int i = 0; i < admins.size();i++) {
            System.out.println(i + 1 + ". " + admins.get(i).getUsername());
        }
        
        while(true) {
            int choice = inputReader.readInt("Enter Admin Choice: ") - 1;

            if(choice < 0 || choice >= admins.size()) {
                displayError("Invalid Admin Choice");
                continue;
            }
            
            return admins.get(choice);
        }
    }
    
    private Set<Privilege> getPrivileges(List<Privilege> privileges) {
        for(int i = 0; i < privileges.size();i++) {
            System.out.println(i + 1 + ". " + privileges.get(i));
        }
        
        String privilegesChoice = inputReader.readString("Enter Privilege Choices (Space Seperated): ");
        if(privilegesChoice.isBlank()) return EnumSet.noneOf(Privilege.class);
        
        Set<Privilege> privilegeList = EnumSet.noneOf(Privilege.class);
        for(String choice : privilegesChoice.trim().split("\\s+")) {
            try {
                int index = Integer.parseInt(choice);

                if(index <= 0 || index > privileges.size()) {
                    displayError("Invalid Privilege Choice: " + index + " Omitted");
                    continue;
                }
                
                privilegeList.add(privileges.get(index - 1));
            } catch(NumberFormatException e) {
                displayError("Invalid input");
            }
        }
        
        return privilegeList;
    }
    
    private void displayError(String message) {
        System.out.println("Error: " + message);
    }
}