package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import repository.JuniorRepository;
import repository.UserRepository;

import javax.persistence.NoResultException;

public class LoginController extends AbstractController {
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    private static final Stage stage=new Stage();

    private final UserRepository userRepository = new UserRepository();
    private final JuniorRepository juniorRepository=new JuniorRepository();

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
    public void close() {
        stage.close();
    }
}