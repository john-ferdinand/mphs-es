package controller.subjectcategory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class RemoveSelectedSubjectsFromAssigned implements ActionListener{

    private JTable jtblAssignedSubjects;
    
    public RemoveSelectedSubjectsFromAssigned(JTable jtblAssignedSubjects){
        this.jtblAssignedSubjects = jtblAssignedSubjects;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        removeRowsSelected();
    }
    
    private void removeRowsSelected() {
        int[] indices = jtblAssignedSubjects.getSelectedRows();
        for (int i = indices.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) jtblAssignedSubjects.getModel();
            tableModel.removeRow(indices[i]);
            ;
        }
    }
    
    
}
