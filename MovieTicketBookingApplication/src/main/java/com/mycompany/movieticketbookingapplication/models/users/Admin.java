package com.mycompany.movieticketbookingapplication.models.users;

import com.mycompany.movieticketbookingapplication.enums.Privilege;
import com.mycompany.movieticketbookingapplication.enums.Role;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Admin extends User {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    private final Set<Privilege> privileges;
    
    public Admin(String username, String password, boolean superAdmin) {
        super(username, password, "AD" + idCounter.incrementAndGet(), Role.ADMIN);
        this.privileges = superAdmin ? EnumSet.allOf(Privilege.class) : EnumSet.noneOf(Privilege.class);
    }
    
    public void grantPrivilege(Privilege privilege) {
        privileges.add(privilege);
    }
    
    public void revokePrivilege(Privilege privilege) {
        privileges.remove(privilege);
    }
    
    public boolean hasPrivilege(Privilege privilege) {
        return privileges.contains(privilege);
    }
    
    public List<Privilege> getPrivileges() {
        return new ArrayList<>(privileges);
    }
}