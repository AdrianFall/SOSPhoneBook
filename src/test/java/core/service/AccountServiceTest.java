package core.service;

import core.entity.Account;
import core.service.exception.AccountExistsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Adrian on 10/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/prod/spring-context.xml")
@Transactional
public class AccountServiceTest {

    /*JUnit 4.7: Per-Test rules article:
      http://www.infoq.com/news/2009/07/junit-4.7-rules*/
    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Autowired
    private AccountService accountService;

    Account firstAcc;

    @Before
    public void setup() throws Exception {
        Account acc = new Account();
        acc.setUsername("adrian");
        acc.setPassword("somepass");
        acc.setEmail("someemail");
        firstAcc = accountService.createAccount(acc);
    }

    @Test
    public void testFirstAccount() throws  Exception {
        assertNotNull(firstAcc);
        assertNotNull(firstAcc.getId());
        assertEquals("adrian", firstAcc.getUsername());
        assertEquals("somepass", firstAcc.getPassword());
        assertEquals("someemail", firstAcc.getEmail());
    }

    @Test
    public void testCreateAccountWithTheSameUsername() throws Exception {
        Account newAcc = new Account();
        newAcc.setUsername("adrian");
        newAcc.setPassword("somepasswordz");
        newAcc.setEmail("newemailzor");

        // Expect the AccountExistsException
        exception.expect(AccountExistsException.class);
        exception.expectMessage("Account username already exists.");
        accountService.createAccount(newAcc);
    }
}
