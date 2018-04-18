package component_model_loader;

import daoimpl.CurriculumDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.curriculum.Curriculum;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class CurriculumJCompModelLoader {
    private CurriculumDaoImpl curriculumDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    
    public CurriculumJCompModelLoader(){
        curriculumDaoImpl = new CurriculumDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    public DefaultTableModel getAllCurriculum(JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] curriculumList = curriculumDaoImpl.getAllCurriculum().toArray();
        for(Object o : curriculumList){
            Curriculum c = (Curriculum)o;
            Object[] rowData = {
                c.getCurriculumId(),c.getTitle(),
                c.getSubjectCount(),
                c.getDescription(), 
                (schoolYearDaoImpl.getSchoolYearById(c.getSchoolYearId()).getYearFrom()),
                c.getDateCreated(),
                c.getIsActive() == true? "Active" : "Inactive"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getCurriculumByWildCard(JTextField jtfSearchBox, JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] curriculumList = curriculumDaoImpl.getCurriculumByWildCard(jtfSearchBox.getText().trim()).toArray();
        for(Object o : curriculumList){
            Curriculum c = (Curriculum)o;
            Object[] rowData = {
                c.getCurriculumId(),c.getTitle(),
                c.getSubjectCount(),
                c.getDescription(), 
                (schoolYearDaoImpl.getSchoolYearById(c.getSchoolYearId()).getYearFrom()),
                c.getDateCreated(),
                c.getIsActive() == true? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getCurriculumSubjectsById(int curriculumId, JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] curriculumSubjects = curriculumDaoImpl.getCurriculumSubjectsById(curriculumId).toArray();
        for (Object o : curriculumSubjects) {
            Subject s = (Subject) o;
            Object[] rowData = {
                s.getSubjectId(), s.getSubjectTitle(),
                s.getSubjectCode(), s.getGradeLevel().getLevelNo(),
                s.getSubjectMinutes()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
