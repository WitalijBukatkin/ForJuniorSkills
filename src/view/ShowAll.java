package view;

import dao.Query;
import entities.ShowJuniors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ShowAll {
    private Stage stage=new Stage();

    public ShowAll(){
        stage.setTitle("Juniors");
        FlowPane layout = new FlowPane();
        stage.setScene(new Scene(layout, 1000, 500));

        try {
            Query<ShowJuniors> showJuniorsQuery=new Query<>(ShowJuniors.class,
                    "SELECT FirstName, LastName, Sex, Birthday, Country, Email, School, Photo," +
                            " Login, Password" +
                            " FROM Juniors, Users" +
                            " WHERE Juniors.User=Users.id"
            );

            ObservableList<ShowJuniors> list=
                    FXCollections.observableArrayList(showJuniorsQuery.getAll());

            TableView<ShowJuniors> tableView = new TableView<>();
            tableView.setItems(list);
            tableView.getColumns().addAll(showJuniorsQuery.getColumns());

            layout.getChildren().add(tableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }
}