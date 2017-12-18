package com.java.dao;

import com.java.model.Project;
import com.java.model.Student;
import com.java.persistance.PersistenceUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectDAO {
    protected EntityManager entityManager = PersistenceUtil.getEntityManager();

    public Project getById(Long id) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Project where id=:entityId");
        query.setParameter("entityId", id);

        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }

        txn.commit();

        return (Project) list.get(0);
    }


    public List<Project> getAll() {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Project");

        txn.commit();

        return (List<Project>) query.getResultList();
    }

    public List<Project> getAllProjects(){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Project> from = criteriaQuery.from(Project.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManager.createQuery(select);
        List<Object> resultList = typedQuery.getResultList();

        final List<Project> projectList=new ArrayList<Project>();


        for(Project project:projectList){
            projectList.add((Project) project);
        }

        txn.commit();

        return projectList;
    }

    public Project getProjectByName(String name){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Project where name=:nameProject");
        query.setParameter("nameProject", name);

        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }

        txn.commit();

        return (Project) list.get(0);
    }

    public void delete(Project value) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(entityManager.merge(value));
        txn.commit();
        entityManager.close();
    }

    public void persist(Project entity) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(entity);
        txn.commit();
    }

    public long countProjects(){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("select count(p) from Project p");

        txn.commit();

        return (Long) query.getSingleResult();
    }
}
