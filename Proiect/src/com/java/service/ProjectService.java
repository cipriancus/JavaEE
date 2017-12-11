package com.java.service;

import com.java.dao.ProjectDAO;
import com.java.dao.factory.DAOFactory;
import com.java.model.Project;

import java.util.List;
import java.util.Map;

public class ProjectService {
    private ProjectDAO projectDAO = DAOFactory.getProjectDAO();

    public Project getById(Long id) {
        return projectDAO.getById(id);
    }

    public List<Project> getAll() {
        return projectDAO.getAll();
    }

    public void delete(Project value) {
        projectDAO.delete(value);
    }

    public void deleteById(long id) {
        Project project = getById(id);
        if (project != null)
            delete(project);
    }

    public boolean update(Project project) {
        Project projectDB = projectDAO.getById(project.getProjectId());
        if (projectDB != null) {
            projectDAO.persist(project);
            return true;
        }
        return false;
    }

    public void save(Project project) {
        projectDAO.persist(project);
    }

    /**
     * To-Do
     * @return
     */
    public Map<Project, Integer> getPreferenceOfStudents(){
        return null;
    }
}
