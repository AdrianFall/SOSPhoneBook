package core.controller;

import core.model.form.RegistrationForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.assertNotNull;
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
@ContextConfiguration("classpath:spring/test-context.xml")
public class AccountControllerTest {

    @Mock
    private BindingResult mockBindingResult;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testMockMvcIsNotNull() throws Exception {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/account.jsp"))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/jsp/registrationForm.jsp"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccountWithEmptyParams() throws Exception {
       /* when(mockBindingResult.hasErrors()).thenReturn(true);*/

        mockMvc.perform(post("/account.jsp")
                        .param("email", "")
                        .param("username", "")
                        .param("password", "")
        )
                .andDo(print())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("registrationForm"))
                .andExpect(status().isOk());
                /*.andExpect(view().name("registrationForm"))
                .andExpect(model().hasErrors()*//*attributeHasErrors("registrationForm", "username", "password", "email")*//*);*/
    }

    @Test
    public void testCreateAccountWithWrongEmailPattern() throws Exception {
        String username = "username";
        String password = "passww";
        String email = "no@dot";

        mockMvc.perform(post("/account.jsp")
                .param("username", email)
                .param("password", password)
                .param("email", email))
                .andDo(print())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("registrationForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccount() throws Exception {
        String username = "username";
        String password = "passww";
        String email = "email@pattern.pl";

        mockMvc.perform(post("/account.jsp")
                        .param("username", email)
                        .param("password", password)
                        .param("email", email))
                .andDo(print())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeHasNoErrors("registrationForm"))
                .andExpect(status().isOk());
    }

}
