
package controller.curriculum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class RemoveSelectedSubjectsFromCurrent implements ActionListener{

    private JTable jtblCurrentSubjects;
    private JTable jtblSubjectList;
    
    public RemoveSelectedSubjectsFromCurrent(JTable jtblCurrentSubjects,JTable jtblSubjectList){
        this.jtblCurrentSubjects = jtblCurrentSubjects;
        this.jtblSubjectList = jtblSubjectList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        removeSelectedSubjects();
    }
    
    private void removeSelectedSubjects(){
        int[] selectedRows = jtblCurrentSubjects.getSelectedRows();
        restoreToSubjectList(selectedRows);
        JTableUtil.removeSelectedRows(selectedRows, jtblCurrentSubjects);
    }
    
    private void restoreToSubjectList(int[] rows){
        int currentSubjectsColCount = jtblCurrentSubjects.getColumnCount();
        DefaultTableModel tableModelCurrentSubjects = (DefaultTableModel) jtblCurrentSubjects.getModel();
        DefaultTableModel tableModelSubjectList = (DefaultTableModel) jtblSubjectList.getModel();
        for(int i = 0; i < rows.length; i++){
            Object[] rowData = new Object[currentSubjectsColCount];
            for(int col = 0; col < currentSubjectsColCount; col++){
                rowData[col] = tableModelCurrentSubjects.getValueAt(i, col);
            }
            tableModelSubjectList.addRow(rowData);
        }
    }
    
}
