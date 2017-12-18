package com.java.dao;

import com.java.model.Preference;
import com.java.model.PreferenceCompoundPk;
import com.java.persistance.PersistenceUtil;

import javax.persistence.*;
import java.util.List;

public class PreferenceDAO {
    protected EntityManager entityManager = PersistenceUtil.getEntityManager();

    public Preference getById(long projectId, long studentId) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Preference where project_id=:projectID and id_student=:studentID");
        query.setParameter("projectID", projectId);
        query.setParameter("studentID", studentId);


        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }

        txn.commit();

        return (Preference) list.get(0);
    }


    public List<Preference> getAll() {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Preference");

        txn.commit();

        return (List<Preference>) query.getResultList();
    }

    public void delete(Preference value) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(value);
        txn.commit();
        entityManager.close();
    }

    public void persist(Preference entity) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(entity);
        txn.commit();
    }

    public Double averagePreference(Long projectId) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("select avg(p.noPref) from Preference p where p.id.projectId=:projectID");
        query.setParameter("projectID", projectId);

        txn.commit();
        return (Double) query.getSingleResult();
    }
}
