package core.event.listener;

import core.entity.Account;
import core.entity.PasswordResetToken;
import core.event.OnResetPasswordEvent;
import core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Adrian on 29/06/2015.
 */
@Component
public class ResetPasswordListener implements ApplicationListener<OnResetPasswordEvent> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnResetPasswordEvent onResetPasswordEvent) {
        System.out.println("ResetPasswordListener - onApplicationEvent");
        this.resetPasswordEmail(onResetPasswordEvent);
    }

    public void resetPasswordEmail(OnResetPasswordEvent event) {
        Account acc = event.getAccount();
        String token = UUID.randomUUID().toString(); // UUID - A class that represents an immutable universally unique identifier (UUID). A UUID represents a 128-bit value.

        PasswordResetToken createdPasswordResetToken = accountService.createPasswordResetToken(acc, token);

        String recipentEmail = acc.getEmail();
        String subject = messageSource.getMessage("request.reset.password.email.subject", null, event.getLocale());
        String confirmationURL = event.getAppUrl() + "/resetPassword?token=" + token;

        String msg = messageSource.getMessage("request.reset.password.email.message", null, event.getLocale());
        System.out.println("The email message is: " + msg);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipentEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(msg + " " + confirmationURL);


        System.out.println("Sending mail to " + recipentEmail);
        // Send the mail
        mailSender.send(mailMessage);

    }
}
