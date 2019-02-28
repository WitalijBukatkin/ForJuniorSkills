package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.SessionUtil;

public class CompetenceRepository extends SessionUtil {

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getSession()
                .createQuery("from Competence")
                .getResultList());
    }
}