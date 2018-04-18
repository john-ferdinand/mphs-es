
package controller.grade;

import component_model_loader.StudentJCompModelLoader;
import component_model_loader.SubjectJCompModelLoader;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.user.User;
import view.grades.View_Dialog_InputGrade;

/**
 *
 * @author Jordan
 */
public class ItemListener_Dialog_InputGrade_Section_JComboBox implements ItemListener{
    
    private final SubjectJCompModelLoader subjectJCompModelLoader;
    private final FacultyDaoImpl facultyDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final StudentJCompModelLoader studentJCompModelLoader;
    private final View_Dialog_InputGrade view;
    private final User user;

    public ItemListener_Dialog_InputGrade_Section_JComboBox(View_Dialog_InputGrade view, User user) {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
        studentDaoImpl = new StudentDaoImpl();
        subjectJCompModelLoader = new SubjectJCompModelLoader();
        studentJCompModelLoader = new StudentJCompModelLoader(studentDaoImpl);
        
        this.view = view;
        this.user = user;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            JComboBox jcmbSection = (JComboBox) e.getSource();
            Section section = (Section) jcmbSection.getSelectedItem();
            SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
            Faculty faculty = facultyDaoImpl.getFacultyByUser(user);
            
            loadSubjects(section, faculty, schoolYear);
            loadGradeLevel(section);
            loadStudentsAndGrades(section, schoolYear);
        }
    }
    
    private void loadSubjects(Section section, Faculty faculty, SchoolYear schoolYear) {
        DefaultComboBoxModel comboModel = subjectJCompModelLoader.getSubjectsHandledByFacultyPerSection(faculty, section, schoolYear);
        view.getJcmbSubjectCode().setModel(comboModel);
    }
    
    private void loadGradeLevel(Section section){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.addElement(section.getGradeLevel());
        view.getJcmbGradeLevel().setModel(comboModel);
    }
    
    private void loadStudentsAndGrades(Section section, SchoolYear schoolYear){
        JTable table = view.getJtblGradingSheet();
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        table.setModel(studentJCompModelLoader.getStudentAndGradesBySectionAndSchoolYear(table, section, schoolYear));
    }
    
}
