package com.mycompany.movieticketbookingapplication.controllers.interfaces.adminControllersInterfaces;

import com.mycompany.movieticketbookingapplication.enums.Privilege;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import java.util.List;
import java.util.Set;

public interface IManageAdminController {
    void updatePrivileges(Admin admin, Set<Privilege> grantPrivileges, Set<Privilege> revokePrivileges);
    
    List<Admin> getAllAdmins();
    List<String> getNonBlockedAdmins();
    List<String> getBlockedAdmins();
    
    void blockAdmin(String username);
    void unblockAdmin(String username);
}