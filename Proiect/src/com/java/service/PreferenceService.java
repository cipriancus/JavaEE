package com.java.service;

import com.java.dao.PreferenceDAO;
import com.java.dao.factory.DAOFactory;
import com.java.model.Preference;
import com.java.model.PreferenceCompoundPk;
import com.java.model.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferenceService {
    private PreferenceDAO preferenceDAO = DAOFactory.getPreferenceDAO();

    public Preference getById(long projectId, long studentId) {
        return preferenceDAO.getById(projectId, studentId);
    }

    public List<Preference> getAll() {
        return preferenceDAO.getAll();
    }

    public void delete(Preference value) {
        preferenceDAO.delete(value);
    }

    public void deleteById(long projectId, long studentId) {
        Preference preference = getById(projectId, studentId);
        if (preference != null)
            delete(preference);
    }

    public boolean update(Preference preference) {
        Preference preferenceDB = preferenceDAO.getById(preference.getId().getProjectId(), preference.getId().getStudentId());
        if (preference != null) {
            preferenceDAO.persist(preference);
            return true;
        }
        return false;
    }

    public void save(Preference preference) {
        preferenceDAO.persist(preference);
    }

    public Map<Project, Double> getPreferenceAverage() {
        ProjectService projectService = new ProjectService();

        List<Project> allProjects = projectService.getAll();

        Map<Project, Double> preferenceAverage = new HashMap<Project, Double>();

        for (Project iterator : allProjects) {
            Double projectAverage = preferenceDAO.averagePreference(iterator.getProjectId());
            preferenceAverage.put(iterator, projectAverage);
        }
        return preferenceAverage;
    }
}
