package core.service;

import core.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Adrian on 10/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/prod/spring-context.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;



    @Test
    public void testCreateAccount() throws  Exception {
        Account acc = new Account();
        acc.setUsername("adrian");
        acc.setPassword("somepass");
        acc.setEmail("someemail");
        Account createdAcc = accountService.createAccount(acc);
        assertNotNull(createdAcc);
        assertNotNull(createdAcc.getId());
        assertEquals("adrian", createdAcc.getUsername());
        assertEquals("somepass", createdAcc.getPassword());
        assertEquals("someemail", createdAcc.getEmail());
    }


}
