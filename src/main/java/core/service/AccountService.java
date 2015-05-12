package core.service;

import core.entity.Account;
import core.service.exception.AccountExistsException;
import core.service.exception.InvalidArgumentException;

/**
 * Created by Adrian on 09/05/2015.
 */
public interface AccountService {
    public Account createAccount(Account acc) throws AccountExistsException;

}
