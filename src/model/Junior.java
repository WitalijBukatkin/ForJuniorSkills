package model;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;
import util.ImageUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Junior extends BaseEntity{
    /*
        Properties
    */

    private final StringProperty firstName=new SimpleStringProperty();

    private final StringProperty lastName=new SimpleStringProperty();

    private final BooleanProperty sex=new SimpleBooleanProperty();

    private final ObjectProperty<LocalDate> birthday=new SimpleObjectProperty<>();

    private final StringProperty email=new SimpleStringProperty();

    private final ObjectProperty<Country> country=new SimpleObjectProperty<>();

    private final StringProperty school=new SimpleStringProperty();

    private final BooleanProperty toolbox=new SimpleBooleanProperty();

    private final ObjectProperty<User> user = new SimpleObjectProperty<>();

    private final ObjectProperty<ImageView> photoImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Competence> competence = new SimpleObjectProperty<>();

    private Set<Sponsor> sponsors=new HashSet<>();

    /*
        Getters and Setters
    */

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public boolean isSex() {
        return sex.get();
    }

    public BooleanProperty sexProperty() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex.set(sex);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty schoolProperty() {
        return school;
    }

    public BooleanProperty toolboxProperty() {
        return toolbox;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    @ManyToOne
    public Country getCountry() {
        return country.get();
    }

    public ObjectProperty<Country> countryProperty() {
        return country;
    }

    public void setCountry(Country country) {
        this.country.set(country);
    }

    @ManyToOne
    public User getUser() {
        return user.get();
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    @Lob
    public byte[] getPhoto() {
        return ImageUtil.save(photoImage.get());
    }

    public void setPhoto(byte[] photo) {
        photoImage.set(ImageUtil.get(photo));
    }

    public ObjectProperty<ImageView> photoImageProperty() {
        return photoImage;
    }

    @ManyToOne
    public Competence getCompetence() {
        return competence.get();
    }

    public ObjectProperty<Competence> competenceProperty() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence.set(competence);
    }

    @ManyToMany
    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getSchool() {
        return school.get();
    }

    public void setSchool(String school) {
        this.school.set(school);
    }

    public boolean isToolbox() {
        return toolbox.get();
    }

    public void setToolbox(boolean toolbox) {
        this.toolbox.set(toolbox);
    }

    @Override
    public String toString() {
        return lastName.get() + " " + firstName.get();
    }
}