package core.service.impl;

import core.entity.Account;
import core.repository.AccountRepo;
import core.service.AccountService;
import core.service.exception.AccountExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Adrian on 10/05/2015.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account createAccount(Account acc) throws AccountExistsException {
        if (accountRepo.findAccountByUsername(acc.getUsername()) != null) {
            throw new AccountExistsException("Account username already exists.");
        }
        return accountRepo.createAccount(acc);
    }

}
