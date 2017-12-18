package com.java.dao.factory;

import com.java.dao.PreferenceDAO;
import com.java.dao.ProjectDAO;
import com.java.dao.StudentDAO;
import com.java.model.Preference;

public class DAOFactory {

    private static ProjectDAO projectDAO = new ProjectDAO();
    private static StudentDAO studentDAO = new StudentDAO();
    private static PreferenceDAO preferenceDAO = new PreferenceDAO();

    public static ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public static StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public static PreferenceDAO getPreferenceDAO() {
        return preferenceDAO;
    }
}
