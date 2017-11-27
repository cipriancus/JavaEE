package com.java.dao;

import com.java.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ProjectDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    public Project getById(Long id) {
        Query query = entityManager.createQuery("from Project where id=:entityId");
        query.setParameter("entityId", id);

        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }
        return (Project) list.get(0);
    }


    public List<Project> getAll() {

        Query query = entityManager.createQuery("from Project");
        return (List<Project>) query.getResultList();
    }

    public void delete(Project value) {
        entityManager.remove(entityManager.merge(value));
    }

    public void persist(Project entity) {
        entityManager.merge(entity);
    }
}
