package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.*;

@Entity
public class User extends BaseEntity{
    private StringProperty login=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();
    private StringProperty role=new SimpleStringProperty();

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    @Override
    public String toString() {
        return "login: " + login.get() + " password: "+password.get();
    }
}