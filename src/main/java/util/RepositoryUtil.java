package util;

import exception.ChangeException;
import model.BaseEntity;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RepositoryUtil {
    protected EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    protected EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    public void update(BaseEntity entity) {
        try {
            getTransaction().begin();
            if (entity.isNew()) {
                getEntityManager().persist(entity);
            } else {
                getEntityManager().merge(entity);
            }
            getTransaction().commit();
        } catch (HibernateException e) {
            getTransaction().rollback();
            throw new ChangeException();
        }
    }

    public void remove(BaseEntity entity) {
        try {
            getTransaction().begin();
            getEntityManager().remove(entity);
            getTransaction().commit();
        } catch (HibernateException e) {
            getTransaction().rollback();
            throw new ChangeException();
        }
    }
}