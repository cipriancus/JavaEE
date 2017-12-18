package com.java.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class PersistenceUtil {

    private static EntityManagerFactory emf;

    private static ThreadLocal localEntity = new ThreadLocal();

    public static EntityManager createEntityManager() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("PersistenceUnit",new Properties());
        EntityManager em = emf.createEntityManager();
        localEntity.set(em);
        return em;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }

    public static EntityManager getEntityManager() {
        if(localEntity.get() == null)
            createEntityManager();
        return (EntityManager) localEntity.get();
    }
    public static void closeEntityManager() {
        EntityManager em = (EntityManager)localEntity.get();
        if (em != null && em.isOpen())
            em.close();
        localEntity.set(null);
    }
}
