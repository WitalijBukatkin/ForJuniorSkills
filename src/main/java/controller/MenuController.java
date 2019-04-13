package controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Competence;
import model.Junior;
import model.Sponsor;
import model.User;
import repository.CompetenceRepository;
import repository.JuniorRepository;
import repository.SponsorRepository;
import util.ControllerUtil;

import java.io.Closeable;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController implements Initializable, Closeable {

    private static final Stage stage=new Stage();
    private static User user;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ComboBox competence;

    @FXML
    private TableView juniorsTable;
    @FXML
    private TableView sponsorTable;
    @FXML
    private Label juniorsCount;
    @FXML
    private Label juniorsAVG;
    @FXML
    private Label countJuniorsForEachCompetence;
    @FXML
    private Label avgAgeForEachCompetence;

    private final JuniorRepository juniorRepository=new JuniorRepository();
    private final SponsorRepository sponsorRepository=new SponsorRepository();
    private final CompetenceRepository competenceRepository=new CompetenceRepository();

    public static void show(User user){
        MenuController.user=user;
        stage.setTitle("General");
        ControllerUtil.show(stage, "/fxml/Menu.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        competence.setItems(competenceRepository.getAll());

        stage.focusedProperty()
                .addListener((ob, o, n)-> update());

        update();
        getStatistic();
    }

    private void update(){
        juniorsTable.setItems(juniorRepository.getAll());
        sponsorTable.setItems(sponsorRepository.getAll());
    }

    /*
        Tab Juniors
    */
    public void juniorClick(MouseEvent mouseEvent) {
        Junior junior = (Junior) juniorsTable.getSelectionModel().getSelectedItem();
        if(junior!=null) {
            JuniorController.show(user, junior);
        }
    }

    public void juniorAdd(ActionEvent actionEvent) {
        JuniorController.show(user, new Junior());
    }

    @Override
    public void close() {
        stage.close();
    }

    public void juniorClean(ActionEvent actionEvent) {
        firstName.clear();
        lastName.clear();
        competence.setValue(null);
        juniorSearch();
    }

    @FXML
    private void juniorSearch() {
        juniorsTable.setItems(juniorRepository.getByFirstNameAndLastNameAndCompetence(
                firstName.getText(), lastName.getText(), (Competence) competence.getValue()));
    }

    /*
        Tab Sponsors
    */

    public void sponsorAdd(ActionEvent actionEvent) {
        SponsorController.show(new Sponsor());
    }

    public void sponsorClick(MouseEvent mouseEvent) {
        Sponsor sponsor=(Sponsor)sponsorTable.getSelectionModel().getSelectedItem();
        if(sponsor!=null){
            SponsorController.show(sponsor);
        }
    }

    /*
        Tab Statistics
     */

    private void getStatistic(){
        ObservableList<Junior> juniors=juniorRepository.getAll();

        juniorsCount.setText(juniors.size() + "");

        juniorsAVG.setText(
                juniors.stream()
                        .mapToInt(e -> Period.between(e.getBirthday(), LocalDate.now()).getYears())
                        .average().orElse(0) + "");

        Map<Competence, List<Junior>> integerListMap = juniors.stream()
                .collect(Collectors.groupingBy(Junior::getCompetence));

        countJuniorsForEachCompetence.setText(
                integerListMap.entrySet().stream()
                        .map(e->e.getKey() + " -> " + e.getValue().size())
                        .collect(Collectors.joining("\n")));

        avgAgeForEachCompetence.setText(
                integerListMap.entrySet().stream()
                        .map(e -> {
                            double avg=e.getValue().stream()
                                    .mapToInt(a-> Period.between(a.getBirthday(), LocalDate.now()).getYears())
                                    .average().orElse(0);
                            return e.getKey() + " -> " + avg;
                        })
                        .collect(Collectors.joining("\n")));
    }
}