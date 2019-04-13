package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Country;
import model.Junior;
import model.User;
import org.hibernate.HibernateException;
import repository.CompetenceRepository;
import repository.CountryRepository;
import repository.JuniorRepository;
import repository.SponsorRepository;
import util.ControllerUtil;

import javax.xml.bind.ValidationException;
import java.awt.*;
import java.io.Closeable;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static util.BindingUtil.bindCheckBoxListToSet;
import static util.ValidationUtil.isEmpty;
import static util.ValidationUtil.validator;

public class JuniorController implements Initializable, Closeable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private RadioButton sex;
    @FXML private DatePicker birthday;
    @FXML private TextField email;
    @FXML private TextField school;
    @FXML private ComboBox<Country> country;
    @FXML private ListView sponsors;
    @FXML private CheckBox toolbox;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML private TextField repeatPassword;
    @FXML private ComboBox competence;
    @FXML private ImageView photo;
    @FXML private Button photoView;

    private static Junior junior;
    private static User user;
    private static final Stage stage=new Stage();

    private final JuniorRepository juniorRepository=new JuniorRepository();
    private final CountryRepository countryRepository=new CountryRepository();
    private final CompetenceRepository competenceRepository=new CompetenceRepository();
    private final SponsorRepository sponsorRepository=new SponsorRepository();

    public static void show(User user, Junior junior){
        JuniorController.user=user;
        JuniorController.junior=junior;
        stage.setTitle("Junior Edit");
        ControllerUtil.show(stage, "/fxml/Junior.fxml");
    }

    @Override
    public void close() {
        stage.close();
    }

    public void apply(){
        try {
            if(user==junior.getUser())
                throw new AccessDeniedException("Change denied");

            validate();

            juniorRepository.update(junior);

            stage.close();
        }
        catch (AccessDeniedException | ValidationException e){
            new Alert(Alert.AlertType.ERROR, e.getClass().getSimpleName() + ": "+ e.getMessage()).showAndWait();
        }
        catch (HibernateException e){
            new Alert(Alert.AlertType.ERROR, "Can’t update!").showAndWait();
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

        bindCheckBoxListToSet(sponsors, junior.getSponsors());

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
