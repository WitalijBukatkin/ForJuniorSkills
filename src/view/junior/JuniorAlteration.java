package view.junior;

import entity.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import util.Query;

import java.util.Map;

public class JuniorAlteration extends BaseJuniorPage {
    private Integer juniorId;
    private Integer userId;

    protected Button remove=new Button("Remove");

    public JuniorAlteration(JuniorView junior){
        super();

        juniorId=junior.id;
        userId=junior.user;


        box3.getChildren()
                .add(remove);

        remove.setOnAction(e -> onClickRemove());

        getJunior(junior);

        //if competence contains > 6 junior, then add competence to list
        Competence competence1=new Competence();
        competence1.name=junior.competenceName;
        competence1.id=junior.competence;
        if(!competence.getItems().contains(competence1))
            competence.getItems().add(competence1);
    }

    private void getJunior(JuniorView junior){
        firstName.setText(junior.firstName);
        lastName.setText(junior.lastName);
        sex.getSelectionModel()
                .select(junior.sex ? 0 : 1);
        birthday.setValue(junior.birthday.toLocalDate());
        country.getSelectionModel().select(
                country.getItems().stream()
                        .filter(c -> c.name.equals(junior.countryName))
                        .findFirst()
                        .orElse(null));
        email.setText(junior.email);
        school.setText(junior.school);

        try {
            sponsors.values()
                    .forEach(checkBox ->
                            checkBox.setSelected(junior.sponsors.contains(checkBox.getText())));
        } catch (Exception ignored) {}

        competence.getSelectionModel().select(
                competence.getItems().stream()
                        .filter(c -> c.name.equals(junior.competenceName))
                        .findFirst()
                        .orElse(null));
        toolbox.setSelected(junior.toolbox);
        photoView.setImage(junior.photo);
        login.setText(junior.login);
        password.setText(junior.password);
        passwordRepeat.setText(junior.password);

        try {
            Junior junior1=new Query<Junior>(Junior.class).getStream()
                    .filter(j -> j.email.equals(email.getText()))
                    .findFirst()
                    .orElse(new Junior());
            juniorId=junior1.id;
            userId=junior1.user;
        } catch (Exception ignored) {
            stage.close();
        }
    }

    private void onClickRemove(){
        try {
            Junior junior=new Junior();
            junior.id=juniorId;
            new Query<Junior>(Junior.class)
                    .delete(junior);
            stage.close();
        } catch (Exception ignore) {
        }
    }

    @Override
    protected void onClickApply() throws Exception {
        Junior junior=new Junior();
        junior.id=juniorId;
        junior.user=userId;

        User user = getUser();
        user.id = userId;
        setJunior(junior, user);

        new Query<User>(User.class)
                .update(user);
        new Query<Junior>(Junior.class)
                .update(junior);
        setSponsors(junior);
        stage.close();
    }
}