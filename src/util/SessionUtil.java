package util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Closeable;

public class SessionUtil{
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    public void openTransaction(){
        HibernateUtil.getSession().beginTransaction();
    }

    public void commit(){
        HibernateUtil.getSession().getTransaction().commit();
        HibernateUtil.refresh();
    }
}