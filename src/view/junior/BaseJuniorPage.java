package view.junior;

import entity.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseJuniorPage {
    public Stage stage = new Stage();

    protected TextField firstName = new TextField();
    protected TextField lastName = new TextField();
    protected ComboBox<String> sex = new ComboBox<>();
    protected DatePicker birthday = new DatePicker();
    protected ComboBox<Country> country = new ComboBox<>();
    protected TextField email = new TextField();
    protected TextField school = new TextField();

    protected ImageView photoView = new ImageView();
    protected CheckBox toolbox = new CheckBox();
    protected Map<Integer, CheckBox> sponsors;
    protected ComboBox<Competence> competence = new ComboBox<>();
    protected TextField login = new TextField();
    protected PasswordField password = new PasswordField();
    protected PasswordField passwordRepeat = new PasswordField();
    protected Button apply = new Button("Apply");
    protected Button addPhoto = new Button("Choose");
    protected VBox box3 = new VBox(10);

    protected Integer juniorId;

    public BaseJuniorPage() {
        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 750, 520));

        Button cancel = new Button("Cancel");
        VBox boxSponsors = new VBox(10);

        try {
            //ComboBoxes
            sex.setItems(FXCollections
                    .observableArrayList("Men", "Woman"));
            country.setItems(FXCollections
                    .observableArrayList(new Query<Country>(Country.class).getAll()));

            List<Junior> juniorList=
                    new Query<Junior>(Junior.class).getAll();

            competence.setItems(FXCollections
                    .observableArrayList(new Query<Competence>(Competence.class).getStream()
                            .filter(competence -> juniorList.stream()
                                    .filter(junior -> junior.competence.equals(competence.id))
                                    .count() < 6)
                            .collect(Collectors.toList())));

            //Buttons
            apply.setOnAction(a -> {
                try {
                    validate();
                    onClickApply();
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Error get Junior from DB!\n" + e.getMessage()).show();
                }
            });
            cancel.setOnAction(a -> stage.close());
            addPhoto.setOnAction(a -> {
                FileChooser fileChooser = new FileChooser();
                photoView.setImage(new Image(fileChooser.showOpenDialog(stage).toURI().toString()));
                photoView.setFitWidth(100);
                photoView.setFitHeight(100);
            });

            photoView.setFitWidth(30);
            photoView.setFitHeight(30);

            //BoxSponsors
            sponsors = new Query<Sponsor>(Sponsor.class).getAll().stream()
                    .collect(Collectors.toMap(i -> i.id, s -> new CheckBox(s.name)));
            sponsors.values()
                    .forEach(boxSponsors.getChildren()::add);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error opening form!\n" + e.getMessage()).show();
        }

        VBox box1 = new VBox(10);
        VBox box2 = new VBox(10);
        hBox.getChildren()
                .addAll(box1, box2, box3);

        box1.getChildren()
                .addAll(new Label("FirstName:"), firstName,
                        new Label("LastName:"), lastName,
                        new Label("Sex:"), sex,
                        new Label("Birthday:"), birthday,
                        new Label("Country:"), country,
                        new Label("Email:"), email,
                        new Label("School:"), school);

        box2.getChildren()
                .addAll(new Label("Sponsor"), new ScrollPane(boxSponsors),
                        new Label("Competence"), competence,
                        new Label("Toolbox:"), toolbox,
                        new Label("Photo:"), photoView,
                        addPhoto);

        box3.getChildren()
                .addAll(new Label("Login:"), login,
                        new Label("Password:"), password,
                        new Label("Repeat password:"), passwordRepeat,
                        apply, cancel);
    }

    protected void setJunior(Junior junior, User user) throws Exception {
        junior.firstName = firstName.getText();
        junior.lastName = lastName.getText();
        junior.sex = sex.getSelectionModel().getSelectedIndex() == 0;
        junior.birthday = Date.valueOf(birthday.getValue());
        junior.country = country.getSelectionModel().getSelectedItem().id;
        junior.competence = competence.getSelectionModel().getSelectedItem().id;
        junior.toolbox = toolbox.isSelected();
        junior.email = email.getText();
        junior.school = school.getText();
        junior.photo = photoView.getImage();
        junior.user = user.id;
    }

    protected void setSponsors(Junior junior) throws Exception{
        new Query<SponsorJunior>(SponsorJunior.class).getStream()
                .filter(sponsorJunior -> sponsorJunior.junior.equals(junior.id))
                .forEach(sponsorJunior ->
                        {
                            try {
                                new Query<SponsorJunior>(SponsorJunior.class)
                                        .delete(sponsorJunior);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        for (Map.Entry<Integer, CheckBox> sponsor : sponsors.entrySet()) {
            if (sponsor.getValue().isSelected()) {
                SponsorJunior competenceJunior = new SponsorJunior();
                competenceJunior.junior = junior.id;
                competenceJunior.sponsor = sponsor.getKey();
                new Query<>(SponsorJunior.class)
                        .insert(competenceJunior);
            }
        }
    }

    protected void validate() throws Exception {
        if (firstName.getText().isEmpty())
            throw new Exception("firstName is empty!");

        if (lastName.getText().isEmpty())
            throw new Exception("lastName is empty!");

        if (sex.getSelectionModel().isEmpty())
            throw new Exception("sex is empty!");

        if (birthday.getValue() == null)
            throw new Exception("birthday is empty!");

        if(Period.between(birthday.getValue(), LocalDate.now()).getYears()<14)
            throw new Exception("your're too young!");

        if (country.getSelectionModel().isEmpty())
            throw new Exception("country is empty!");

        if (email.getText().isEmpty())
            throw new Exception("email is empty!");

        if (!email.getText().matches(".+@.+[.].+"))
            throw new Exception("email is'nt match template 'x@x.x'!");

        if (school.getText().isEmpty())
            throw new Exception("school is empty!");

        if (competence.getSelectionModel().isEmpty())
            throw new Exception("competence is empty!");

        if (photoView.getImage() == null)
            throw new Exception("photo is empty!");

        if (login.getText().isEmpty())
            throw new Exception("login is empty!");

        if (password.getText().isEmpty())
            throw new Exception("password is empty!");

        if (passwordRepeat.getText().isEmpty())
            throw new Exception("passwordRepeat is empty!");

        if (!password.getText().equals(passwordRepeat.getText()))
            throw new Exception("password and repeat not equals!");

        if (password.getText().length() < 6)
            throw new Exception("password must contain min 6 chars");

        if (!password.getText().matches(".*[A-ZА-ЯЁ].*"))
            throw new Exception("password must contain min one uppercase chars");

        if (!password.getText().matches(".*[0-9].*"))
            throw new Exception("password must contain min one digit");

        if (!password.getText().matches(".*[!|@|#|$|%|^].*"))
            throw new Exception("password must contain one of '!', '@', '#', '$', '%', '^'");
    }

    protected User getUser() {
        User user = new User();
        user.login = login.getText();
        user.password = password.getText();
        user.role = "junior";
        return user;
    }

    protected abstract void onClickApply() throws Exception;
}
