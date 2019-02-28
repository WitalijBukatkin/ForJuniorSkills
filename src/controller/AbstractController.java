package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractController implements Closeable {

    protected static void show(Stage stage, String viewName){
        try {
            stage.setScene(new Scene(FXMLLoader.load(AbstractController.class.getResource(viewName))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}