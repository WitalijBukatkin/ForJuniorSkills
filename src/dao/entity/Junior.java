package dao.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Date;

public class Junior extends BaseEntity{
    public String firstName;
    public String lastName;
    public Boolean sex;
    public Date birthday;
    public Integer country;
    public String email;
    public String school;
    public Boolean toolbox;
    public Image photo;
    public Integer user;
    public Integer competence;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean isSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public Boolean isToolbox() {
        return toolbox;
    }

    public Integer getCompetence() {
        return competence;
    }

    public ImageView getPhoto() {
        ImageView imageView=new ImageView(photo);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        return imageView;
    }

    public Integer getUser() {
        return user;
    }
}