
package controller.reports;

import daoimpl.SectionDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import utility.jtable.JTableUtil;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_ClassList_Section_JComboBox implements ItemListener{
    
    private final Panel_Reports view;
    private final SectionDaoImpl sectionDaoImpl;

    public Controller_Reports_ClassList_Section_JComboBox(Panel_Reports view) {
        this.view = view;
        this.sectionDaoImpl = new SectionDaoImpl();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == view.getJcmbClassListSection()){
            if(view.getJcmbClassListSection().getSelectedIndex() > -1){
                view.clearClassList();
                
                Section section = (Section)view.getJcmbClassListSection().getSelectedItem();
                view.getJlblClassListAdviserName().setText(section.getAdviser().getLastName()+", "+section.getAdviser().getFirstName());
                view.getJlblClassListGradeLevelText().setText(section.getGradeLevel().getLevelNo() == 0? "Kindergarten": section.getGradeLevel().getLevelNo()+"");
                view.getJlblClassListSectionName().setText(section.getSectionName());
                view.getJlblSectionSessionClassList().setText("Session: "+section.getSectionSession());
                view.getJlblSectionTypeClassList().setText("Section Type: " +(section.getSectionType().equalsIgnoreCase("R")? "Regular":"Summer"));
                
                SchoolYear schoolYear  = (SchoolYear) view.getJcmbCorSchoolYear().getSelectedItem();
                view.getJlblClassListSchoolYear().setText(schoolYear.getYearFrom()+"-"+schoolYear.getYearTo());
                section.setSchoolYear(schoolYear);
                
                List<Student> studentList = sectionDaoImpl.getSectionStudentsOf(section);
                String[] columns = {"No.", "Student No", "Student Name"};
                DefaultTableModel tableModel = (DefaultTableModel) view.getJtblClassList().getModel();
                tableModel.setColumnIdentifiers(columns);
                tableModel.setRowCount(0);
                int no = 1;
                for (Student s : studentList) {
                    Object[] rowData = {
                        no, s.getStudentNo(), s.getRegistration().getLastName() + ", "
                        + s.getRegistration().getFirstName() + " " + s.getRegistration().getMiddleName()
                    };
                    tableModel.addRow(rowData);
                    no++;
                }
                JTableUtil.applyCustomHeaderRenderer(view.getJtblClassList());
                JTableUtil.resizeColumnWidthsOf(view.getJtblClassList());
                view.getJcmbClassListSchoolYear().setEnabled(true);
            }
        }
    }
}
