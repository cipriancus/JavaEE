package com.java.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PreferenceCompoundPk implements Serializable {

    @Column(name = "PROJECT_ID")
    private long projectId;

    @Column(name = "ID_STUDENT")
    private long studentId;

    public PreferenceCompoundPk(){
        super();
    }

    public PreferenceCompoundPk(long projectId, long studentId) {
        super();
        this.projectId = projectId;
        this.studentId = studentId;
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
}
