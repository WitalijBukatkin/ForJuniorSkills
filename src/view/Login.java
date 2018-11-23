package view;

import dao.Query;
import dao.entity.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Login {
    private Stage stage;

    private TextField labelLogin=new TextField("root");
    private PasswordField labelPassword=new PasswordField();

    public Login() {
        stage = new Stage();
        FlowPane pane=new FlowPane();

        stage.setScene(new Scene(pane, 170, 120));
        stage.setTitle("Auth");

        Button buttonLogin=new Button("Login");
        buttonLogin.setOnAction(e -> onLoginClick());
        labelPassword.setOnAction(e -> onLoginClick());

        Button buttonCancel=new Button("Cancel");
        buttonCancel.setOnAction(e->stage.close());

        pane.getChildren()
                .addAll(new Label("Login"), labelLogin,
                        new Label("Password"), labelPassword,
                        buttonLogin, buttonCancel);

        stage.show();
    }

    private void onLoginClick(){
        try {
            User user =new Query<User>(User.class).getAll().stream()
                    .filter(u->u.login.equals(labelLogin.getText()) && u.password.equals(labelPassword.getText()))
                    .findFirst().orElseGet(User::new);
            if(user.role.equals("admin") || user.role.equals("expert")) {
                stage.hide();
                new Menu();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Вам не разрешено входить!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Логин или пароль некоректны!").show();
        }
    }
}