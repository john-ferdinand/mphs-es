
package controller.editschedule;

import controller.schedule.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Edit_RemoveSelectedEntryController implements ActionListener{

    private final JTable jtblSchedule;
    
    public Edit_RemoveSelectedEntryController(JTable jtblSchedule){
        this.jtblSchedule = jtblSchedule;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Remove?", "Remove Confirmation",JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            
        }
    }
    
}
