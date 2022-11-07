package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.Users;
import biz.intelix.focuX.followup.model.jwt.JwtRequest;
import biz.intelix.focuX.followup.model.jwt.JwtResponse;
import biz.intelix.focuX.followup.repository.CollaboratorRepository;
import biz.intelix.focuX.followup.service.Mail;
import biz.intelix.focuX.followup.service.UserService;
import biz.intelix.focuX.followup.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/followup/user")
@CrossOrigin("*")
public class UserLoginController {

    private static final Logger log = LogManager.getLogger(UserLoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private Mail mail;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    //TODO: Metodo de login, metodo logout, metodo register

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody JwtRequest jwtRequest) throws Exception {
        Map<String, Object> responseEntityMap = new HashMap<>();
        String user = jwtRequest.getUsername().replace("@intelix.biz", "");
        authenticate(user, jwtRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(user);

        final String token = jwtTokenUtil.generateToken(userDetails);
        String email = jwtRequest.getUsername().trim()+"@intelix.biz";
        Optional<Collaborator> collaborator = collaboratorRepository.findCollaboratorByMail(email);
        responseEntityMap.put("token", token);
        responseEntityMap.put("username", user);
        responseEntityMap.put("authorization", userDetails.getAuthorities());
        responseEntityMap.put("collaborator", collaborator.get());
        return ResponseEntity.ok().body(responseEntityMap);

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users user) throws Exception {
        ResponseEntity<Map<String, Object>> response = userService.registerUsername(user);
        return response;
    }

    @PutMapping("/forgotpassword")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody Map<String, Object> body) {
        Map<String, Object> responseEntityMap = new HashMap<>();
        if (body.get("email") != null && !body.get("email").toString().isEmpty()) {
            ResponseEntity<Map<String, Object>> response = mail.sendMail(String.valueOf(body.get("email")));
            return response;
        } else {
            responseEntityMap.put("error", "valor nulo, ingrese correo");
            return new ResponseEntity<Map<String, Object>>(responseEntityMap, HttpStatus.BAD_REQUEST);
        }
    }
}
