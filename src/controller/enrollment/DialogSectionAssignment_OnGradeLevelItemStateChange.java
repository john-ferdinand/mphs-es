package controller.enrollment;

import component_model_loader.EnrollmentJCompModelLoader;
import component_model_loader.SectionJCompModelLoader;
import daoimpl.EnrollmentDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_OnGradeLevelItemStateChange implements ItemListener {

    private final Dialog_SectionAssignment view;
    private SchoolYearDaoImpl schoolYearDaoImpl;

    public DialogSectionAssignment_OnGradeLevelItemStateChange(Dialog_SectionAssignment view) {
        this.view = view;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            clearForm();
            view.getJcmbSection().setEnabled(true);
            view.getJbtnMoveStudentToSection().setEnabled(false);
            view.getJbtnRemoveStudentFromSection().setEnabled(false);
            loadSectionsByGradeLevelSelected();
            loadStudentsByGradeLevelSelected();
            applySectionComboListener();
        }
    }

    private void loadSectionsByGradeLevelSelected() {
        SectionJCompModelLoader sectionJCompModelLoader = new SectionJCompModelLoader();
        if (view.getJcbSummer().isSelected()) {
            SchoolYear currentSchoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
            GradeLevel gradeLevel = (GradeLevel) view.getJcmbGradeLevel().getSelectedItem();
            String sectionType = "S";
            view.getJcmbSection().setModel(sectionJCompModelLoader.getSectionsBy(currentSchoolYear, gradeLevel, sectionType));
            view.getJcmbSection().setSelectedIndex(-1);
        } else {
            view.getJcmbSection().setModel(sectionJCompModelLoader.getSectionsByGradeLevelNo(view.getJcmbGradeLevel()));
            view.getJcmbSection().setSelectedIndex(-1);
        }
    }

    private void loadStudentsByGradeLevelSelected() {
        EnrollmentDaoImpl enrollmentDaoImpl = new EnrollmentDaoImpl();
        EnrollmentJCompModelLoader enrollmentJCompModelLoader = new EnrollmentJCompModelLoader(schoolYearDaoImpl.getCurrentSchoolYear());
        JTable table = view.getJtblEnrolledStudents();
        JComboBox jcmbGradeLevel = view.getJcmbGradeLevel();
        if (view.getJcbSummer().isSelected()) {
            SchoolYear currentSchoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
            DefaultTableModel summerStudents = (DefaultTableModel) enrollmentJCompModelLoader.getAllUnsectionedSummerEnrolleesBy(table, jcmbGradeLevel, currentSchoolYear);
            view.getJtblEnrolledStudents().setModel(summerStudents);
        } else {
            int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
            DefaultTableModel tableModel = (DefaultTableModel) enrollmentJCompModelLoader.getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(table, jcmbGradeLevel, schoolYearId);
            view.getJtblEnrolledStudents().setModel(tableModel);
        }
    }
    
    private void clearForm() {
        Component[] compArr = view.getJpnlSectionDetails().getComponents();
        for (Component c : compArr) {
            if (c instanceof JComboBox) {
                String actionCommand = ((JComboBox) c).getActionCommand().toString().trim();
                if (actionCommand.equalsIgnoreCase("session") || actionCommand.equalsIgnoreCase("section")
                        || actionCommand.equalsIgnoreCase("adviser")) {
                    ((JComboBox) c).setSelectedIndex(-1);
                }
            } else if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
        ((DefaultTableModel) view.getJtblEnrolledStudents().getModel()).setRowCount(0);
        ((DefaultTableModel) view.getJtblSectionStudents().getModel()).setRowCount(0);
    }
    
    private void applySectionComboListener() {
        view.getJcmbSection().addActionListener(new DialogSectionAssignment_OnSectionItemStateChange(view));
    }

}
