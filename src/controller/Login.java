package controller;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AuthService;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class Login {
    public TextField login;
    public TextField password;

    public static void show(){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(Login.class.getResource("/view/Login.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginAction(ActionEvent actionEvent) {
        try(AuthService authService = new AuthService()){
            User user = authService.getUserByLoginAndPassword(login.getText(), password.getText());
            if(user.getRole().equals("admin")) {
                System.out.println(user.toString());
            } else{
                throw new AccessDeniedException("");
            }
        }
        catch (AccessDeniedException e)
        {
            new Alert(Alert.AlertType.ERROR,"У вас нет доступа!").showAndWait();
        }
        catch (NoResultException e){
            new Alert(Alert.AlertType.ERROR, "Неверный логин или пароль!").showAndWait();
        }
        catch(Throwable e){
            new Alert(Alert.AlertType.ERROR, "Не могу подключится к серверу!").showAndWait();
            System.exit(-1);
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}