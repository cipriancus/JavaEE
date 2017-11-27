package com.java.bean;

import com.java.model.Project;
import com.java.service.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class ProjectBean {

    private ProjectService projectService = new ProjectService();

    public String name;
    public String description;
    public int maximumNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumNo() {
        return maximumNo;
    }

    public void setMaximumNo(int maximumNo) {
        this.maximumNo = maximumNo;
    }

    public List<Project> projectList() {
        return projectService.getAll();
    }

    public void saveProjectDetails() {
        if (name.length() > 0 && description.length() > 0 && maximumNo > 0) {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setMaximumNo(maximumNo);
            projectService.save(project);
        }
    }

    public void editProjectRecord(long projectId) {
        Project project = projectService.getById(projectId);
        if (project != null) {
            Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMapObj.put("editRecordObj", project);
        }
    }

    public void updateProject(Project project) {
        projectService.update(project);
    }

    public void deleteProjectRecord(long projectId) {
        projectService.deleteById(projectId);
    }

    public Project getProjectById(long id) {
        return projectService.getById(id);
    }
}
