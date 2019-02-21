package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController implements Initializable, Closeable {

    static void show(Stage stage, String viewName){
        try {
            stage.setScene(new Scene(FXMLLoader.load(AbstractController.class.getResource(viewName))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}