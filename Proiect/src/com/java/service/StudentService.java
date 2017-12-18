package com.java.service;

import com.java.dao.ProjectDAO;
import com.java.dao.StudentDAO;
import com.java.dao.factory.DAOFactory;
import com.java.model.Preference;
import com.java.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = DAOFactory.getStudentDAO();

    public Student getById(Long id) {
        return studentDAO.getById(id);
    }

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public void delete(Student value) {
        studentDAO.delete(value);
    }

    public void deleteById(long id) {
        Student student = getById(id);
        if (student != null)
            delete(student);
    }

    public boolean update(Student value) {
        Student studentDB = studentDAO.getById(value.getStudentId());
        if (studentDB != null) {
            studentDAO.persist(value);
            return true;
        }
        return false;
    }

    public Student getStudentByEmail(String email){
        return studentDAO.getStudentByEmail(email);
    }

    public void saveStudent(Student student) {
        studentDAO.persist(student);
    }

    public boolean addPreferences(Student student, List<Preference> preferences) {
        student.setPreferences(preferences);
        return update(student);
    }

    public List<Student> incompletePreferenceList(){
        List<Student> allStudents=studentDAO.getAll();

        ProjectDAO projectDAO=DAOFactory.getProjectDAO();

        long allProjects=projectDAO.countProjects();

        List<Student> incompleteList=new ArrayList<Student>();

        for(Student iterator:allStudents){
            if(iterator.getPreferences().size()<allProjects)
                incompleteList.add(iterator);
        }
        return incompleteList;
    }
}