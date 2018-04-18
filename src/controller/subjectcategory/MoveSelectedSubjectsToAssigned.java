
package controller.subjectcategory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class MoveSelectedSubjectsToAssigned implements ActionListener{

    private JTable jtblSubjectsMasterList;
    private JTable jtblAssignedSubjects;
    private ArrayList<String> rejectedData;
    
    public MoveSelectedSubjectsToAssigned(JTable jtblSubjectsMasterList, JTable jtblAssignedSubjects) {
        this.jtblSubjectsMasterList = jtblSubjectsMasterList;
        this.jtblAssignedSubjects = jtblAssignedSubjects;
        rejectedData = new ArrayList<>();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        add();
        if(rejectedData.size() > 0){
            displayRejectedData();
            rejectedData.clear();
        }
    }
    
    private boolean exists(String subjectId) {
        boolean exists = false;
        for(int i = 0; i<jtblAssignedSubjects.getRowCount(); i++){
            if(subjectId.equalsIgnoreCase(jtblAssignedSubjects.getValueAt(i, 0).toString().trim())){
                exists = true;
            }
        }
        return exists;
    }
    
    private boolean add() {
        boolean isAdded = false;
        DefaultTableModel dtmSubjectToAssign = (DefaultTableModel) jtblAssignedSubjects.getModel();
        int[] selectedRows = jtblSubjectsMasterList.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            String id = jtblSubjectsMasterList.getValueAt(selectedRows[i], 0).toString();
            String name = jtblSubjectsMasterList.getValueAt(selectedRows[i], 1).toString();
            String code = jtblSubjectsMasterList.getValueAt(selectedRows[i], 2).toString();
//            JOptionPane.showMessageDialog(null,"Id: "+id);
            if (!exists(id.trim())) {
                Object[] rowData = {id, name, code};
                dtmSubjectToAssign.addRow(rowData);
            }else{
                rejectedData.add(id+" "+name+" "+code);
            } 
        }
        removeRowsSelected(selectedRows, jtblSubjectsMasterList);
        
        return isAdded;
    }
    
    private void removeRowsSelected(int[] selectedRows, JTable jTable) {
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.removeRow(selectedRows[i]);
            ;
        }
    }
    
    private void displayRejectedData(){
        String output = "";
        for(int i = 0; i < rejectedData.size(); i++){
           output = output + rejectedData.get(i) + ",\n";
        }
        JOptionPane.showMessageDialog(null,"The following are already on the list of assigned\n"+output);
    }
    
    
}
