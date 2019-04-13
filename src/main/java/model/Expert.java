package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
class Expert extends NamedEntity{
    private final ObjectProperty<User> user=new SimpleObjectProperty<>();

    @ManyToOne
    public User getUser() {
        return user.get();
    }

    public void setUser(User user) {
        this.user.set(user);
    }
}