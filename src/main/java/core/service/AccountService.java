package core.service;

import core.entity.Account;
import core.service.exception.EmailExistsException;
import core.service.exception.UsernameExistsException;

/**
 * Created by Adrian on 09/05/2015.
 */
public interface AccountService {
    public Account createAccount(Account acc) throws UsernameExistsException, EmailExistsException;

}
