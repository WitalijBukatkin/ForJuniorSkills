package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("model");

    private static EntityManager entityManager = entityManagerFactory
            .createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void shutdown(){
        entityManagerFactory.close();
    }
}