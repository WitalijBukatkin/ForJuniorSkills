package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Competence;
import model.Junior;
import model.User;
import util.SessionUtil;

public class JuniorRepository extends SessionUtil {

    public void save(Junior junior){
        getSession().save(junior);
    }

    public ObservableList getByFirstNameAndLastNameAndCompetence(String firstName, String lastName, Competence competence){
        return FXCollections.observableArrayList(getSession()
                .createQuery("from Junior where firstName like concat('%', ?1, '%') and lastName like concat('%', ?2, '%') and competence_id like ?3")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, competence != null ? competence.getId() : "%")
                .getResultList());
    }

    public Junior getOfUser(User user){
        return (Junior) getSession()
                .createQuery("from Junior where user_id=?1")
                .setParameter(1, user.getId())
                .getSingleResult();
    }

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getSession()
                .createQuery("from Junior")
                .getResultList());
    }
}