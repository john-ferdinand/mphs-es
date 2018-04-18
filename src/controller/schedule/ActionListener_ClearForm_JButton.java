
package controller.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ActionListener_ClearForm_JButton implements ActionListener{

    private final Dialog_CreateSchedule view;
    private final JTable jtblSchedule;
    
    public ActionListener_ClearForm_JButton(Dialog_CreateSchedule view){
        this.view = view;
        this.jtblSchedule = view.getJtblSchedule();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Clear Schedule Form?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) jtblSchedule.getModel();
            model.setRowCount(0);
        }
        
    }
    
}
