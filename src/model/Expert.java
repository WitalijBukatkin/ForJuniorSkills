package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Expert extends NamedEntity{
    private ObjectProperty<User> user=new SimpleObjectProperty<>();

    @ManyToOne
    public User getUser() {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }
}