package controller.grade;

import component_model_loader.SectionJCompModelLoader;
import daoimpl.ClassTypeDaoImpl;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.classtype.ClassType;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.user.User;
import view.grades.View_Dialog_InputGrade;

/**
 *
 * @author Jordan
 */
public class ItemListener_Dialog_InputGrade_ClassHandled_JComboBox implements ItemListener {

    private final User user;
    private final View_Dialog_InputGrade view;
    private final FacultyDaoImpl facultyDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final ClassTypeDaoImpl classTypeDaoImpl;
    private final SectionJCompModelLoader sectionJCompModelLoader;

    public ItemListener_Dialog_InputGrade_ClassHandled_JComboBox(View_Dialog_InputGrade view, User user) {
        this.view = view;
        this.user = user;
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        this.facultyDaoImpl = new FacultyDaoImpl();
        this.classTypeDaoImpl = new ClassTypeDaoImpl();
        this.sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            resetFormState();
            JComboBox jcmbClassHandled = (JComboBox) e.getSource();
            String selected = jcmbClassHandled.getSelectedItem().toString().trim();
            int classTypeId = Integer.parseInt(selected.trim());
            ClassType classType = classTypeDaoImpl.getClassTypeById(classTypeId);
            if (classType.getClassTypeName().trim().equalsIgnoreCase("Non-Advisory")) {
                loadNonAdvisorySectionsOfFaculty();
            } else if (classType.getClassTypeName().trim().equalsIgnoreCase("Advisory")) {
                loadAdvisorySectionsOfFaculty();
            } else if(classType.getClassTypeName().trim().equalsIgnoreCase("Summer")){
                
            }
        }
    }

    private void loadAdvisorySectionsOfFaculty() {
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        Faculty faculty = facultyDaoImpl.getFacultyByUser(user);
        DefaultComboBoxModel comboModel = sectionJCompModelLoader.getAdvisorySectionsOfFaculty(faculty, schoolYear);
        view.getJcmbSection().setModel(comboModel);
    }

    private void loadNonAdvisorySectionsOfFaculty() {
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        Faculty faculty = facultyDaoImpl.getFacultyByUser(user);
        DefaultComboBoxModel comboModel = sectionJCompModelLoader.getNonAdvisorySectionsOfFaculty(faculty, schoolYear);
        view.getJcmbSection().setModel(comboModel);
    }
    
    private void loadSummerSectionsOfFaculty(){
        
    }

    private void resetFormState() {
        ((DefaultTableModel) view.getJtblGradingSheet().getModel()).setRowCount(0);
        view.getJcmbSection().setModel(new DefaultComboBoxModel());
        view.getJcmbGradeLevel().setModel(new DefaultComboBoxModel());
        view.getJcmbSubjectCode().setModel(new DefaultComboBoxModel());
    }

}
