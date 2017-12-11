//package com.java.test;
//
//import org.junit.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class CacheTest {
//
//    public static EntityManagerFactory emf;
//    public static EntityManager em;
//
//
//    @Test
//    public final void testCache(){
//        emf = Persistence.createEntityManagerFactory("theJavaGeekJPA");
//        em = emf.createEntityManager();
//
//        EntityTransaction txn = em.getTransaction();
//        txn.begin();
//
//        Animal animal = new Animal();
//
//        Bike bike = new Bike();
//
//        Computer computer = new Computer();
//
//        em.persist(animal);
//        em.persist(bike);
//        em.persist(computer);
//
//        int idAnimal = animal.getIdAnimal();
//        int idBike = bike.getIdBike();
//        int idComputer = computer.getIdComputer();
//
//        txn.commit();
//        txn.begin();
//
//        Cache cache = emf.getCache();
//
//        System.out.println("Animal in Cache: "
//                + cache.contains(Animal.class, idAnimal));
//        System.out.println("Bike in Cache : "
//                + cache.contains(Bike.class, idBike));
//        System.out.println("Computer in Cache : "
//                + cache.contains(Computer.class, idComputer));
//        cache.evictAll();
//
//        System.out.println("Animal in Cache after evict: "
//                + cache.contains(Animal.class, idAnimal));
//        System.out.println("Bike in Cache after evict: "
//                + cache.contains(Bike.class, idBike));
//        System.out.println("Computer in Cache after evict: "
//                + cache.contains(Computer.class, idComputer));
//
//        txn.commit();
//        em.close();
//        emf.close();
//    }
//
//}