package core.controller;

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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
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
public class AccountControllerTest {

    private static final String MODEL_NAME = "registrationForm";

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
                .alwaysExpect(status().isOk())
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
                .andExpect(forwardedUrl("/WEB-INF/jsp/registrationForm.jsp"));
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

        mockMvc.perform(post("/account.jsp")
                .param("username", email)
                .param("password", password)
                .param("email", email))
                .andDo(print())
                // Model errors
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors(MODEL_NAME))
                //.andExpect(model().attributeErrorCount(MODEL_NAME, 2))
                .andExpect(model().attributeHasFieldErrors(MODEL_NAME, "email"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        String username = "username";
        String password = "passww";
        String email = "email@pattern.pl";

        mockMvc.perform(post("/account.jsp")
                .param("username", username)
                .param("password", password)
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
