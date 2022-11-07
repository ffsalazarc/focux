package biz.intelix.focuX.followup.service;


import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.Roles;
import biz.intelix.focuX.followup.model.Users;
import biz.intelix.focuX.followup.repository.CollaboratorRepository;
import biz.intelix.focuX.followup.repository.RolesRepository;
import biz.intelix.focuX.followup.repository.UserRepository;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users us = userRepository.findByUsername(username);
        if (us == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(us.getUsername(), us.getPassword(),
                us.getIsActive() == 1,
                true,
                true,
                true, 
                getAuthorities(us.getRole()));
    }

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Roles role) {
        return getGrantedAuthorities(getPrivilege(role));
    }

    private String getPrivilege(Roles role) {
        return role.getName();
    }

    private List<GrantedAuthority> getGrantedAuthorities(String privilege) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(privilege));
        return authorities;
    }


    public boolean validatePassword(String username, String password) {
        Users users = userRepository.findByUsername(username);
        return passwordEncoder.matches(password, users.getPassword());
    }

    public ResponseEntity<Map<String, Object>> registerUsername(Users users)  {
        Map<String, Object> responseEntityMap = new HashMap<>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Roles role = rolesRepository.findRolesById(users.getRole().getId());

        Collaborator collaborator = collaboratorRepository.findCollaboratorById(users.getCollaborator().getId());

            if (collaborator.getUsers() == null) {
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                users.setRole(role);
                users.setCollaborator(collaborator);
                userRepository.save(users);
                responseEntityMap.put("success", "El usuario se ha agregado con exito");                
                responseEntity = new ResponseEntity<>(responseEntityMap, HttpStatus.CREATED);
            } else {
                responseEntityMap.put("error", "Usuario registrado o asociado con un colaborador");
                responseEntity = new ResponseEntity<>(responseEntityMap, HttpStatus.BAD_REQUEST);
            }

            return responseEntity;
    }

}
