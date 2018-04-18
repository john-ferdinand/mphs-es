
package controller.room;

import daoimpl.RoomDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.room.Room;
/**
 *
 * @author Paul
 */
public class EditRoom implements ActionListener {
    
    private final int roomIdOfSelected;
    
    private final JTextField jtfRoomName;
    private final JTextField jtfBldgName;
    private final JComboBox jcmbStatus;
    private final JTextField jtfCapacity;
    private final JTextArea jtaNotes;
    
    private final JDialog jdlgRoomCreate;
    
    private RoomDaoImpl roomDaoImpl;
    
    public EditRoom(int roomIdOfSelected, JTextField jtfRoomName, JTextField jtfBldgName, 
            JComboBox jcmbStatus, JTextField jtfCapacity, JTextArea jtaNotes, JDialog jdlgRoomCrud){
        
        this.roomIdOfSelected = roomIdOfSelected;
        
        
        this.jtfRoomName = jtfRoomName;
        this.jtfBldgName = jtfBldgName;
        this.jcmbStatus = jcmbStatus;
        this.jtfCapacity = jtfCapacity;
        this.jtaNotes = jtaNotes;
        
        this.jdlgRoomCreate = jdlgRoomCrud;
        
        roomDaoImpl = new RoomDaoImpl();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Update Room?","Update",JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            if(updateSubject()){
                JOptionPane.showMessageDialog(null, "Successfully Updated Room!");
            
                jdlgRoomCreate.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Failed to Update Room!");
            }
        
        }
        
       
    }
    
    private boolean updateSubject(){
        boolean isUpdated = false;
        Room room = new Room(); 
        room.setRoomID(roomIdOfSelected);        
        room.setRoomName(jtfRoomName.getText().trim());
        room.setBuildingName(jtfBldgName.getText().trim());
        room.setCapacity(jtfCapacity.getText().trim());
        room.setStatus(jcmbStatus.getSelectedItem().equals(room.getStatus()));
        room.setDescription(jtaNotes.getText().trim());

        isUpdated = roomDaoImpl.updateRoom(room);
        return isUpdated;
    
    }
    
    
}

