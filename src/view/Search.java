package view;

import dao.Query;
import entities.ShowJuniors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Search {
    private Stage stage=new Stage();
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private TableView<ShowJuniors> tableView = new TableView<>();

    public Search(){
        FlowPane pane=new FlowPane();
        stage.setScene(new Scene(pane, 1000, 500));

        Button buttonSearch=new Button("Search");
        buttonSearch.setOnAction(e->search());
        Button buttonExit=new Button("Exit");

        pane.getChildren()
                .addAll(new Label("FirstName"), firstName,
                        new Label("LastName"), lastName,
                        tableView,
                        buttonExit, buttonSearch);
        search();
        stage.show();
    }

    private void search(){
        try {
            Query<ShowJuniors> showJuniorsQuery=new Query<>(ShowJuniors.class,
                    "SELECT firstName, lastName, sex, birthday, country, email, school, photo, " +
                            " login, password " +
                            " FROM Juniors, Users" +
                            " WHERE Juniors.user=Users.id AND" +
                            " firstName LIKE '%"+firstName.getText()+"%'" +
                            " AND" +
                            " lastName LIKE '%"+lastName.getText()+"%'"
            );

            ObservableList<ShowJuniors> list=
                    FXCollections.observableArrayList(showJuniorsQuery
                            .getAll());

            tableView.setItems(list);
            tableView.getColumns().addAll(showJuniorsQuery.getColumns());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}