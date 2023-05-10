package app.model.binding;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
    @NotEmpty
    @Size(min = 3)
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3)
    private String fullname;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;
    @NotEmpty
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public UserRegistrationDto setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
