package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Timestamp;

public class Juniors extends BaseEntity{
    public String firstName;
    public String lastName;
    public boolean sex;
    public Timestamp birthday;
    public String country;
    public String email;
    public String school;
    public Image photo;
    public int user;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isSex() {
        return sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public ImageView getPhoto() {
        ImageView imageView=new ImageView(photo);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        return imageView;
    }

    public int getUser() {
        return user;
    }
}