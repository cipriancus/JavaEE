package com.java.dao;

import com.java.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class StudentDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    public Student getById(Long id) {

        Query query = entityManager.createQuery("from Student where id=:entityId");
        query.setParameter("entityId", id);

        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }
        return (Student) list.get(0);
    }

    public List<Student> getAll() {
        Query query = entityManager.createQuery("from Student");
        return (List<Student>) query.getResultList();
    }

    public void delete(Student value) {
        entityManager.remove(entityManager.merge(value));
    }

    public void persist(Student entity) {
        entityManager.merge(entity);
    }
}
