package com.java.test;

import com.java.model.Preference;
import com.java.model.PreferenceCompoundPk;
import com.java.model.Project;
import com.java.model.Student;
import com.java.service.PreferenceService;
import com.java.service.ProjectService;
import com.java.service.StudentService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class PreferenceTest {

    private PreferenceService preferenceService = new PreferenceService();
    private StudentService studentService = new StudentService();
    private ProjectService projectService = new ProjectService();

    @Test
    public final void insertPreference() {
        Preference preference=new Preference();
        Student student=studentService.getStudentByEmail("cipriancus@gmail.com");
        Project project=projectService.getProjectByName("Dummy project 1");

        PreferenceCompoundPk preferenceCompoundPk=new PreferenceCompoundPk();
        preferenceCompoundPk.setProjectId(project.getProjectId());
        preferenceCompoundPk.setStudentId(student.getStudentId());

        preference.setId(preferenceCompoundPk);
        preferenceService.save(preference);
    }

    @Test
    public final void deletePreference() {
        Preference preference=preferenceService.getById(projectService.getProjectByName("Dummy project 1").getProjectId(),studentService.getStudentByEmail("cipriancus@gmail.com").getStudentId());
        preferenceService.delete(preference);
    }

    @Test
    public final void getPreferences() {
        List<Preference> all = preferenceService.getAll();
        assert (all != null);
    }

    @Test
    public final void preferanceAverage(){
        Map<Project,Double> prefAverage=preferenceService.getPreferenceAverage();
        for(Project iterator:prefAverage.keySet()){
            System.out.println(iterator.getName() +" has average "+prefAverage.get(iterator));
        }
        assert(prefAverage != null);
    }
}
