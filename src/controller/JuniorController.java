package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.hibernate.HibernateException;
import repository.*;

import javax.xml.bind.ValidationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static util.ValidationUtil.isEmpty;
import static util.ValidationUtil.validator;

public class JuniorController extends AbstractController {
    public TextField firstName;
    public TextField lastName;
    public RadioButton sex;
    public DatePicker birthday;
    public TextField email;
    public TextField school;
    public ComboBox country;
    public ListView sponsors;
    public CheckBox toolbox;
    public TextField login;
    public TextField password;
    public TextField repeatPassword;
    public ComboBox competence;
    public ImageView photo;
    public Button photoView;

    private static Junior junior;
    private static User user;
    private static Stage stage=new Stage();

    private UserRepository userRepository=new UserRepository();
    private JuniorRepository juniorRepository=new JuniorRepository();
    private CountryRepository countryRepository=new CountryRepository();
    private CompetenceRepository competenceRepository=new CompetenceRepository();
    private SponsorRepository sponsorRepository=new SponsorRepository();

    static void show(User user, Junior junior){
        JuniorController.user=user;
        JuniorController.junior=junior;
        stage.setTitle("Junior Edit");
        AbstractController.show(stage, "/view/Junior.fxml");
    }

    @Override
    public void close() throws IOException {
        stage.close();
    }

    public void apply(){
        try {
            if(user==junior.getUser())
                throw new AccessDeniedException("Change denied");

            validate();

            juniorRepository.openTransaction();
            userRepository.save(junior.getUser());
            juniorRepository.save(junior);
            juniorRepository.commit();

            stage.close();
        }
        catch (AccessDeniedException | ValidationException e){
            new Alert(Alert.AlertType.ERROR, e.getClass().getSimpleName() + ": "+ e.getMessage()).showAndWait();
        }
        catch (HibernateException e){
            new Alert(Alert.AlertType.ERROR, "Can’t save!").showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(junior.isNew()) {
            junior.setUser(new User());
            junior.getUser().setRole("junior");
            junior.photoImageProperty().set(new ImageView());
        }

        country.setItems(countryRepository.getAll());
        sponsors.setItems(sponsorRepository.getAll());
        competence.setItems(competenceRepository.getAll());

        sponsors.setCellFactory(CheckBoxListCell.forListView(e ->
                new SimpleBooleanProperty(junior.getSponsors().contains(e)) {{
                    addListener((ob, o, n) -> {
                        if (n) junior.getSponsors().add((Sponsor) e);
                        else junior.getSponsors().remove(e);
                    });
                }}
        ));

        firstName.textProperty().bindBidirectional(junior.firstNameProperty());
        lastName.textProperty().bindBidirectional(junior.lastNameProperty());
        sex.selectedProperty().bindBidirectional(junior.sexProperty());
        birthday.valueProperty().bindBidirectional(junior.birthdayProperty());
        email.textProperty().bindBidirectional(junior.emailProperty());
        school.textProperty().bindBidirectional(junior.schoolProperty());
        country.valueProperty().bindBidirectional(junior.countryProperty());
        toolbox.selectedProperty().bindBidirectional(junior.toolboxProperty());
        login.textProperty().bindBidirectional(junior.getUser().loginProperty());
        password.textProperty().bindBidirectional(junior.getUser().passwordProperty());
        competence.valueProperty().bindBidirectional(junior.competenceProperty());
        photo.imageProperty().bindBidirectional(junior.photoImageProperty().get().imageProperty());
    }

    private void validate() throws ValidationException {
        validator(junior.getFirstName() == null, firstName, isEmpty);
        validator(junior.getLastName() == null, lastName, isEmpty);
        validator(junior.getBirthday() == null, birthday, isEmpty);
        validator(junior.getCountry() == null, country, isEmpty);
        validator(junior.getEmail() == null, email, isEmpty);
        validator(junior.getSchool() == null, school, isEmpty);
        validator(junior.getCompetence() == null, competence, isEmpty);
        validator(junior.getPhoto() == null, photoView, isEmpty);
        validator(junior.getUser().getLogin() == null, login, isEmpty);
        validator(junior.getUser().getPassword() == null, password, isEmpty);

        validator(Period.between(junior.getBirthday(), LocalDate.now()).getYears()<14,
                birthday,": your're too young!");

        validator(!junior.getEmail().matches(".+@.+[.].+"),
                email," is'nt match template 'x@x.x'!");

        validator(!junior.getUser().getPassword().equals(repeatPassword.getText()),
                password," and repeat not equals!");

        validator(junior.getUser().getPassword().length() < 6,
                password," must contain min 6 chars");

        validator(!junior.getUser().getPassword().matches(".*[A-ZА-ЯЁ].*"),
                password, " must contain min one uppercase chars");

        validator(!junior.getUser().getPassword().matches(".*[0-9].*"),
                password, " must contain min one digit");

        validator(!junior.getUser().getPassword().matches(".*[!|@|#|$|%|^].*"),
                password, " must contain one of '!', '@', '#', '$', '%', '^'");
    }

    public void choosePhoto() {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose photo for form");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            photo.setImage(new Image(file.toURI().toString()));
        }
    }

    public void ofJuniorSkills(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://juniorskills.com/info"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ofCompetence(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://juniorskills.com/comp"+junior.getCompetence().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
