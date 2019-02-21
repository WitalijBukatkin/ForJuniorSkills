import controller.LoginController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.Session;
import util.HibernateUtil;

public class Launcher extends Application {
    public static void main(String[] args){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            launch();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Не могу подключится к серверу!").showAndWait();
        }
        HibernateUtil.shutdown();
    }

    @Override
    public void start(Stage primaryStage){
        LoginController.show();
    }
}