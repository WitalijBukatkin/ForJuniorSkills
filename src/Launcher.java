import view.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage){
        new Login();
    }
}