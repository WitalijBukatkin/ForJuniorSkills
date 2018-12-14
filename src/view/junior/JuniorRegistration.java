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

        new Query<SponsorJunior>(SponsorJunior.class).getStream()
                .filter(sponsorJunior -> sponsorJunior.junior.equals(junior.id))
                .forEach(sponsorJunior ->
                        {
                            try {
                                new Query<SponsorJunior>(SponsorJunior.class)
                                        .delete(sponsorJunior);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        for (Map.Entry<Integer, CheckBox> sponsor : sponsors.entrySet()) {
            if (sponsor.getValue().isSelected()) {
                SponsorJunior competenceJunior = new SponsorJunior();
                competenceJunior.junior = junior.id;
                competenceJunior.sponsor = sponsor.getKey();
                new Query<>(SponsorJunior.class)
                        .insert(competenceJunior);
            }
        }
    }
}