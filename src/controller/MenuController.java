package controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController extends AbstractController{

    private static Stage stage=new Stage();
    private static User user;

    public TextField firstName;
    public TextField lastName;
    public ComboBox competence;

    public TableView juniorsTable;
    public TableView sponsorTable;
    public Label juniorsCount;
    public Label juniorsAVG;
    public Label countJuniorsForEachCompetence;
    public Label avgAgeForEachCompetence;

    private JuniorRepository juniorRepository=new JuniorRepository();
    private SponsorRepository sponsorRepository=new SponsorRepository();
    private CompetenceRepository competenceRepository=new CompetenceRepository();

    public static void show(User user){
        MenuController.user=user;
        stage.setTitle("General");
        AbstractController.show(stage,"/view/Menu.fxml");
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
    public void close() throws IOException {
        stage.close();
    }

    public void juniorClean(ActionEvent actionEvent) {
        firstName.clear();
        lastName.clear();
        competence.setValue(null);
        juniorSearch();
    }

    public void juniorSearch() {
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