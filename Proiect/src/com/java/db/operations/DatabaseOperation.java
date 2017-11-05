package com.java.db.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.java.model.ProjectBean;
import com.java.model.StudentBean;

public class DatabaseOperation {

    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;

    /* Method To Establish Database Connection */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3306/students", db_userName = "root", db_password = "0000";
            connObj = DriverManager.getConnection(db_url, db_userName, db_password);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connObj;
    }

    /* Method To Fetch The Student Records From Database */
    public static ArrayList<StudentBean> getStudentsListFromDB() {
        ArrayList<StudentBean> studentsList = new ArrayList<StudentBean>();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from student_record");
            while (resultSetObj.next()) {
                StudentBean stuObj = new StudentBean();
                stuObj.setId(resultSetObj.getInt("student_id"));
                stuObj.setName(resultSetObj.getString("student_name"));
                stuObj.setEmail(resultSetObj.getString("student_email"));
                stuObj.setPassword(resultSetObj.getString("student_password"));
                stuObj.setGender(resultSetObj.getString("student_gender"));
                stuObj.setAddress(resultSetObj.getString("student_address"));
                stuObj.setProject_id(resultSetObj.getInt("student_proj"));
                studentsList.add(stuObj);
            }
            System.out.println("Total Records Fetched: " + studentsList.size());
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return studentsList;
    }

    /* Method Used To Save New Student Record In Database */
    public static String saveStudentDetailsInDB(StudentBean newStudentObj) {
        int saveResult = 0;
        String navigationResult = "";
        try {
            pstmt = getConnection().prepareStatement(
                    "insert into student_record (student_name, student_email, student_password, student_gender, student_address, student_proj) values (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newStudentObj.getName());
            pstmt.setString(2, newStudentObj.getEmail());
            pstmt.setString(3, newStudentObj.getPassword());
            pstmt.setString(4, newStudentObj.getGender());
            pstmt.setString(5, newStudentObj.getAddress());
            pstmt.setInt(6, newStudentObj.getProject_id());
            saveResult = pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        if (saveResult != 0) {
            navigationResult = "studentsList.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createStudent.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }

    /* Method Used To Edit Student Record In Database */
    public static String editStudentRecordInDB(int studentId) {
        StudentBean editRecord = null;
        System.out.println("editStudentRecordInDB() : Student Id: " + studentId);

		/* Setting The Particular Student Details In Session */
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from student_record where student_id = " + studentId);
            if (resultSetObj != null) {
                resultSetObj.next();
                editRecord = new StudentBean();
                editRecord.setId(resultSetObj.getInt("student_id"));
                editRecord.setName(resultSetObj.getString("student_name"));
                editRecord.setEmail(resultSetObj.getString("student_email"));
                editRecord.setGender(resultSetObj.getString("student_gender"));
                editRecord.setAddress(resultSetObj.getString("student_address"));
                editRecord.setPassword(resultSetObj.getString("student_password"));
                editRecord.setProject_id(resultSetObj.getInt("student_proj"));
            }
            sessionMapObj.put("editRecordObj", editRecord);
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/editStudent.xhtml?faces-redirect=true";
    }

    /* Method Used To Update Student Record In Database */
    public static String updateStudentDetailsInDB(StudentBean updateStudentObj) {
        try {
            pstmt = getConnection().prepareStatement(
                    "update student_record set student_name=?, student_email=?, student_password=?, student_gender=?, student_address=? , student_proj=? where student_id=?");
            pstmt.setString(1, updateStudentObj.getName());
            pstmt.setString(2, updateStudentObj.getEmail());
            pstmt.setString(3, updateStudentObj.getPassword());
            pstmt.setString(4, updateStudentObj.getGender());
            pstmt.setString(5, updateStudentObj.getAddress());
            pstmt.setInt(6, updateStudentObj.getProject_id());
            pstmt.setInt(7, updateStudentObj.getId());
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/studentsList.xhtml?faces-redirect=true";
    }

    /* Method Used To Delete Student Record From Database */
    public static String deleteStudentRecordInDB(int studentId) {
        System.out.println("deleteStudentRecordInDB() : Student Id: " + studentId);
        try {
            pstmt = getConnection().prepareStatement("delete from student_record where student_id = " + studentId);
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/studentsList.xhtml?faces-redirect=true";
    }

    /* Method To Fetch The Student Records From Database */
    public static ArrayList<ProjectBean> getProjectListFromDB() {
        ArrayList<ProjectBean> projectList = new ArrayList<ProjectBean>();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from project_record");
            while (resultSetObj.next()) {
                ProjectBean projObs = new ProjectBean();
                projObs.setId(resultSetObj.getInt("project_id"));
                projObs.setName(resultSetObj.getString("project_name"));
                projObs.setDescription(resultSetObj.getString("project_description"));
                projObs.setMaximumNo(resultSetObj.getInt("project_max_number"));
                projectList.add(projObs);
            }
            System.out.println("Total Records Fetched: " + projectList.size());
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return projectList;
    }

    /* Method Used To Save New Student Record In Database */
    public static String saveProjectDetailsInDB(ProjectBean newProjectObj) {
        int saveResult = 0;
        String navigationResult = "";
        try {
            pstmt = getConnection().prepareStatement(
                    "insert into project_record (project_name, project_description, project_max_number) values (?, ?, ?)");
            pstmt.setString(1, newProjectObj.getName());
            pstmt.setString(2, newProjectObj.getDescription());
            pstmt.setInt(3, newProjectObj.getMaximumNo());
            saveResult = pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        if (saveResult != 0) {
            navigationResult = "projectList.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createProject.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }

    /* Method Used To Edit Student Record In Database */
    public static String editProjectRecordInDB(int projectId) {
        ProjectBean editRecord = null;
        System.out.println("editProjectRecordInDB() : Project Id: " + projectId);

		/* Setting The Particular Student Details In Session */
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from project_record where project_id = " + projectId);
            if (resultSetObj != null) {
                resultSetObj.next();
                editRecord = new ProjectBean();
                editRecord.setId(resultSetObj.getInt("project_id"));
                editRecord.setName(resultSetObj.getString("project_name"));
                editRecord.setDescription(resultSetObj.getString("project_description"));
                editRecord.setMaximumNo(resultSetObj.getInt("project_max_number"));
            }
            sessionMapObj.put("editRecordObj", editRecord);
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/editProject.xhtml?faces-redirect=true";
    }

    /* Method Used To Update Student Record In Database */
    public static String updateProjectDetailsInDB(ProjectBean updateProjectObj) {
        try {
            pstmt = getConnection().prepareStatement(
                    "update project_record set project_name=?, project_description=?, project_max_number=? where project_id=?");
            pstmt.setString(1, updateProjectObj.getName());
            pstmt.setString(2, updateProjectObj.getDescription());
            pstmt.setInt(3, updateProjectObj.getMaximumNo());
            pstmt.setInt(4, updateProjectObj.getId());
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/projectList.xhtml?faces-redirect=true";
    }

    /* Method Used To Delete Student Record From Database */
    public static String deleteProjectRecordInDB(int projectId) {
        System.out.println("deleteProjectRecordInDB() : Project Id: " + projectId);
        try {
            pstmt = getConnection().prepareStatement("delete from project_record where project_id = " + projectId);
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/projectList.xhtml?faces-redirect=true";
    }
}