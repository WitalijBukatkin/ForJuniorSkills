package view;

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

public class SponsorRegistration {
    Stage stage = new Stage();

    private TextField name = new TextField();
    private TextField description = new TextField();

    private ImageView photoView = new ImageView();
    private Button apply = new Button("Apply");

    private Sponsor sponsor;

    SponsorRegistration(Sponsor sponsor){
        this();

        this.sponsor=sponsor;

        getSponsor();

        apply.setOnAction(a -> {
            try {
                validate();
                update();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error get Junior from DB!\n" + e.getMessage()).show();
            }
        });
    }

    SponsorRegistration() {
        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 400, 520));

        Button cancel = new Button("Cancel");
        Button addPhoto = new Button("Choose");

        try {
            apply.setOnAction(a -> {
                try {
                    validate();
                    insert();
                    stage.close();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error get Sponsor from DB!\n" + e.getMessage()).show();
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
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error opening form!\n" + e.getMessage()).show();
        }

        VBox box1 = new VBox(10);
        VBox box2 = new VBox(10);
        VBox box3 = new VBox(10);
        hBox.getChildren()
                .addAll(box1, box2, box3);

        box1.getChildren()
                .addAll(new Label("Name:"), name,
                        new Label("Description:"), description,
                        new Label("Logo:"), photoView,
                        addPhoto,
                        apply, cancel);
    }

    private void getSponsor() {
        name.setText(sponsor.name);
        photoView.setImage(sponsor.logo);
        description.setText(sponsor.description);
    }

    private void setSponsor() {
        sponsor.name= name.getText();
        sponsor.description = description.getText();
        sponsor.logo = photoView.getImage();
    }

    private void validate() throws Exception {
        if (name.getText().isEmpty())
            throw new Exception("name is empty!");

        if (photoView.getImage() == null)
            throw new Exception("photo is empty!");
    }

    private void update() throws Exception{
        setSponsor();

        new Query<Sponsor>(Sponsor.class)
                .update(sponsor);
        stage.close();
    }

    private void insert() throws Exception {
        sponsor = new Sponsor();
        setSponsor();
        new Query<>(Sponsor.class)
                .insert(sponsor);
    }
}