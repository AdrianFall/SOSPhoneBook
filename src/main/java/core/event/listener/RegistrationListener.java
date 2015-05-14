package core.event.listener;

import core.entity.Account;
import core.entity.VerificationToken;
import core.event.OnRegistrationCompleteEvent;
import core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * Created by Adrian on 14/05/2015.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        System.out.println("Registration Listener - onApplicationEvent");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        try {
            Account acc = event.getAccount();
            String token = UUID.randomUUID().toString(); // UUID - A class that represents an immutable universally unique identifier (UUID). A UUID represents a 128-bit value.
            VerificationToken createdToken = accountService.createVerificationToken(acc, token);


            System.out.println("VerificationToken " + ((createdToken == null) ? "not created" : "created and id is: " + createdToken.getId()));


            String recipentEmail = acc.getEmail();
            String subject = "SOSPhoneBook Registration Confirmation.";
            String confirmationURL = event.getAppUrl() + "/registrationConfirm?token=" + token;
            System.out.println("Pre getting message");

            String msg = messageSource.getMessage("regSucc", null, event.getLocale());
            System.out.println("The message is: " + msg);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(recipentEmail);
            mailMessage.setSubject(subject);
            mailMessage.setText(msg + " " + "http://localhost:8082" + confirmationURL);


            System.out.println("Sending mail to " + recipentEmail);
            // Send the mail
            mailSender.send(mailMessage);
        } catch (NoSuchMessageException nsme) {
            System.err.println("No such message in the .properties file");
            nsme.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error while trying to send email");
            e.printStackTrace();
        }
    }
}
