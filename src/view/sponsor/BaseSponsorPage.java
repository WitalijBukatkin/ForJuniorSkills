package view.sponsor;

import entity.Sponsor;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public abstract class BaseSponsorPage {
    public Stage stage = new Stage();
    protected VBox box3 = new VBox(10);

    protected TextField name = new TextField();
    protected TextField description = new TextField();

    protected ImageView photoView = new ImageView();
    protected Button apply = new Button("Apply");

    protected Sponsor sponsor;

    BaseSponsorPage() {
        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(40));
        stage.setScene(new Scene(hBox, 400, 520));

        Button cancel = new Button("Cancel");
        Button addPhoto = new Button("Choose");

        try {
            apply.setOnAction(a -> {
                try {
                    validate();
                    onClickApply();
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
        hBox.getChildren()
                .addAll(box1, box2, box3);

        box1.getChildren()
                .addAll(new Label("Name:"), name,
                        new Label("Description:"), description,
                        new Label("Logo:"), photoView,
                        addPhoto,
                        apply, cancel);
    }

    protected void setSponsor() {
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

    protected abstract void onClickApply() throws Exception;
}