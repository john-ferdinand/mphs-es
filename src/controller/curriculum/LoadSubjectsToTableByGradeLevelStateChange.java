
package controller.curriculum;

import daoimpl.GradeLevelDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class LoadSubjectsToTableByGradeLevelStateChange implements ItemListener{
    
    private JComboBox jcmbGradeLevel;
    private JTable jtblSubjectList;
    private JTable jtblCurrentSubjects;
    
    private GradeLevelDaoImpl gradeLevelDaoImpl;
    private SubjectDaoImpl subjectDaoImpl;
    
    public LoadSubjectsToTableByGradeLevelStateChange(JComboBox jcmbGradeLevel,JTable jtblSubjectList, JTable jtblCurrentSubjects){
        this.jcmbGradeLevel = jcmbGradeLevel;
        this.jtblSubjectList = jtblSubjectList;
        this.jtblCurrentSubjects = jtblCurrentSubjects;
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        subjectDaoImpl = new SubjectDaoImpl();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (jcmbGradeLevel.getSelectedIndex() > -1) {
            loadSubjectToTable();
        }
    }
    
    private void loadSubjectToTable(){
        String gradeLevelSelected = jcmbGradeLevel.getSelectedItem().toString().trim();
        int gradeLevelId = gradeLevelDaoImpl.getId(Integer.parseInt(gradeLevelSelected));
        DefaultTableModel tableModel = (DefaultTableModel) jtblSubjectList.getModel();
        tableModel.setRowCount(0);
        Object[] subjectList = subjectDaoImpl.getAllSubjectsByGradeLevelId(gradeLevelId).toArray();
        
        for(Object o : subjectList){
            Subject s = (Subject)o;
            Object[] rowData = {
                s.getSubjectId(),s.getSubjectTitle(),
                s.getSubjectCode(), s.getGradeLevel().getLevelNo()
            };
            tableModel.addRow(rowData);
        }
        jtblSubjectList.setModel(tableModel);
    }
}
