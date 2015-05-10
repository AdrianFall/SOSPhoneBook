package core.service.impl;

import core.entity.Account;
import core.repository.AccountRepo;
import core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Adrian on 10/05/2015.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account createAccount(Account acc) {
        if(accountRepo.findAccountByUsername(acc.getUsername()) == null)
            return accountRepo.createAccount(acc);
        else
            return null;

    }
}
