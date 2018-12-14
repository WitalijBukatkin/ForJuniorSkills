package view.sponsor;

import entity.Sponsor;
import javafx.scene.control.Button;
import util.Query;

public class SponsorAlteration extends SponsorRegistration {
    public SponsorAlteration(Sponsor sponsor){
        super();

        this.sponsor=sponsor;

        Button remove=new Button("Remove");
        box3.getChildren()
                .add(remove);

        remove.setOnAction(e -> onClickRemove());

        getSponsor();
    }

    private void getSponsor() {
        name.setText(sponsor.name);
        photoView.setImage(sponsor.logo);
        description.setText(sponsor.description);
    }

    private void onClickRemove(){
        try {
            new Query<Sponsor>(Sponsor.class)
                    .delete(sponsor);
            stage.close();
        } catch (Exception ignore) {
        }
    }

    @Override
    protected void onClickApply() throws Exception {
        setSponsor();

        new Query<Sponsor>(Sponsor.class)
                .update(sponsor);
        stage.close();
    }
}