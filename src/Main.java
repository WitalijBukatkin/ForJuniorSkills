import form.ShowAll;
import javafx.application.Application;
import javafx.stage.Stage;
import form.Login;

public class Main extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage){
        new ShowAll();
    }
}