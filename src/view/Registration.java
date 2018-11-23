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
import entities.*;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Registration {
    private Stage stage=new Stage();

    //left panel
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private RadioButton sexMen=new RadioButton("Men");
    private RadioButton sexWoman=new RadioButton("Woman");
    private DatePicker birthday=new DatePicker();
    private ComboBox<Country> country=new ComboBox<>();
    private TextField email=new TextField();
    private TextField school=new TextField();
    private ImageView photoView=new ImageView();
    private AtomicReference<File> photoFile=new AtomicReference<>();

    //right panel
    private TextField login=new TextField();
    private PasswordField password=new PasswordField();
    private Map<Integer, CheckBox> competences;

    public Registration() {
        HBox hBox=new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 750, 600));

        try {
            //left panel
            VBox leftBox=new VBox(10);
            hBox.getChildren().add(leftBox);

            ToggleGroup toggleGroup = new ToggleGroup();
            sexMen.setToggleGroup(toggleGroup);
            sexMen.setSelected(true);
            sexWoman.setToggleGroup(toggleGroup);

            Button addPhoto=new Button("Choose");
            addPhoto.setOnAction(e -> {
                FileChooser fileChooser=new FileChooser();
                photoFile.set(fileChooser.showOpenDialog(stage));
                if (photoFile.get() != null) {
                    photoView.setImage(new Image(photoFile.get().toURI().toString()));
                    photoView.setFitWidth(100);
                    photoView.setFitHeight(100);
                }
            });

            country.setItems(FXCollections
                    .observableArrayList(new Query<Country>(Country.class).getAll()));

            leftBox.getChildren()
                    .addAll(new Label("FirstName:"), firstName,
                            new Label("LastName:"), lastName,
                            new Label("Sex:"), sexMen, sexWoman,
                            new Label("Birthday:"), birthday,
                            new Label("Country:"), country,
                            new Label("Email:"), email,
                            new Label("School:"), school,
                            new Label("Photo:"), photoView, addPhoto);

            //right panel
            HBox rightBox=new HBox(10);
            hBox.getChildren().add(rightBox);

            //box competence
            VBox boxCompetences=new VBox(10);
            rightBox.getChildren()
                    .addAll(new Label("Competences"),
                            new ScrollPane(boxCompetences));

            competences=new Query<Competence>(Competence.class).getAll().stream()
                    .collect(Collectors.toMap(i -> i.id, s -> new CheckBox(s.name)));
            competences.values()
                    .forEach(boxCompetences.getChildren()::add);

            //buttons
            Button apply = new Button("Apply");
            apply.setOnAction(e -> insert());
            Button cancel = new Button("Cancel");
            cancel.setOnAction(e -> stage.close());

            rightBox.getChildren().addAll(
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
            Users users=new Users();
            users.login=login.getText();
            users.password=password.getText();
            users.role="junior";
            int userId=new Query<>(Users.class)
                    .insert(users);

            Juniors juniors=new Juniors();
            juniors.firstName=firstName.getText();
            juniors.lastName=lastName.getText();
            juniors.sex=sexMen.isSelected();
            juniors.birthday=Timestamp.valueOf(LocalDateTime.of(birthday.getValue(), LocalTime.now()));
            juniors.country=country.getSelectionModel().getSelectedItem().name;
            juniors.email=email.getText();
            juniors.school=school.getText();
            juniors.photo=new Image(photoFile.get().toURI().toString());
            juniors.user=userId;
            int juniorId=new Query<>(Juniors.class)
                    .insert(juniors);

            for (Map.Entry<Integer, CheckBox> competence: competences.entrySet()) {
                if(competence.getValue().isSelected()) {
                    CompetenceJuniors competenceJuniors=new CompetenceJuniors();
                    competenceJuniors.junior=juniorId;
                    competenceJuniors.competence=competence.getKey();
                    new Query<>(CompetenceJuniors.class)
                            .insert(competenceJuniors);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}