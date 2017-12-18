package com.java.test;

import com.java.model.Project;
import com.java.model.Student;
import com.java.service.ProjectService;
import com.java.service.StudentService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectTest {

    private ProjectService projectService = new ProjectService();

    @Test
    public final void insertProject() {
        Project projectFirst=new Project();
        projectFirst.setName("Dummy project 1");
        projectFirst.setDescription("Dummy description 1");
        projectFirst.setMaximumNo(100);

        projectService.save(projectFirst);
    }

    @Test
    public final void deleteProject() {
        Project project = projectService.getProjectByName("Dummy project 1");
        projectService.delete(project);
    }

    @Test
    public final void updateProject() {
        Project project = projectService.getProjectByName("Dummy project 1");
        project.setName("Project");
        projectService.update(project);
        assert (projectService.getProjectByName("Project").getName().equals("Project"));
    }

    @Test
    public final void getProjects() {
        List<Project> all = projectService.getAll();
        assert (all != null);
    }
}