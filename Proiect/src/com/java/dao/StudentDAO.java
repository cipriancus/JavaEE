package com.java.dao;

import com.java.model.Student;
import com.java.persistance.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    protected EntityManager entityManager = PersistenceUtil.getEntityManager();

    public Student getById(Long id) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Student where id=:entityId");
        query.setParameter("entityId", id);

        List list = query.getResultList();

        txn.commit();

        if (list.isEmpty()) {
            return null;
        }
        return (Student) list.get(0);
    }

    public Student getStudentByEmail(String email) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        Query query = entityManager.createQuery("from Student where email=:emailStud");
        query.setParameter("emailStud", email);

        List list = query.getResultList();

        txn.commit();

        if (list.isEmpty()) {
            return null;
        }

        return (Student) list.get(0);
    }

    public List<Student> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Student> from = criteriaQuery.from(Student.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManager.createQuery(select);
        List<Object> resultList = typedQuery.getResultList();

        final List<Student> studentList = new ArrayList<Student>();

        for(Object student:resultList){
            studentList.add((Student) student);
        }

        return studentList;
    }

    public void delete(Student value) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(value);
        txn.commit();
    }

    public void persist(Student entity) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(entity);
        txn.commit();
    }
}
