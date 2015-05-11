package core.controller;

import core.entity.Account;
import core.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Adrian on 10/05/2015.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test/test-context.xml")
@Transactional
public class AccountControllerTest {

    private static final String MODEL_NAME = "registrationForm";

    @InjectMocks
    AccountController controller;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .alwaysExpect(status().isOk())
                .build();
    }

    @Test
    public void testMockMvcIsNotNull() throws Exception {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetForm() throws Exception {
        mockMvc.perform(get("/account.jsp"))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/jsp/registrationForm.jsp"));
    }

    @Test
    public void testCreateAccountWithEmptyParams() throws Exception {
       /* when(mockBindingResult.hasErrors()).thenReturn(true);*/

        mockMvc.perform(postForm("", "", "", ""))
                .andDo(print())
                // Model errors
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors(MODEL_NAME))
                //.andExpect(model().attributeErrorCount(MODEL_NAME, 3))
                .andExpect(model().attributeHasFieldErrors(MODEL_NAME, "username", "password", "email"));

                /*.andExpect(view().name(MODEL_NAME))
                .andExpect(model().hasErrors()*//*attributeHasErrors(MODEL_NAME, "username", "password", "email")*//*);*/
    }

    @Test
    public void testCreateAccountWithWrongEmailPattern() throws Exception {
        String username = "username";
        String password = "passww";
        String email = "no@dot";

        mockMvc.perform(postForm(username, password, password, email))
                .andDo(print())
                // Model errors
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors(MODEL_NAME))
                //.andExpect(model().attributeErrorCount(MODEL_NAME, 2))
                .andExpect(model().attributeHasFieldErrors(MODEL_NAME, "email"));
    }

    @Test
    public void testCreateAccountWithMismatchingPassword() throws Exception {
        String username = "testuser";
        String password = "somepassword";
        String confirmPassword = "somepaszword";
        String email = "email@pattern.pl";

        mockMvc.perform(postForm(username, password, confirmPassword, email))
                .andDo(print())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors(MODEL_NAME))
                .andExpect(model().attributeHasFieldErrors(MODEL_NAME, "password", "confirmPassword"));


    }

    private MockHttpServletRequestBuilder postForm(String username, String password, String confirmPassword, String email) {
        return post("/account.jsp")
                .param("username", username)
                .param("password", password)
                .param("confirmPassword", confirmPassword);
    }

    @Test
    public void testCreateAccount() throws Exception {
        String username = "usernametestm";
        String password = "passww";
        String email = "email@pattern.pl";
        Account createdAcc = new Account(username, password, email);

        when(accountService.createAccount(any(Account.class))).thenReturn(createdAcc);

        mockMvc.perform(post("/account.jsp")
                .param("username", username)
                .param("password", password)
                .param("confirmPassword", password)
                .param("email", email))
                .andDo(print())
                // Model errors
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeHasNoErrors(MODEL_NAME))
                // Model attributes
                .andExpect(model().attribute(MODEL_NAME, hasProperty("email", is(email))))
                .andExpect(model().attribute(MODEL_NAME, (hasProperty("password", is(nullValue()))))) // do not expect the model to contain password property
                .andExpect(model().attribute(MODEL_NAME, hasProperty("username", is(username))));
    }

}
