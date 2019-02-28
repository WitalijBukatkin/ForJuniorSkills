package util;

import org.hibernate.Session;

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