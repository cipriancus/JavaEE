package com.java.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.java.db.operations.DatabaseOperation;

@ManagedBean
@RequestScoped
public class ProjectBean {
	
	public int id;
	public String name;
	public String description;
	public int maximumNo;
	
	private ArrayList<ProjectBean> projectsFromDB;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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

	@PostConstruct
	public void init() {
		projectsFromDB = DatabaseOperation.getProjectListFromDB();
	}

	/* Method Used To Fetch All Records From The Database */
	public ArrayList<ProjectBean> projectList() {
		return projectsFromDB;
	}

	/* Method Used To Save New Student Record */
	public String saveProjectDetails(ProjectBean newProject) {
		return DatabaseOperation.saveProjectDetailsInDB(newProject);
	}

	/* Method Used To Edit Student Record */
	public String editProjectRecord(int projectId) {
		return DatabaseOperation.editProjectRecordInDB(projectId);
	}

	/* Method Used To Update Student Record */
	public String updateProjectDetails(ProjectBean updateProjectObj) {
		return DatabaseOperation.updateProjectDetailsInDB(updateProjectObj);
	}

	/* Method Used To Delete Student Record */
	public String deleteProjectRecord(int projectId) {
		return DatabaseOperation.deleteProjectRecordInDB(projectId);
	}

	public ProjectBean getProjectById(String id){
		for(ProjectBean iterator : projectsFromDB){
			if(iterator.getId()==Integer.parseInt(id))
				return iterator;
		}
		return null;
	}
}
