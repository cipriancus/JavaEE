package com.java.test;

import com.java.model.Project;
import com.java.model.Student;
import com.java.persistance.PersistenceUtil;
import com.java.service.ProjectService;
import com.java.service.StudentService;
import org.junit.Test;

import javax.persistence.*;

public class CacheTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private StudentService studentService = new StudentService();
    private ProjectService projectService = new ProjectService();

    @Test
    public final void testCacheStudent() {
        emf = PersistenceUtil.getEntityManagerFactory();
        em = PersistenceUtil.getEntityManager();

        Student student = new Student();
        student.setName("Ciprian");
        student.setEmail("cipriancus@gmail.com");
        student.setGender("Male");
        student.setAddress("Pacurari");

        studentService.saveStudent(student);

        long idStudent = studentService.getStudentByEmail(student.getEmail()).getStudentId();

        Cache cache = emf.getCache();

        System.out.println("Student in Cache: "
                + cache.contains(Student.class, idStudent));

        cache.evictAll();

        System.out.println("Student in Cache after evict: "
                + cache.contains(Student.class, idStudent));
    }

    @Test
    public final void testCacheProject() {
        emf = PersistenceUtil.getEntityManagerFactory();
        em = PersistenceUtil.getEntityManager();

        Project project=new Project();
        project.setName("Dummy project");
        project.setDescription("Dummy description");
        project.setMaximumNo(100);

        projectService.save(project);

        long idProject=projectService.getProjectByName(project.getName()).getProjectId();

        Cache cache = emf.getCache();

        System.out.println("Project in Cache: "
                + cache.contains(Student.class, idProject));

        cache.evictAll();

        System.out.println("Project in Cache after evict: "
                + cache.contains(Student.class, idProject));
    }
}