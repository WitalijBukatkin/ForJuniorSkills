package view;

import util.Query;
import entity.ShowJunior;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class Head {
    private Stage stage=new Stage();
    private TextField firstName=new TextField();
    private TextField lastName=new TextField();
    private TableView<ShowJunior> tableView = new TableView<>();

    public Head(){
        FlowPane pane=new FlowPane();
        stage.setScene(new Scene(pane, 1000, 500));

        Button buttonSearch=new Button("Search");
        buttonSearch.setOnAction(e->search());

        Button buttonAdd=new Button("Add");
        buttonAdd.setOnAction(e->new Registration());

        pane.getChildren()
                .addAll(new Label("FirstName"), firstName,
                        new Label("LastName"), lastName,
                        buttonSearch,
                        tableView,
                        buttonAdd);
        search();
        stage.show();
    }

    private void search(){
        try {
            Query<ShowJunior> showJuniorsQuery=new Query<>(ShowJunior.class,
                    "SELECT firstName, lastName, sex, birthday, email, country, school, toolbox, photo, competence," +
                            " login, password" +
                            " FROM Junior, User" +
                            " WHERE Junior.user=User.id"
            );

            ObservableList<ShowJunior> list=
                    FXCollections.observableArrayList(showJuniorsQuery
                            .getStream()
                            .filter(e ->
                                    e.firstName.toLowerCase()
                                            .matches(".*"+firstName.getText().toLowerCase()+".*") &&

                                            e.lastName.toLowerCase()
                                                    .matches(".*"+lastName.getText().toLowerCase()+".*"))
                            .collect(Collectors.toList())
                    );

            tableView.setItems(list);
            tableView.getColumns().clear();
            tableView.getColumns().addAll(showJuniorsQuery.getColumns());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}