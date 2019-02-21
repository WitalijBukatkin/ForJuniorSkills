package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Sponsor;
import util.SessionUtil;

public class SponsorRepository extends SessionUtil {

    public void save(Sponsor sponsor){
        getSession().save(sponsor);
    }

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getSession()
                .createQuery("from Sponsor")
                .getResultList());
    }
}