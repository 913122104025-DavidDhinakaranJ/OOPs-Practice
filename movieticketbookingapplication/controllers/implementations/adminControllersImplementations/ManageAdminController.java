package com.mycompany.movieticketbookingapplication.controllers.implementations.adminControllersImplementations;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces.IManageAdminController;
import com.mycompany.movieticketbookingapplication.enums.Privilege;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import com.mycompany.movieticketbookingapplication.repositories.IUserRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ManageAdminController implements IManageAdminController {
    private final IUserRepository userRepository;
    
    public ManageAdminController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void updatePrivileges(Admin admin, Set<Privilege> grantPrivileges, Set<Privilege> revokePrivileges) {
        grantPrivileges.forEach(privilege -> {
            admin.grantPrivilege(privilege);
        });
        revokePrivileges.forEach(privilege -> {
            admin.revokePrivilege(privilege);
        });
    }
    
    @Override
    public List<Admin> getAllAdmins() {
        return userRepository.getAllAdmins();
    }

    @Override
    public List<String> getNonBlockedAdmins() {
        return userRepository.getNonBlockedAdmins();
    }

    @Override
    public List<String> getBlockedAdmins() {
        return userRepository.getBlockedAdmins();
    }

    @Override
    public void blockAdmin(String username) {
        userRepository.getUser(username).blockUser();
    }

    @Override
    public void unblockAdmin(String username) {
        userRepository.getUser(username).unblockUser();
    }
    
}