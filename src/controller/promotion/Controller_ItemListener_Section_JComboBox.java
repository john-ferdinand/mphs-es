
package controller.promotion;

import daoimpl.GradeDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.PromotionDaoImpl;
import daoimpl.SectionDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.grade.Grade;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import view.promotion.Dialog_Promotion;

/**
 *
 * @author Jordan
 */
public class Controller_ItemListener_Section_JComboBox implements ItemListener, ActionListener{

    private final Dialog_Promotion view;
    private final SchoolYear currentSchoolYear;
    private final SectionDaoImpl sectionDaoImpl;
    private final GradeDaoImpl gradeDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final PromotionDaoImpl promotionDaoImpl;
    
    public Controller_ItemListener_Section_JComboBox(Dialog_Promotion view,SchoolYear currentSchoolYear) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.sectionDaoImpl = new SectionDaoImpl();
        this.gradeDaoImpl = new GradeDaoImpl();
        this.studentDaoImpl = new StudentDaoImpl();
        this.gradeLevelDaoImpl = new GradeLevelDaoImpl();
        this.promotionDaoImpl = new PromotionDaoImpl();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox jcmbSection = (JComboBox) e.getSource();
        if(jcmbSection.getSelectedIndex() > -1){
            Section section = (Section) jcmbSection.getSelectedItem();
            loadStudentsToTable(section);
            loadPromotionStatus();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(view.getJcmbSections().getSelectedIndex() > -1){
            Section section = (Section) view.getJcmbSections().getSelectedItem();
            loadStudentsToTable(section);
            loadPromotionStatus();
        }
    }
    
    private void loadStudentsToTable(Section section){
        DefaultTableModel tableModel = (DefaultTableModel) view.getJtblStudents().getModel();
        tableModel.setRowCount(0);
        List<Student> students = sectionDaoImpl.getSectionStudentsOf(section);
        for(Student s : students){
            Grade studentSchoolYearFinalGrade = gradeDaoImpl.getFinalGradeOf(s, currentSchoolYear);
            System.out.println("TEST STUDENT ID: "+s.getStudentId() + "GRADE: "+studentSchoolYearFinalGrade.getValue());
            Object[] rowData = {
                s.getRegistration().getLastName()+", "+s.getRegistration().getFirstName()+" "+s.getRegistration().getMiddleName(),
                s.getStudentNo(),s.getGradeLevelNo(),studentSchoolYearFinalGrade.getValue(),
                studentSchoolYearFinalGrade.getValue()>=0 && studentSchoolYearFinalGrade.getValue() < 75? "Failed" : "Passed"
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void loadPromotionStatus(){
        JTable t = view.getJtblStudents();
        for(int row = 0; row < t.getRowCount(); row++){
            int studentNo = Integer.parseInt(t.getValueAt(row,1).toString().trim());
            Student student = studentDaoImpl.getStudentByStudentNo(studentNo);
            int gradeLevelTo = Integer.parseInt(t.getValueAt(row, 2).toString().trim()) + 1;
            int gradeLevelToID = gradeLevelDaoImpl.getId(gradeLevelTo);
            GradeLevel gradeLevel = new GradeLevel();
            gradeLevel.setGradeLevelID(gradeLevelToID);
            if(promotionDaoImpl.isPromoted(student, currentSchoolYear, gradeLevel)){
                t.setValueAt("Yes", row, 5);
            }else{
                t.setValueAt("Pending", row, 5);
            }
        }
    }
    
}
