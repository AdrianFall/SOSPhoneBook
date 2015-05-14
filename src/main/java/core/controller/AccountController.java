package core.controller;

import core.entity.Account;
import core.entity.VerificationToken;
import core.event.OnRegistrationCompleteEvent;
import core.model.form.LoginForm;
import core.model.form.RegistrationForm;
import core.service.AccountService;
import core.service.EmailService;
import core.service.exception.EmailExistsException;
import core.service.exception.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Adrian on 09/05/2015.
 */
@Controller

public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    MessageSource messages;
    @Autowired
    EmailService emailService;


    public static final String MODEL_REG_FORM = "registrationForm";

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String registrationConfirm(@RequestParam("token") String token, WebRequest request, Model model){
        Locale locale = request.getLocale();

        VerificationToken verificationToken = accountService.findVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        Account acc = verificationToken.getAcc();
        Calendar cal = Calendar.getInstance();
        Long timeDiff = verificationToken.getExpiryDate().getTime() - cal.getTime().getTime();
        if (timeDiff <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        // Activate the acc
        acc.setEnabled(true);
        Account updatedAcc = accountService.updateAccount(acc);
        if (updatedAcc == null) {
            model.addAttribute("message", messages.getMessage("auth.message.updateUser", null, locale));
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        return "redirect:/login?lang=" + request.getLocale().getLanguage();
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getRegistrationForm(Model m) {
        System.out.println("Getting form account.");
        RegistrationForm tempRegForm = new RegistrationForm();
        tempRegForm.setEmail("def@au.lt");
        m.addAttribute("registrationForm",tempRegForm);
        return "registrationForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@Valid @ModelAttribute LoginForm loginForm,
                              BindingResult bResult,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            bResult.rejectValue("email", "invalidCredentials");
            model.addObject("error", "Invalid credentials.");
        }

        if (logout != null) {
            model.addObject("msg", "Logged out.");
        }

        model.setViewName("login");
        return model;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView createAccount(@Valid @ModelAttribute RegistrationForm regForm,
                                      BindingResult bResult, WebRequest request, Model m) {
        System.out.println("createAccount POSTv2");

        // Validate the form password & passConfirm match
        if (!regForm.getPassword().equals(regForm.getConfirmPassword())) {
            bResult.rejectValue("password", "passwordMismatch");
            bResult.rejectValue("confirmPassword", "passwordMismatch");
            System.out.println("Password match validation - failed.");
        }

        if (bResult.hasErrors()) {
            // Clean the password fields before returning the form again
            regForm.setPassword("");
            regForm.setConfirmPassword("");

            return new ModelAndView("registrationForm");
        }

        // Build up the Account entity (to be used for createAcc(arg))
        Account newAcc = new Account();
        newAcc.setEmail(regForm.getEmail());
        newAcc.setPassword(regForm.getPassword());


        try {// Attempt acc creation
            accountService.createAccount(newAcc);
            // Acc created

            String appUrl = request.getContextPath();

            emailService.sendConfirmationEmail(new OnRegistrationCompleteEvent(newAcc, request.getLocale(), appUrl));


        }  catch(EmailExistsException eee) {
            System.out.println("Catched EmailexistsException, attaching the rejected value to BindingREsult");
            bResult.rejectValue("email", "emailExists");
        } finally {
            // TODO change to different view upon account creation

            // Clean the password fields before returning the form again
            regForm.setPassword("");
            regForm.setConfirmPassword("");
            return new ModelAndView("registrationForm");
        }
       /* Account createdAcc = accountService.createAccount(newAcc);
        if (createdAcc == null) {
            bResult.rejectValue("username", "usernameExists");
            System.out.println("Account not created.");
        } else {
            System.out.println("Registered account. with id: " + createdAcc.getId());
        }
        // Clean the passwords from the regForm
        regForm.setPassword(null);
        //m.addAttribute("message", "Registered account for: " + regForm.toString());*/

    }
}
