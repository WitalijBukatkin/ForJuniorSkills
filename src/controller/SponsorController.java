package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Junior;
import model.Sponsor;
import org.hibernate.HibernateException;
import repository.JuniorRepository;
import repository.SponsorRepository;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static util.ValidationUtil.isEmpty;
import static util.ValidationUtil.validator;

public class SponsorController extends AbstractController implements Initializable {
    private static Sponsor sponsor;
    private static final Stage stage=new Stage();

    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private ImageView logo;
    @FXML
    private ListView juniors;
    @FXML
    private Button logoView;

    private final SponsorRepository sponsorRepository=new SponsorRepository();
    private final JuniorRepository juniorRepository=new JuniorRepository();

    public static void show(Sponsor sponsor){
        SponsorController.sponsor=sponsor;
        stage.setTitle("Sponsor Edit");
        AbstractController.show(stage, "/view/Sponsor.fxml");
    }

    public void apply(){
        try {
            validate();

            sponsorRepository.openTransaction();
            sponsorRepository.save(sponsor);
            sponsorRepository.commit();
        } catch (ValidationException v){
            new Alert(Alert.AlertType.ERROR, v.getClass().getSimpleName() + ": "+ v.getMessage()).showAndWait();
        } catch (HibernateException h){
            new Alert(Alert.AlertType.ERROR, "Can’t save!").showAndWait();
            h.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(sponsor.isNew()) {
            sponsor.logoProperty().set(new ImageView());
        }

        juniors.setItems(juniorRepository.getAll());

        juniors.setCellFactory(CheckBoxListCell.forListView(e ->
                new SimpleBooleanProperty(sponsor.getJuniors().contains(e)) {{
                    addListener((ob, o, n) -> {
                        if (n) sponsor.getJuniors().add((Junior) e);
                        else sponsor.getJuniors().remove(e);
                    });
                }}
        ));

        name.textProperty().bindBidirectional(sponsor.nameProperty());
        description.textProperty().bindBidirectional(sponsor.descriptionProperty());
        logo.imageProperty().bindBidirectional(sponsor.logoProperty().get().imageProperty());
    }

    private void validate() throws ValidationException{
        validator(sponsor.getName() == null, name, isEmpty);
        validator(sponsor.getDescription() == null, description, isEmpty);
        validator(sponsor.getLogo() == null, logoView, isEmpty);
    }

    public void chooseLogo() {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose logo for form");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            logo.setImage(new Image(file.toURI().toString()));
        }
    }

    @Override
    public void close() {
        stage.close();
    }
}
