package biz.intelix.focuX.followup.utils;

import biz.intelix.focuX.followup.model.Privilege;
import biz.intelix.focuX.followup.model.Roles;
import biz.intelix.focuX.followup.repository.PrivilegeRepository;
import biz.intelix.focuX.followup.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RolesRepository rolesRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_GOD", adminPrivileges);
        createRoleIfNotFound("ROLE_BASIC", Arrays.asList(readPrivilege));

        alreadySetup = true;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    Roles createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Roles role = rolesRepository.findByName(name);
        if (role == null) {
            role = new Roles();
            role.setName(name);
            role.setPrivileges(privileges);
            rolesRepository.save(role);
        }
        return role;
    }
}
