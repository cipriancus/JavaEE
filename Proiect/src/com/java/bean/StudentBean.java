package com.java.bean;

import com.java.model.Project;
import com.java.model.Student;
import com.java.service.ProjectService;
import com.java.service.StudentService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ManagedBean
@RequestScoped
public class StudentBean {

    private long project_id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String message;

    private String selected;
    private String renderedError;

    private StudentService studentService=new StudentService();
    private ProjectService projectService=new ProjectService();

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

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public void listener(AjaxBehaviorEvent event) {
        String selected=this.selected;

        if (selected != null) {
            Project project=projectService.getById(Long.parseLong(selected));
            if(project!=null){
                if(project.getMaximumNo()!=0){
                    this.project_id = project.getProjectId();
                    renderedError="false";
                }else {
                    renderedError = "true";
                }
            }else {
                renderedError = "true";
            }
        }else {
            renderedError = "true";
        }
    }

    public void allert_project(){
        Random random=new Random();
        this.message="Hurry up, "+ random.nextInt(10)+" are looking at this project";
    }


    public List<Student> studentsList() {
        return studentService.getAll();
    }

    public void saveStudentDetails() {

        if(name.length()>0&&email.length()>0&&email.length()>0
                &&password.length()>0&&gender.length()>0&&address.length()>0&&selected.length()>0){
            Student student=new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPassword(password);
            student.setGender(gender);
            student.setAddress(address);

            Project project=projectService.getById(project_id);
            if(project!=null&&project.maximumNo>0)
            {
                project.setMaximumNo(project.getMaximumNo()-1);
                projectService.update(project);

                student.setProject(project);
            }
            studentService.saveStudent(student);
        }
    }

    public void editStudentRecord(long studentId) {
        Student student = studentService.getById(studentId);
        if (student != null) {
            Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMapObj.put("editRecordObj", student);
        }
    }

    public void updateStudentDetails(Student student) {
        studentService.update(student);
    }

    public void deleteStudentRecord(long studentId) {
       studentService.deleteById(studentId);
    }
}