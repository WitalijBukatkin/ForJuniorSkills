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
import dao.Query;
import dao.entity.*;

import java.sql.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class Registration {
    private Stage stage=new Stage();

    //left panel
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private ComboBox<String> sex=new ComboBox<>(
            FXCollections.observableArrayList("Men", "Woman"));
    private DatePicker birthday=new DatePicker();
    private ComboBox<Country> country=new ComboBox<>();
    private TextField email=new TextField();
    private TextField school=new TextField();
    private ImageView photoView=new ImageView();

    //right panel
    private TextField login=new TextField();
    private PasswordField password=new PasswordField();
    private Map<Integer, CheckBox> sponsors;
    private ComboBox<Competence> competence=new ComboBox<>();

    public Registration() {
        HBox hBox=new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 750, 600));

        try {
            //left panel
            VBox leftBox=new VBox(10);
            hBox.getChildren().add(leftBox);

            country.setItems(FXCollections
                    .observableArrayList(new Query<Country>(Country.class).getAll()));

            leftBox.getChildren()
                    .addAll(new Label("FirstName:"), firstName,
                            new Label("LastName:"), lastName,
                            new Label("Sex:"), sex,
                            new Label("Birthday:"), birthday,
                            new Label("Country:"), country,
                            new Label("Email:"), email,
                            new Label("School:"), school);

            //right panel
            VBox rightBox=new VBox(10);
            hBox.getChildren().add(rightBox);

            competence.setItems(FXCollections
                    .observableArrayList(new Query<Competence>(Competence.class).getAll()));

            Button addPhoto=new Button("Choose");
            addPhoto.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                photoView.setImage(new Image(fileChooser.showOpenDialog(stage).toURI().toString()));
                photoView.setFitWidth(100);
                photoView.setFitHeight(100);
            });

            //box sponsors
            VBox boxSponsors=new VBox(10);
            rightBox.getChildren()
                    .addAll(new Label("Sponsor"),
                            new ScrollPane(boxSponsors));

            //TODO: full query use for fix NullPointerException, because fields 'logo' is empty in Sponsor table
            sponsors=new Query<Sponsor>(Sponsor.class, "SELECT id, name FROM Sponsor").getAll().stream()
                    .collect(Collectors.toMap(i -> i.id, s -> new CheckBox(s.name)));
            sponsors.values()
                    .forEach(boxSponsors.getChildren()::add);

            //buttons
            Button apply = new Button("Apply");
            apply.setOnAction(e -> insert());
            Button cancel = new Button("Cancel");
            cancel.setOnAction(e -> stage.close());

            rightBox.getChildren().addAll(
                    new Label("Competence"), competence,
                    new Label("Photo:"), photoView,
                    addPhoto,
                    new Label("Login:"), login,
                    new Label("Password:"), password,
                    apply, cancel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.show();
    }

    private void insert(){
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}