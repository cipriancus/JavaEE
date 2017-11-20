package com.java.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreferenceCompoundPk {

	@Column(name = "PROJECT_ID")
	private long projectId;

	@Column(name = "ID_STUDENT")
	private long studentId;

	public PreferenceCompoundPk(long projectId, long studentId) {
		super();
		this.projectId = projectId;
		this.studentId = studentId;
	}

	public PreferenceCompoundPk() {
		super();
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreferenceCompoundPk other = (PreferenceCompoundPk) obj;
		if (projectId != other.projectId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}

}
