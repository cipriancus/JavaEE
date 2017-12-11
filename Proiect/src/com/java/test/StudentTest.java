package com.java.test;

import com.java.model.Student;
import com.java.service.StudentService;
import org.junit.Test;

import java.util.List;

public class StudentTest {

    private StudentService studentService = new StudentService();

    @Test
    public final void insertStudent() {
        Student student = new Student();
        student.setName("Ciprian");
        student.setEmail("cipriancus@gmail.com");
        student.setGender("Male");
        student.setAddress("Pacurari");
        studentService.saveStudent(student);
    }

    @Test
    public final void deleteStudent() {
        Student student = studentService.getStudentByEmail("cipriancus@gmail.com");
        studentService.delete(student);
    }

    @Test
    public final void updateStudent() {
        Student student = studentService.getStudentByEmail("cipriancus@gmail.com");
        student.setName("Andrei");
        studentService.update(student);
        assert (studentService.getStudentByEmail("cipriancus@gmail.com").getName().equals("Andrei"));
    }

    @Test
    public final void getStudents() {
        List<Student> all = studentService.getAll();
        assert (studentService != null);
    }
}