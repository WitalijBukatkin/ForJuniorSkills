package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Competence;
import model.Junior;
import model.User;
import util.RepositoryUtil;

public class JuniorRepository extends RepositoryUtil {

    public ObservableList getByFirstNameAndLastNameAndCompetence(String firstName, String lastName, Competence competence){
        return FXCollections.observableArrayList(getEntityManager()
                .createQuery("from Junior where firstName like concat('%', ?1, '%') and lastName like concat('%', ?2, '%') and competence_id like ?3")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, competence != null ? competence.getId() : "%")
                .getResultList());
    }

    public Junior getOfUser(User user){
        return (Junior) getEntityManager()
                .createQuery("from Junior where user_id=?1")
                .setParameter(1, user.getId())
                .getSingleResult();
    }

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getEntityManager()
                .createQuery("from Junior")
                .getResultList());
    }
}