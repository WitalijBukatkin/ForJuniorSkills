package repository;

import model.User;
import util.SessionUtil;

public class UserRepository extends SessionUtil {
    
    public User getByLoginAndPassword(String login, String password) {
        return (User) getSession()
                .createQuery("FROM User WHERE login=?1 AND password=?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
    }
}
