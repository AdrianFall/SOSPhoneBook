package core.service.impl;

import core.entity.Account;
import core.repository.AccountRepo;
import core.service.AccountService;
import core.service.exception.EmailExistsException;
import core.service.exception.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Adrian on 10/05/2015.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account createAccount(Account acc) throws UsernameExistsException, EmailExistsException {
        if (accountRepo.findAccountByUsername(acc.getUsername()) != null) {
            throw new UsernameExistsException("Account username already exists.");
        } else if (accountRepo.findAccountByEmail(acc.getEmail()) != null) {
            throw new EmailExistsException("Email already exists.");
        }
        return accountRepo.createAccount(acc);
    }
}
