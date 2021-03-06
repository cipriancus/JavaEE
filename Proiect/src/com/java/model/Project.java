package com.java.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Cacheable
@Table(name = "Projects")
public class Project  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	public long projectId;

	@Column(name = "NAME")
	public String name;

	@Column(name = "DESCRIPTION")
	public String description;

	@Column(name = "MAXIMUM_NO")
	public int maximumNo;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
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
}
