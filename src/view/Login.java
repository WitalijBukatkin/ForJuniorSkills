package view;

import entity.JuniorView;
import entity.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import util.Query;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.junior.JuniorPage;
import view.main.Head;

public class Login {
    private Stage stage = new Stage();
    private TextField login=new TextField("root");
    private PasswordField password=new PasswordField();
    private Button buttonLogin=new Button("Login");
    private Button buttonCancel=new Button("Cancel");

    public Login(){
        FlowPane pane=new FlowPane(10, 10);
        pane.setPadding(new Insets(10));

        stage.setScene(new Scene(pane, 200, 200));
        stage.setTitle("Auth");
        stage.show();

        pane.getChildren()
                .addAll(new Label("Login"), login,
                        new Label("Password"), password,
                        buttonLogin, buttonCancel);

        init();
    }

    private void init(){
        password.setOnAction(a -> buttonLogin.fire());
        buttonLogin.setOnAction(e -> onLoginClick());
        buttonCancel.setOnAction(e->stage.close());
    }

    private void onLoginClick(){
        try {
            User user = new Query<User>(User.class).getAll().stream()
                    .filter(u->u.login.equals(login.getText()) && u.password.equals(password.getText()))
                    .findFirst().orElseGet(User::new);

            switch(user.role) {
                case "admin":
                case "expert":
                    stage.hide();
                    new Head();
                    break;
                case "junior":
                    Stage stage = new JuniorPage(new Query<JuniorView>(JuniorView.class).getStream()
                            .filter(e-> e.login.equals(user.login))
                            .findFirst()
                            .orElse(new JuniorView())).stage;
                    stage.show();
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "Вам не разрешено входить!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Логин или пароль некоректны!").show();
        }
    }
}