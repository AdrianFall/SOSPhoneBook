package core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adrian on 09/05/2015.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping(method = RequestMethod.POST)
    public void createAccount() {

    }
}
