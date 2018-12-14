package view.junior;

import entity.Junior;
import entity.JuniorView;
import javafx.scene.control.Hyperlink;
import util.Query;

import java.awt.*;
import java.net.URI;

public class JuniorPage extends JuniorAlteration{

    public JuniorPage(JuniorView juniorView) {
        super(juniorView);

        firstName.setEditable(false);
        lastName.setEditable(false);
        sex.setDisable(true);
        birthday.setDisable(true);
        country.setDisable(true);
        email.setEditable(false);
        school.setEditable(false);
        competence.setDisable(true);
        login.setEditable(false);
        password.setEditable(false);
        passwordRepeat.setEditable(false);
        toolbox.setDisable(true);

        sponsors.values()
                .forEach(e -> e.setDisable(true));

        apply.setVisible(false);
        remove.setVisible(false);
        addPhoto.setVisible(false);

        Hyperlink hyperlink=new Hyperlink("Open info of juniorskills");
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://juniorskills.com/info"));
            } catch (Exception ignore){
            }
        });

        Hyperlink hyperlinkOfCompetence=new Hyperlink("Open info of competence");
        hyperlinkOfCompetence.setOnAction(e -> {
            try {
                Junior junior=new Query<Junior>(Junior.class).getStream()
                        .filter(j -> j.email.equals(email.getText()))
                        .findFirst()
                        .orElse(new Junior());
                Desktop.getDesktop().browse(new URI("https://juniorskills.com/comp"+junior.competence));
            } catch (Exception ignore){
            }
        });

        box3.getChildren()
                .addAll(hyperlink, hyperlinkOfCompetence);
    }

    @Override
    protected void onClickApply(){
    }
}