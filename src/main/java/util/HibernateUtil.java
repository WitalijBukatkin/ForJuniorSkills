package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("model");

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown(){
        entityManagerFactory.close();
    }
}