package core.controller;

import core.entity.Account;
import core.model.form.RegistrationForm;
import core.service.AccountService;
import core.service.exception.AccountExistsException;
import core.service.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

/**
 * Created by Adrian on 09/05/2015.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    public static final String MODEL_REG_FORM = "registrationForm";

    @RequestMapping(method = RequestMethod.GET)
    public String getFormAccount(Model m) {
        System.out.println("Getting form account.");
        RegistrationForm tempRegForm = new RegistrationForm();
        tempRegForm.setUsername("testusername");
        tempRegForm.setEmail("email@em.ail");
        m.addAttribute("registrationForm",tempRegForm);
        return "registrationForm";
    }

/*    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String createAccount(@Valid @ModelAttribute("registrationForm") RegistrationForm regForm,
                                              BindingResult validationResult, Model m) {
        System.out.println("CREATE ACCOUNT.");

        if (validationResult.hasErrors()) {
            System.out.println("HAS ERRORS.");
            return "Has Errors";
        } else {
            System.out.println("Has NO Errors.");
            System.out.println("Email = " + regForm.getEmail());
            m.addAttribute("email", regForm.getEmail());
            return "No Errors";
        }
    }*/
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createAccount(@Valid @ModelAttribute RegistrationForm regForm, BindingResult bResult, Model m) {
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

        System.out.println("has no errors. And the email is : " + regForm.getEmail());
        // Build up the Account entity (to be used for createAcc(arg))
        Account newAcc = new Account();
        newAcc.setEmail(regForm.getEmail());
        newAcc.setPassword(regForm.getPassword());
        newAcc.setUsername(regForm.getUsername());



        try {// Attempt acc creation
            accountService.createAccount(newAcc);


        } catch(AccountExistsException aee) {
            bResult.rejectValue("username", "usernameExists");
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
