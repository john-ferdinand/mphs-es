
package component_model_loader;

import daoimpl.SubjectCategoryDaoImpl;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.subject.Subject;
import model.subjectcategory.SubjectCategory;

/**
 *
 * @author Jordan
 */
public class SubjectCategoryJCompModelLoader {
    
    private SubjectCategoryDaoImpl subjectCategoryDaoImpl;
    
    public SubjectCategoryJCompModelLoader(){
        subjectCategoryDaoImpl = new SubjectCategoryDaoImpl();
    }
    
    public DefaultTableModel getAllSubjectCategoryInfo(JTable jtblSubjectCategoryMasterList){
        DefaultTableModel tableModel = (DefaultTableModel) jtblSubjectCategoryMasterList.getModel();
        tableModel.setRowCount(0);
        Object[] subjectCategoryList = subjectCategoryDaoImpl.getAllSubjectCategoryInfo().toArray();
        for(Object o : subjectCategoryList){
            SubjectCategory sc = (SubjectCategory)o;
            Object[] rowData = {
                sc.getSubjectCategoryId(), sc.getSubjectCategoryName(),
                sc.getDescription(), sc.getIsActive() == true ? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getAllSubjectCategoryInfoByWildCard(JTextField jtfSearchBox, JTable jtblSubjectCategoryMasterList){
        DefaultTableModel tableModel = (DefaultTableModel) jtblSubjectCategoryMasterList.getModel();
        tableModel.setRowCount(0);
        Object[] subjectCategoryList = subjectCategoryDaoImpl.getAllSubjectCategoryInfoByWildCard(jtfSearchBox.getText().trim()).toArray();
        for(Object o : subjectCategoryList){
            SubjectCategory sc = (SubjectCategory)o;
            Object[] rowData = {
                sc.getSubjectCategoryId(), sc.getSubjectCategoryName(),
                sc.getDescription(), sc.getIsActive() == true ? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getSubjectCategoryAssignedSubjectsById(JTable jTableSubjectCategoryMasterList, JTable jtblAssignedSubjects){
        DefaultTableModel tableModel = (DefaultTableModel) jtblAssignedSubjects.getModel();
        tableModel.setRowCount(0);
        if (jTableSubjectCategoryMasterList.getSelectedRow() > -1) {
            int selectedRow = jTableSubjectCategoryMasterList.getSelectedRow();
            int subjectCategoryId = Integer.parseInt(jTableSubjectCategoryMasterList.getValueAt(selectedRow, 0).toString().trim());
            Object[] assignedSubjectsList = subjectCategoryDaoImpl.getSubjectCategoryAssignedSubjectsById(subjectCategoryId).toArray();
            for (Object o : assignedSubjectsList) {
                Subject s = (Subject) o;
                Object[] rowData = {
                    s.getSubjectId(), s.getSubjectTitle(),
                    s.getSubjectCode(), s.getSubjectDescription(),
                    s.getIsActive() == true ? "Yes" : "No"
                };
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }
    
    public DefaultTableModel getSubjectCategoryAssignedSubjectsById(int subjectCategoryId, JTable jtblAssignedSubjects){
        DefaultTableModel tableModel = (DefaultTableModel) jtblAssignedSubjects.getModel();
        tableModel.setRowCount(0);
            Object[] assignedSubjectsList = subjectCategoryDaoImpl.getSubjectCategoryAssignedSubjectsById(subjectCategoryId).toArray();
            for (Object o : assignedSubjectsList) {
                Subject s = (Subject) o;
                Object[] rowData = {
                    s.getSubjectId(), s.getSubjectTitle(),
                    s.getSubjectCode(), s.getSubjectDescription(),
                    s.getIsActive() == true ? "Yes" : "No"
                };
                tableModel.addRow(rowData);
            }
        return tableModel;
    }
}
