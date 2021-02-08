package JudgeV2.demo.model.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Length(min = 2, max = 10, message = "Username length must be minimum 2 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 10, message = "Password length must be minimum 3 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
