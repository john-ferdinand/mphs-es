package controller.room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.rooms.Dialog_RoomCreate;

/**
 *
 * @author Paul
 */
public class DisplayCreateRoomDialog implements ActionListener{
    
   private final JTable  jtblRoomMasterList;
   public DisplayCreateRoomDialog (JTable jtblRoomMasterList, String actionCommand){
        this.jtblRoomMasterList =  jtblRoomMasterList;
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (hasSubjectSelected()) {
            String action = getAction(e);
            String dialogTitle = getDialogTitle(e);
            
            if (action.equalsIgnoreCase("create")) {
                displayDialog(dialogTitle, action);
            } else if (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("view")) {
                validateSelection(dialogTitle, action);
            }
//        }
    }

    private String getDialogTitle(ActionEvent e) {
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("create") ? "Create New Room"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Room"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Room Info(View)"
                : e.getActionCommand().equalsIgnoreCase("print") ? "Print" : "";
        return dialogTitle;
    }

    private String getAction(ActionEvent e) {
        String action = e.getActionCommand().equalsIgnoreCase("create") ? "create"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "edit"
                : e.getActionCommand().equalsIgnoreCase("view") ? "view"
                : e.getActionCommand().equalsIgnoreCase("print") ? "print" : "";
        return action;
    }

    private boolean hasRoomSelected() {
        boolean hasRoomtSelected = false;
        if (jtblRoomMasterList.getSelectedRow() > -1) {
            hasRoomtSelected = true;
        }
        return hasRoomtSelected;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasRoomSelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a Room from the list.");
        }
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasRoomSelected()) {
                int roomIdColIdx = 0;
                int rowSelected = jtblRoomMasterList.getSelectedRow();
                int roomId = Integer.parseInt(jtblRoomMasterList.getValueAt(rowSelected, roomIdColIdx).toString());
                Dialog_RoomCreate jdlgRoomCreate = new Dialog_RoomCreate(null, true, action, roomId);
                jdlgRoomCreate.setTitle(dialogTitle);
                jdlgRoomCreate.setLocationRelativeTo(null);
                jdlgRoomCreate.setVisible(true);
            }
        } else {
            Dialog_RoomCreate jdlgRoomCreate = new Dialog_RoomCreate(null, true, action);
            jdlgRoomCreate.setTitle(dialogTitle);
            jdlgRoomCreate.setLocationRelativeTo(null);
            jdlgRoomCreate.setVisible(true);
        }
    }
    }