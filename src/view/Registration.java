package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Query;
import entity.*;

import java.sql.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class Registration {
    private Stage stage=new Stage();

    //first panel
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private ComboBox<String> sex=new ComboBox<>(
            FXCollections.observableArrayList("Men", "Woman"));
    private DatePicker birthday=new DatePicker();
    private ComboBox<Country> country=new ComboBox<>();
    private TextField email=new TextField();
    private TextField school=new TextField();

    //second panel
    private ImageView photoView=new ImageView();
    private CheckBox toolbox=new CheckBox();
    private Map<Integer, CheckBox> sponsors;
    private ComboBox<Competence> competence=new ComboBox<>();
    
    
    //last panel
    private TextField login=new TextField();
    private PasswordField password=new PasswordField();
    private PasswordField passwordRepeat=new PasswordField();

    public Registration() {
        HBox hBox=new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 750, 520));

        try {
            //left panel
            VBox firstBox=new VBox(10);

            country.setItems(FXCollections
                    .observableArrayList(new Query<Country>(Country.class).getAll()));

            firstBox.getChildren()
                    .addAll(new Label("FirstName:"), firstName,
                            new Label("LastName:"), lastName,
                            new Label("Sex:"), sex,
                            new Label("Birthday:"), birthday,
                            new Label("Country:"), country,
                            new Label("Email:"), email,
                            new Label("School:"), school);

            //right panel
            VBox secondBox=new VBox(10);

            competence.setItems(FXCollections
                    .observableArrayList(new Query<Competence>(Competence.class).getAll()));

            VBox boxSponsors=new VBox(10);
            secondBox.getChildren()
                    .addAll(new Label("Sponsor"),
                            new ScrollPane(boxSponsors));

            //TODO: full query use for fix NullPointerException, because fields 'logo' is empty in Sponsor table
            sponsors=new Query<Sponsor>(Sponsor.class, "SELECT id, name FROM Sponsor").getAll().stream()
                    .collect(Collectors.toMap(i -> i.id, s -> new CheckBox(s.name)));
            sponsors.values()
                    .forEach(boxSponsors.getChildren()::add);

            Button addPhoto=new Button("Choose");
            addPhoto.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                photoView.setImage(new Image(fileChooser.showOpenDialog(stage).toURI().toString()));
                photoView.setFitWidth(100);
                photoView.setFitHeight(100);
            });

            secondBox.getChildren().addAll(
                    new Label("Competence"), competence,
                    new Label("Toolbox:"), toolbox,
                    new Label("Photo:"), photoView,
                    addPhoto);

            VBox lastBox=new VBox(10);

            Button apply = new Button("Apply");
            apply.setOnAction(a -> {
                try {
                    validate();
                    insert();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error inserting! "+e.getMessage()).show();
                }
            });

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e -> stage.close());

            lastBox.getChildren().addAll(
                    new Label("Login:"), login,
                    new Label("Password:"), password,
                    new Label("Repeat password:"), passwordRepeat,
                    apply, cancel);

            hBox.getChildren().addAll(
                    firstBox, secondBox, lastBox
            );
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error opening! "+e.getMessage()).show();
        }

        stage.show();
    }

    private void validate() throws Exception{
        if(firstName.getText().isEmpty())
            throw new Exception("firstName is empty!");

        if(lastName.getText().isEmpty())
            throw new Exception("lastName is empty!");

        if(sex.getSelectionModel().isEmpty())
            throw new Exception("sex is empty!");

        if(birthday.getValue()==null)
            throw new Exception("birthday is empty!");

        if(country.getSelectionModel().isEmpty())
            throw new Exception("country is empty!");

        if(email.getText().isEmpty())
            throw new Exception("email is empty!");

        if(!email.getText().matches(".+@.+[.].+"))
            throw new Exception("email is'nt match template 'x@x.x'!");

        if(school.getText().isEmpty())
            throw new Exception("school is empty!");

        if(competence.getSelectionModel().isEmpty())
            throw new Exception("competence is empty!");

        if(photoView.getImage()==null)
            throw new Exception("photo is empty!");

        if(login.getText().isEmpty())
            throw new Exception("login is empty!");

        if(password.getText().isEmpty())
            throw new Exception("password is empty!");

        if(passwordRepeat.getText().isEmpty())
            throw new Exception("passwordRepeat is empty!");

        if(!password.getText().equals(passwordRepeat.getText()))
            throw new Exception("password and repeat not equals!");

        if(password.getText().length()<6)
            throw new Exception("password must contain min 6 chars");
        if(!password.getText().matches(".*[A-ZА-ЯЁ].*"))
            throw new Exception("password must contain min one uppercase chars");
        if(!password.getText().matches(".*[0-9].*"))
            throw new Exception("password must contain min one digit");
        if(!password.getText().matches(".*[!|@|#|$|%|^].*"))
            throw new Exception("password must contain one of '!', '@', '#', '$', '%', '^'");
    }

    private void insert() throws Exception{
        User user = new User();
        user.login = login.getText();
        user.password = password.getText();
        user.role = "junior";
        int userId = new Query<>(User.class)
                .insert(user);

        Junior junior = new Junior();
        junior.firstName = firstName.getText();
        junior.lastName = lastName.getText();
        junior.sex = sex.getSelectionModel().getSelectedIndex() == 0;
        junior.birthday = new Date(birthday.getValue().toEpochDay());
        junior.country = country.getSelectionModel().getSelectedItem().id;
        junior.competence = competence.getSelectionModel().getSelectedItem().id;
        junior.toolbox = toolbox.isSelected();
        junior.email = email.getText();
        junior.school = school.getText();
        junior.photo = photoView.getImage();
        junior.user = userId;
        int juniorId = new Query<>(Junior.class)
                .insert(junior);

        for (Map.Entry<Integer, CheckBox> sponsor: sponsors.entrySet()) {
            if(sponsor.getValue().isSelected()) {
                SponsorJunior competenceJunior = new SponsorJunior();
                competenceJunior.junior = juniorId;
                competenceJunior.sponsor = sponsor.getKey();
                new Query<>(SponsorJunior.class)
                        .insert(competenceJunior);
            }
        }
    }
}