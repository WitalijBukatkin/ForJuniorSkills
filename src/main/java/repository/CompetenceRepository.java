package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.RepositoryUtil;

public class CompetenceRepository extends RepositoryUtil {

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getEntityManager()
                .createQuery("from Competence")
                .getResultList());
    }
}