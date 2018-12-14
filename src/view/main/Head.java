package view.main;

import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Head {
    public Head(){
        TabPane tabPane=new TabPane();
        Stage stage = new Stage();
        stage.setScene(new Scene(tabPane, 1200, 500));
        stage.show();

        TabJuniors tabJuniors=new TabJuniors();
        TabSponsors tabSponsors=new TabSponsors();
        TabInfo tabInfo = new TabInfo();

        tabPane.getTabs().addAll(
                tabJuniors.getTab(),
                tabSponsors.getTab(),
                tabInfo.getTab());
    }
}