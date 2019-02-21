package controller;

import javafx.stage.Stage;
import model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import repository.JuniorRepository;
import repository.UserRepository;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class LoginController extends AbstractController {
    public TextField login;
    public TextField password;

    private static Stage stage=new Stage();

    private UserRepository userRepository = new UserRepository();
    private JuniorRepository juniorRepository=new JuniorRepository();

    public static void show(){
        stage.setTitle("Login");
        AbstractController.show(stage,"/view/Login.fxml");
    }

    public void login(ActionEvent actionEvent) {
        try{
            User user = userRepository.getByLoginAndPassword(login.getText(), password.getText());
            if(!user.getRole().equals("expert")) {
                JuniorController.show(user, juniorRepository.getOfUser(user));
            } else{
                MenuController.show(user);
            }
            stage.close();
        }
        catch (NoResultException e){
            new Alert(Alert.AlertType.ERROR, "Неверный логин или пароль!").showAndWait();
        }
    }

    @Override
    public void close() throws IOException {
        stage.close();
    }
}