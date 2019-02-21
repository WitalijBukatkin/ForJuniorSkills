package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.SessionUtil;

public class CountryRepository extends SessionUtil {

    public ObservableList getAll(){
        return FXCollections.observableArrayList(getSession()
                .createQuery("from Country")
                .getResultList());
    }
}