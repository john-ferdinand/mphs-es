package controller.editschedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.room.Room;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Edit_ItemListener_Room_JComboBox implements ItemListener{

    private final Dialog_EditSchedule view;
    private final JComboBox jcmbRoom;
    private final JTable jtblSchedule;
    
    public Edit_ItemListener_Room_JComboBox(Dialog_EditSchedule view){
        this.view = view;
        this.jcmbRoom = view.getJcmbRoom();
        this.jtblSchedule = view.getJtblSchedule();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(jcmbRoom.getSelectedIndex() > -1){
            Room room = (Room)jcmbRoom.getSelectedItem();
            for(int i = 0; i<jtblSchedule.getRowCount(); i++){
                jtblSchedule.setValueAt(room, i, 5);
            }
        }
    }
    
    
    private boolean isOccupiedByAM(){
        boolean isOccupied = false;
        return isOccupiedByAM();
    }
    
    private boolean isOccupiedByPM(){
        boolean isOccupied = false;
        return isOccupied;
    }
}
