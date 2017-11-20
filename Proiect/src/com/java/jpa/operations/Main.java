package com.java.jpa.operations;

package jpa;

import jpa.entity.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.java.jpa.model.Preference;
import com.java.jpa.model.Student;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();
			//persistPerson(entityManager);
			persistStudent(entityManager);

			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}
	}



	/*private Date createDate(int day, int month, int year) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.DAY_OF_MONTH, day);
		gc.set(Calendar.MONTH, month - 1);
		gc.set(Calendar.YEAR, year);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return new Date(gc.getTimeInMillis());
	}*/

/*	private void addStudent(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> personRoot = query.from(Student.class);
		query.where(builder.and(builder.equal(personRoot.get("firstName"), "Homer"),
				builder.equal(personRoot.get("lastName"), "Simpson")));
		List<Student> resultList = entityManager.createQuery(query).getResultList();
		for (Student person : resultList) {
			Phone phone = new Phone();
			phone.setNumber("+49 1234 456789");
			entityManager.persist(phone);
			person.getPhones().add(phone);
			phone.setPerson(person);
		}
		transaction.commit();
	}*/

	private void persistStudent(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student person = new Student();
			person.setAddress("Musatini");
			person.setEmail("geo");
			person.setGender("female");
			person.setName("georgiana");
			entityManager.persist(person);
		
	
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
/*
	private void persistGeek(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Geek geek = new Geek();
		geek.setFirstName("Gavin");
		geek.setLastName("Coffee");
		geek.setFavouriteProgrammingLanguage("Java");
		entityManager.persist(geek);
		geek = new Geek();
		geek.setFirstName("Thomas");
		geek.setLastName("Micro");
		geek.setFavouriteProgrammingLanguage("C#");
		entityManager.persist(geek);
		geek = new Geek();
		geek.setFirstName("Christian");
		geek.setLastName("Cup");
		geek.setFavouriteProgrammingLanguage("Java");
		entityManager.persist(geek);
		transaction.commit();
	}*/

}
