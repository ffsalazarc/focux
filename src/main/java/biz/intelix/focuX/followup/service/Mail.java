package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.core.UtilityService;
import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.Users;
import biz.intelix.focuX.followup.repository.CollaboratorRepository;
import biz.intelix.focuX.followup.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

@Service
public class Mail {
    
    private static final Logger log = LogManager.getLogger(Mail.class);
    private final CollaboratorRepository collaboratorRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UtilityService utilityService;


    @Autowired
    public Mail(CollaboratorRepository collaboratorRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder , UtilityService utilityService) {
        this.collaboratorRepository = collaboratorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder  = bCryptPasswordEncoder;
        this.utilityService = utilityService;
    }

    @Async
    public ResponseEntity<Map<String, Object>> sendMail(String email) {
        Map<String, Object> response = new HashMap<>();
        Properties prop = utilityService.readConstants();      

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(prop.getProperty("mail.email"), prop.getProperty("mail.password"));
            }
        });

        try {
                Optional<Collaborator> collaborator = collaboratorRepository.findCollaboratorByMail(email);
                Optional<Users> optionalUsers = userRepository.findByCollaboratorId(collaborator.get().getId());
                if (collaborator.isPresent() && optionalUsers.isPresent()){
                    Users users = optionalUsers.get();

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(prop.getProperty("mail.email")));
                    message.setRecipients(
                            Message.RecipientType.TO, InternetAddress.parse(collaborator.get().getMail()));
                    message.setSubject("FOCUX System support");

                    String newPassword = Base64.getEncoder().withoutPadding().encodeToString(UUID.randomUUID().toString().getBytes());

                    users.setPassword(passwordEncoder.encode(newPassword.substring(0, 11)));
                    userRepository.save(users);

                    MimeBodyPart mimeBodyPart = new MimeBodyPart();
                    mimeBodyPart.setContent(templateGenerator(collaborator.get().getName(), collaborator.get().getLastName(), newPassword.substring(0, 11)), "text/html; charset=utf-8");

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(mimeBodyPart);

                    message.setContent(multipart);

                    Transport.send(message);
                    response.put("success","Se envio la clave al correo: " + collaborator.get().getMail());
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
                } else {
                    response.put("error", "usuario o correo no estan registrados en la base de datos, por favor verificar, si no, llamar a soporte intelix");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }
        } catch (MessagingException exception) {
            log.error(exception.getLocalizedMessage());
            response.put("error","Error con el servidor al enviar el correo");
            response.put("description", exception.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String templateGenerator(String name, String lastName, String password) {
        String template = "<table width=\"98%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top:24px;\">"
                        +  "<tr>"
                        +   "<td>"
                        +   "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"5\">"
                        +   "<tr>"
                        +   "<td style=\"font-family: Arial;font-size: 13px;\">"
                        +   "Estimado(a) <b>"+ name + " " + lastName + "</b>:"
                        +   "<p><span>Su nueva contraseña es: <b>"+ password +"</b></span></p>"
                        +   "</td>"
                        +   "</tr>"
                        +   "</table>"
                        +   "</td>"
                        +   "</tr>"
                        +   "<td style=\"height: 262px;\"><img src=\"https://media-exp1.licdn.com/dms/image/C4E1BAQELbEyYT1Prtw/company-background_10000/0/1557956057950?e=2147483647&v=beta&t=OxU2FWJ0Y-oDAw1FJZevf9U1VaUXMhPHHHle8Sz4Oyg\" alt=\"intelix\" style=\"margin-bottom: 8px;\"></td>"
                        +   "<tr>"
                        +   "<td>"
                        +   "<table height=\"80\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"5\">"
                        +   "<tr valign=\"middle\">"
                        +   "<td>"
                        +   "<div align=\"justify\">"
                        +   "<span style=\"font-family: Helvetica,Arial,sans-serif;font-size: 10px;\">"
                        +    "Las\n"
                        +   "informaciones, conceptos y opiniones emitidas por este medio no\n"
                        +   "representan necesariamente la opini&#243;n de la Compa&#241;&#237;a, sus\n"
                        +   "accionistas o administradores. Por lo tanto, la Compa&#241;&#237;a, sus\n"
                        +   "accionistas y administradores no se hacen responsables por ninguna de las\n"
                        +   "informaciones o datos suministrados por este medio, ni del uso que se\n"
                        +   "haga de los mismos. Este correo electr&#243;nico est&#225;dirigido\n"
                        +   "exclusivamente a sus destinatarios y puede contener informaci&#243;n\n"
                        +   "privada y confidencial; por lo tanto, queda prohibido el copiado o\n"
                        +   "duplicado de este material, reserv&#225;ndose la Compa&#241;&#237;a el\n"
                        +   "derecho de ejercer las correspondientes acciones legales.&nbsp;"
                        +   "</span>"
                        +   "</div>"
                        +   "</td>"
                        +   "</tr>"
                        +   "</table>"
                        +   "</td>"
                        +   "</tr>"
                        +   "</table>";
        return template;
    }
}
