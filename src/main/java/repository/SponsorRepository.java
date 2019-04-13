package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.RepositoryUtil;

public class SponsorRepository extends RepositoryUtil {

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getEntityManager()
                .createQuery("from Sponsor")
                .getResultList());
    }
}