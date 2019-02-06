package view.main;

import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Head {
    public Head(){
        new Stage(){{
            setScene(new Scene(
                    new TabPane(){{
                        getTabs().addAll(
                                new TabJuniors().getTab(),
                                new TabSponsors().getTab(),
                                new TabInfo().getTab()
                        );
                    }}, 1200, 500));
        }}.show();
    }
}
