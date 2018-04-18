
package controller.editschedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.room.Room;
import model.section.Section;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author Jordan
 */
public class Edit_Controller_AddRow_JButton implements ActionListener{
    
    private final Dialog_EditSchedule view;

    public Edit_Controller_AddRow_JButton(Dialog_EditSchedule view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJbtnAddRow()){
            Object[] blankRowData = {};
            ((DefaultTableModel)view.getJtblSchedule().getModel()).addRow(blankRowData);
            int lastRow = view.getJtblSchedule().getRowCount()-1;
            String sectionSession = ((Section)view.getJcmbSection().getSelectedItem()).getSectionSession().trim();
            String room = ((Room)view.getJcmbRoom().getSelectedItem()).getRoomName().trim();
            view.getJtblSchedule().getModel().setValueAt(room, lastRow, 5);
            view.getJtblSchedule().getModel().setValueAt(sectionSession, lastRow, 6);
        }
    }
    
}
