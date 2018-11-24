package view;

import entity.User;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import util.Query;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Login {
    private Stage stage;

    private TextField labelLogin=new TextField("root");
    private PasswordField labelPassword=new PasswordField();

    public Login() {
        stage = new Stage();
        GridPane pane=new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        stage.setScene(new Scene(pane));
        stage.setTitle("Log in");

        Button buttonLogin=new Button("Login");
        buttonLogin.setOnAction(e -> onLoginClick());
        labelPassword.setOnAction(e -> onLoginClick());

        Button buttonCancel=new Button("Cancel");
        buttonCancel.setOnAction(e->stage.close());

        pane.addColumn(0,
                new Label("Login"),
                new Label("Password"),
                buttonLogin);

        pane.addColumn(1,
                labelLogin,
                labelPassword,
                buttonCancel);

        stage.show();
    }

    private void onLoginClick(){
        try {
            User user =new Query<User>(User.class).getAll().stream()
                    .filter(u->u.login.equals(labelLogin.getText()) && u.password.equals(labelPassword.getText()))
                    .findFirst().orElseGet(User::new);
            if(user.role.equals("admin") || user.role.equals("expert")) {
                stage.hide();
                new Head();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Вам не разрешено входить!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Логин или пароль некоректны!").show();
        }
    }
}