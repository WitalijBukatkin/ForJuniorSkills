package domain;

public class Users extends BaseEntity{
    public String login;
    public String password;
    public String role;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return role;
    }
}