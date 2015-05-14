package resources.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adrian on 14/05/2015.
 */
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("Authentication failure. Exception msg " + e.getMessage() + " " + e.getLocalizedMessage() + " ");


        if (e.getClass().isAssignableFrom(DisabledException.class) || e instanceof DisabledException) {
            System.out.println("DisabledException");
        }

        if (e instanceof  BadCredentialsException) {
            System.out.println("BadCredentialsException");
        }


    }
}
