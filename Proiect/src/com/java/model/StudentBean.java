package com.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.event.AjaxBehaviorEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.java.db.operations.DatabaseOperation;

@ManagedBean
@RequestScoped
public class StudentBean {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String message;
    private int project_id;
    private String selected;
    private List<String> skills;
    private String renderedError;


    public ArrayList<ProjectBean> projectsFromDB;

    public ArrayList<StudentBean> studentsListFromDB;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRenderedError(String renderedError) {
        this.renderedError = renderedError;
    }

    public String getRenderedError() {
        return renderedError;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void listener(AjaxBehaviorEvent event) {
        String selected=this.selected;
        if (selected != null) {
            ProjectBean proj = null;
            int id=Integer.parseInt(selected);
            for (ProjectBean iterator : DatabaseOperation.getProjectListFromDB()) {
                if (iterator.getId() == id)
                    proj = iterator;
            }
            if (proj != null) {
                if (proj.maximumNo != 0) {
                    proj.maximumNo = proj.maximumNo - 1;
                    DatabaseOperation.updateProjectDetailsInDB(proj);
                    this.project_id = proj.getId();
                    renderedError="false";
                } else {
                    renderedError = "true";
                }
            } else {
                renderedError = "true";
            }
        } else {
            renderedError = "true";
        }
    }

    public void allert_project(){
        Random random=new Random();
        this.message="Hurry up, "+ random.nextInt(10)+" are looking at this project";
    }

    @PostConstruct
    public void init() {
        renderedError = "false";
        studentsListFromDB = DatabaseOperation.getStudentsListFromDB();
        projectsFromDB = DatabaseOperation.getProjectListFromDB();
    }

    /* Method Used To Fetch All Records From The Database */
    public ArrayList<StudentBean> studentsList() {
        return studentsListFromDB;
    }

    /* Method Used To Save New Student Record */
    public String saveStudentDetails(StudentBean newStudentObj) {
        if(selected.length()>0)
            this.project_id=Integer.parseInt(selected);
        return DatabaseOperation.saveStudentDetailsInDB(newStudentObj);
    }

    /* Method Used To Edit Student Record */
    public String editStudentRecord(int studentId) {
        return DatabaseOperation.editStudentRecordInDB(studentId);
    }

    /* Method Used To Update Student Record */
    public String updateStudentDetails(StudentBean updateStudentObj) {
        return DatabaseOperation.updateStudentDetailsInDB(updateStudentObj);
    }

    /* Method Used To Delete Student Record */
    public String deleteStudentRecord(int studentId) {
        return DatabaseOperation.deleteStudentRecordInDB(studentId);
    }
}