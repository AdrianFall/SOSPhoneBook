package core.service;

import core.entity.Account;
import core.entity.VerificationToken;
import core.event.OnRegistrationCompleteEvent;
import core.service.exception.EmailExistsException;
import core.service.exception.EmailNotSentException;
import core.service.exception.UsernameExistsException;

/**
 * Created by Adrian on 09/05/2015.
 */
public interface AccountService {
    public Account createAccount(Account acc) throws UsernameExistsException, EmailExistsException;
    public Account updateAccount(Account acc);
    public VerificationToken createVerificationToken(Account acc, String token);
    public VerificationToken findVerificationToken(String token);

}
