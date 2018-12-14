package view.main;

import entity.Competence;
import entity.JuniorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import util.Query;
import view.junior.JuniorAlteration;
import view.junior.JuniorRegistration;

import java.util.stream.Collectors;

public class TabJuniors {
    private Tab tab;
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private ComboBox<Competence> competence=new ComboBox<>();
    private TableView<JuniorView> tableView = new TableView<>();

    TabJuniors(){
        tab=new Tab("Juniors");
        FlowPane pane=new FlowPane();
        pane.setPadding(new Insets(10));
        tab.setContent(pane);

        Button buttonAdd=new Button("+");
        Button buttonSearch=new Button(">");
        Button buttonClean=new Button("X");

        buttonSearch.setOnAction(e->search());
        buttonClean.setOnAction(e->clean());
        buttonAdd.setOnAction(e->{
            Stage stage1 = new JuniorRegistration().stage;
            stage1.showAndWait();
            search();
        });
        tableView.setOnMouseClicked(e-> {
            if(tableView.getSelectionModel().getSelectedItem()!=null) {
                Stage stage1 = new JuniorAlteration(tableView.getSelectionModel()
                        .getSelectedItem()).stage;
                stage1.showAndWait();
                search();
            }
        });

        try{
            competence.setItems(FXCollections
                    .observableArrayList(new Query<Competence>(Competence.class).getAll()));
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Competences are can't load\n"+e.getMessage()).show();
        }

        pane.getChildren()
                .addAll(buttonAdd,
                        new Label("FirstName"), firstName,
                        new Label("LastName"), lastName,
                        new Label("Competence"), competence,
                        buttonSearch, buttonClean,
                        tableView);
        search();

    }

    private void clean(){
        firstName.setText("");
        lastName.setText("");
        competence.setValue(null);
        search();
    }

    private void search(){
        try {
            Query<JuniorView> juniorsQuery=new Query<>(JuniorView.class);

            ObservableList<JuniorView> list = FXCollections
                    .observableArrayList(juniorsQuery.getStream()
                            .filter(j ->
                                    j.firstName.toLowerCase()
                                            .matches(".*" + firstName.getText().toLowerCase() + ".*") &&
                                            j.lastName.toLowerCase()
                                                    .matches(".*" + lastName.getText().toLowerCase() + ".*") &&
                                            (competence.getValue()==null || j.competenceName.equals(competence.getValue().name)))
                            .collect(Collectors.toList()));

            tableView.setItems(list);
            tableView.getColumns().clear();
            tableView.getColumns().addAll(juniorsQuery.getColumns());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Table isn't correct!\n"+e.getMessage()).show();
        }
    }

    public Tab getTab() {
        return tab;
    }
}