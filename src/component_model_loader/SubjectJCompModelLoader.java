
package component_model_loader;

import daoimpl.SubjectDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class SubjectJCompModelLoader {

    private final SubjectDaoImpl subjectDaoImpl;
    
    public SubjectJCompModelLoader(){
        subjectDaoImpl = new SubjectDaoImpl();
    }
    
   public DefaultTableModel getAllSubjectsInfo(JTable jTable){
       DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
       tableModel.setRowCount(0);
       Object[] subjectList = subjectDaoImpl.getAllSubjectsInfo().toArray();
       for(Object o : subjectList){
           Subject s  = (Subject)o;
           Object[] rowData = {
               s.getSubjectId(), s.getSubjectTitle(), 
               s.getSubjectCode(), s.getSubjectDescription(),
               s.isIsActive()==true? "Yes":"No",s.getGradeLevel().getLevelNo()
           };
           tableModel.addRow(rowData);
       }
       return tableModel;
   }
   
   public DefaultTableModel getSubjectBySearchKeyword(JTable jTable, JTextField jtfSearchBox){
       DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
       tableModel.setRowCount(0);
       Object[] subjectList = subjectDaoImpl.getSubjectInfoByWildCard(jtfSearchBox.getText()).toArray();
       for(Object o : subjectList){
           Subject s  = (Subject)o;
           Object[] rowData = {
               s.getSubjectId(), s.getSubjectTitle(), 
               s.getSubjectCode(), s.getSubjectDescription(),
               s.isIsActive()==true? "Yes":"No",s.getGradeLevel().getLevelNo()
           };
           tableModel.addRow(rowData);
       }
       return tableModel;
   }
   
   public DefaultComboBoxModel getSubjectsHandledByFacultyPerSection(Faculty faculty, Section section, SchoolYear schoolYear){
       DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
       List<Subject> subjectList = subjectDaoImpl.getSubjectsHandledByFacultyUsingFacultySectionAndSchoolYear(faculty, section, schoolYear);
       for(Subject subject : subjectList){
           comboModel.addElement(subject);
       }
       comboModel.setSelectedItem(null);
       return comboModel;
   }
   
}
