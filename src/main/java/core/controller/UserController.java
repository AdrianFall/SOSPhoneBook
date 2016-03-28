package core.controller;

import core.service.AccountService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Adrian on 26/03/2016.
 */

@RestController
public class UserController {

    @Autowired
    AccountService accountService;

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public String user(Principal user) {
        System.out.println("/user");
        if (user != null)
            System.out.println("User is authenticated : " + user.getName());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", (user != null) ? user.getName() : "");
        return jsonObject.toString();
    }

}
