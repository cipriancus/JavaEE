package com.java.dao.factory;

import com.java.dao.ProjectDAO;
import com.java.dao.StudentDAO;

public class DAOFactory {

    private static ProjectDAO projectDAO = new ProjectDAO();
    private static StudentDAO studentDAO = new StudentDAO();

    public static ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public static StudentDAO getStudentDAO() {
        return studentDAO;
    }
}
