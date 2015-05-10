package core.model.form;

        import org.hibernate.validator.constraints.Email;
        import org.hibernate.validator.constraints.NotEmpty;

        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Pattern;
        import javax.validation.constraints.Size;

/**
 * Created by Adrian on 10/05/2015.
 */
public class RegistrationForm {


    @Size(min=3, max=30)
    @NotNull
    private String username;

    @NotNull
    @NotEmpty @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "Please enter a valid email format.")
    private String email;

    @Size(min = 5, max = 60)
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
