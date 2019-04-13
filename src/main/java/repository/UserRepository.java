package repository;

import model.User;
import util.RepositoryUtil;

public class UserRepository extends RepositoryUtil {

    public User getByLoginAndPassword(String login, String password) {
        return (User) getEntityManager()
                .createQuery("FROM User WHERE login=?1 AND password=?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
    }
}