package com.java.model;

import java.util.List;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STUDENT")
    private long studentId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID", unique = true, nullable = true, insertable = false, updatable = false)
    private Project project;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumns({@JoinColumn(name = "projectId", referencedColumnName = "PROJECT_ID"),
            @JoinColumn(name = "studentId", referencedColumnName = "ID_STUDENT")})
    private List<Preference> preferences;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }
}
