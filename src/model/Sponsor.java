package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import util.ImageUtil;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sponsor extends NamedEntity{
    /*
        Properties
    */

    private final ObjectProperty<ImageView> logo=new SimpleObjectProperty<>();
    private final StringProperty description=new SimpleStringProperty();
    private Set<Junior> juniors=new HashSet<>();

    /*
        Getters and Setters
    */

    @Lob
    public byte[] getLogo() {
        return ImageUtil.save(logo.get());
    }

    public void setLogo(byte[] logo) {
        this.logo.set(ImageUtil.get(logo));
    }

    public ObjectProperty<ImageView> logoProperty() {
        return logo;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @ManyToMany(mappedBy = "sponsors")
    public Set<Junior> getJuniors() {
        return juniors;
    }

    public void setJuniors(Set<Junior> juniors) {
        this.juniors = juniors;
    }
}