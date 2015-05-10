package core.repository;

import core.entity.Account;

/**
 * Created by Adrian on 10/05/2015.
 */
public interface AccountRepo {

    public Account findAccount(Long id);
    public Account findAccountByUsername(String username);
    public Account createAccount(Account acc);

}
