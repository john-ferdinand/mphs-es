
package controller.enrollment;

import component_model_loader.SectionJCompModelLoader;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.section.Section;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_OnSectionItemStateChange implements ActionListener{

    private final Dialog_SectionAssignment view;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final SectionDaoImpl sectionDaoImpl;
    private final SectionJCompModelLoader sectionJCompModelLoader;

    public DialogSectionAssignment_OnSectionItemStateChange(Dialog_SectionAssignment view) {
        this.view = view;
        this.sectionDaoImpl = new SectionDaoImpl();
        this.sectionJCompModelLoader = new SectionJCompModelLoader();
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getJcmbSection().getSelectedIndex() > -1) {
            Section section = (Section)view.getJcmbSection().getSelectedItem();
            loadAdviser(section.getSectionId());
            loadCapacity(section.getSectionId());
            loadSession(section.getSectionId());
            loadSectionStudents();
            view.getJbtnMoveStudentToSection().setEnabled(true);
            view.getJbtnRemoveStudentFromSection().setEnabled(true);
        }
    }
    
    private void loadSession(int sectionId){
        Section s = sectionDaoImpl.getSectionById(sectionId);
        view.getJcmbSession().setSelectedItem(s.getSectionSession());
    }
    
    private void loadCapacity(int sectionId){
        Section s = sectionDaoImpl.getSectionById(sectionId);
        view.getJtfCapacityDenominator().setText(""+s.getCapacity());
    }
    
    private void loadAdviser(int sectionId){
        Section s = sectionDaoImpl.getSectionById(sectionId);
        view.getJcmbAdviser().setSelectedItem(s.getAdviser().getFacultyID());
    }
    
    private void loadSectionStudents() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getJtblSectionStudents().getModel();
        JTable table = view.getJtblSectionStudents();
        JComboBox jcmbSection = view.getJcmbSection();
        int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        tableModel = sectionJCompModelLoader.getSectionStudentsBySectionIdAndSchoolYearId(table, jcmbSection, schoolYearId);
        view.getJtblSectionStudents().setModel(tableModel);
    }
}
