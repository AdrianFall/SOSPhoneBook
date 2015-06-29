package core.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adrian on 27/06/2015.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String main() {
        System.out.println("MainController /main");
        return "main";
    }
}
