package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtil {

    public static void show(Stage stage, String viewName) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(ControllerUtil.class.getResource(viewName))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}