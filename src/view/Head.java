package view;

import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

class Head {
    Head(){
        TabPane tabPane=new TabPane();
        Stage stage = new Stage();
        stage.setScene(new Scene(tabPane, 1200, 500));
        stage.show();

        TabJuniors tabJuniors=new TabJuniors();
        tabPane.getTabs().add(tabJuniors.getTab());

        TabSponsors tabSponsors=new TabSponsors();
        tabPane.getTabs().add(tabSponsors.getTab());
    }
}