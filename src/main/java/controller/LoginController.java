package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import repository.JuniorRepository;
import repository.UserRepository;
import util.ControllerUtil;

import javax.persistence.NoResultException;
import java.io.Closeable;

public class LoginController implements Closeable {
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    private static final Stage stage=new Stage();

    private final UserRepository userRepository = new UserRepository();
    private final JuniorRepository juniorRepository=new JuniorRepository();

    public static void show(){
        stage.setTitle("Login");
        ControllerUtil.show(stage, "/fxml/Login.fxml");
        stage.setResizable(false);
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