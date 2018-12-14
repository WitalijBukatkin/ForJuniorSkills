package view.junior;

import entity.*;
import javafx.scene.control.CheckBox;
import util.Query;

import java.util.Map;

public class JuniorRegistration extends BaseJuniorPage {

    @Override
    protected void onClickApply() throws Exception{
        User user = getUser();
        user.id = new Query<>(User.class)
                .insert(user);

        Junior junior = new Junior();
        setJunior(junior, user);
        junior.id = new Query<>(Junior.class)
                .insert(junior);

        setSponsors(junior);
    }
}