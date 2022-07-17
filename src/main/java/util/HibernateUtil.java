package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory emf;

    private static EntityManager entityManager;

    private HibernateUtil() {
    }

    public static EntityManagerFactory getEmf() {

        if (emf == null)
            emf = Persistence.createEntityManagerFactory("tiny_twitter");

        return emf;

    }

    public static EntityManager getEntityManager() {

        if (entityManager == null) {
            entityManager = getEmf().createEntityManager();
        }

        return entityManager;

    }

    public static void closeEntityManager() {

        if (entityManager != null)
            entityManager.close();

    }

}