package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory=new Configuration().configure("/resources/hibernate.cfg.xml")
            .buildSessionFactory();
    private static Session session=sessionFactory.openSession();

    public static Session getSession() {
        return session;
    }

    public static void refresh(){
        session.close();
        session = sessionFactory.openSession();
    }

    public static void shutdown(){
        session.close();
        sessionFactory.close();
    }
}