package view.main;

import entity.Sponsor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import util.Query;
import view.sponsor.SponsorAlteration;
import view.sponsor.SponsorRegistration;

public class TabSponsors {
    private Tab tab;
    private TableView<Sponsor> tableView = new TableView<>();

    TabSponsors(){
        tab=new Tab("Sponsors");
        FlowPane pane=new FlowPane();
        pane.setPadding(new Insets(10));
        tab.setContent(pane);

        Button buttonAdd=new Button("+");

        buttonAdd.setOnAction(e->{
            Stage stage1 = new SponsorRegistration().stage;
            stage1.showAndWait();
            show();
        });
        tableView.setOnMouseClicked(e-> {
            if(tableView.getSelectionModel().getSelectedItem()!=null) {
                Stage stage1 = new SponsorAlteration(tableView.getSelectionModel()
                        .getSelectedItem()).stage;
                stage1.showAndWait();
                show();
            }
        });

        pane.getChildren()
                .addAll(buttonAdd,
                        tableView);
        show();

    }

    private void show(){
        try {
            Query<Sponsor> sponsorQuery=new Query<>(Sponsor.class);

            ObservableList<Sponsor> list = FXCollections
                    .observableArrayList(sponsorQuery.getAll());

            tableView.setItems(list);
            tableView.getColumns().clear();
            tableView.getColumns().addAll(sponsorQuery.getColumns());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Table isn't correct!\n"+e.getMessage()).show();
        }
    }

    public Tab getTab() {
        return tab;
    }
}