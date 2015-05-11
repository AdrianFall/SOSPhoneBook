package core.repository;

import core.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Adrian on 10/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/prod/spring-context.xml")
public class AccountRepoTest {

    @Autowired
    private AccountRepo repo;

    // First account
    private Account firstAcc;

    @Before
    @Transactional
    @Rollback(false) // Whether or not the transaction for the annotated method should be rolled back after the method has completed.
    public void setup() throws  Exception{
        firstAcc = new Account();
        firstAcc.setEmail("testemail");
        firstAcc.setUsername("hombre");
        firstAcc.setPassword("superhard");
        repo.createAccount(firstAcc);
    }

    @Test
    @Transactional
    public void findTheFirstAccountById() throws Exception {
        assertNotNull(repo.findAccount(firstAcc.getId()));
    }

    @Test
    @Transactional
    public void findAccountByUserName() throws Exception {
        assertNotNull(repo.findAccountByUsername(firstAcc.getUsername()));
    }

}
