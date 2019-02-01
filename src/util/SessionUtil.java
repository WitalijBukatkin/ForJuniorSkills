package util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Closeable;

public class SessionUtil implements Closeable {
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final Transaction transaction = session.beginTransaction();

    protected Session getSession() {
        return session;
    }

    public void commit(){
        transaction.commit();
    }

    public void close(){
        session.close();
    }
}