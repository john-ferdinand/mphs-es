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
import utility.form.FormValidator;

public class CreateRoom implements ActionListener, FormValidator {

    private final JDialog jdlgRoomCreate;
    private final JTextField jtfRoomName;
    private final JTextField jtfBldgName;
    private final JTextField jtfCapacity;
    private final JComboBox jcmbStatus;
    private final JTextArea jtaNotes;

    private RoomDaoImpl roomDaoImpl;

    public CreateRoom(JDialog jdlgRoomCreate, JTextField jtfRoomName, JTextField jtfBldgName, JTextField jtfCapacity, JComboBox jcmbStatus, JTextArea jtaNotes) {

        this.jdlgRoomCreate = jdlgRoomCreate;
        this.jtfRoomName = jtfRoomName;
        this.jtfBldgName = jtfBldgName;
        this.jtfCapacity = jtfCapacity;
        this.jcmbStatus = jcmbStatus;
        this.jtaNotes = jtaNotes;

        roomDaoImpl = new RoomDaoImpl();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (formIsValid()) {
            int choice = JOptionPane.showConfirmDialog(null, "Add Room", "Add", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_NO_OPTION) {
                if (addRoom()) {

                    JOptionPane.showMessageDialog(null, "Successfully Added Room");
                    jdlgRoomCreate.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add Room");
                }

            }
        } else {
        }

    }

    private boolean addRoom() {
        boolean isAdded = true;

        Room room = new Room();
        room.setRoomName(jtfRoomName.getText().trim());
        room.setBuildingName(jtfBldgName.getText().trim());
        room.setCapacity(jtfCapacity.getText().trim());
        room.setStatus(jcmbStatus.getSelectedItem().toString().equalsIgnoreCase("Yes")?true:false);
        room.setDescription(jtaNotes.getText().trim());
        isAdded = roomDaoImpl.addRoom(room);
        return isAdded;
    }

    @Override
    public boolean formIsValid() {
        return true;
    }

}
