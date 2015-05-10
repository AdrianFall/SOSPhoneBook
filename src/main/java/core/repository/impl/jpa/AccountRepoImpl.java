package core.repository.impl.jpa;

import core.entity.Account;
import core.repository.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Adrian on 10/05/2015.
 */
@Repository
public class AccountRepoImpl implements AccountRepo {

    @PersistenceContext
    private EntityManager emgr;

    @Override
    public Account createAccount(Account acc) {
        emgr.persist(acc);
        return acc;
    }
}
