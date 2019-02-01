import javafx.application.Application;
import javafx.stage.Stage;
import util.HibernateUtil;

public class Launcher extends Application {
    public static void main(String[] args){
        HibernateUtil.getSessionFactory();
        launch();
        HibernateUtil.shutdown();
    }

    @Override
    public void start(Stage primaryStage){
    }
}