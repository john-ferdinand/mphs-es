
package controller.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class AddRowToSchedule implements ActionListener {

    private final Dialog_CreateSchedule view;
    private final JTable jtblSchedule;
    private final JComboBox jcmbRoom;
    
    public AddRowToSchedule(Dialog_CreateSchedule view){
        this.view = view;
        this.jtblSchedule = view.getJtblSchedule();
        this.jcmbRoom = view.getJcmbRoom();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel tableModel = (DefaultTableModel) jtblSchedule.getModel();
        int currentRowCount = tableModel.getRowCount();
        tableModel.setRowCount(currentRowCount+1);
        int newlyAddedRowIndex = tableModel.getRowCount()-1;
        
        if(jcmbRoom.getSelectedIndex()>-1){
            tableModel.setValueAt(jcmbRoom.getSelectedItem().toString(), newlyAddedRowIndex, 5);
        }
    }
    
}
