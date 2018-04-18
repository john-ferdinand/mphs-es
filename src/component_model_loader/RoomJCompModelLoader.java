package component_model_loader;

import daoimpl.RoomDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.room.Room;
import model.schoolyear.SchoolYear;

/**
 *
 * @author
 */
public class RoomJCompModelLoader {

    private final RoomDaoImpl roomDaoImpl;

    public RoomJCompModelLoader() {
        roomDaoImpl = new RoomDaoImpl();
    }

    public DefaultTableModel getAllRoomsInfo(JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] roomList = roomDaoImpl.getAllRoomInfo().toArray();
        for (Object o : roomList) {
            Room r = (Room) o;
            Object[] rowData = {
                r.getRoomID(),
                r.getRoomName(),
                r.getBuildingName(),
                r.getCapacity(),
                r.getStatus() == true ? "Yes" : "No",
                r.getDateCreated(),
                r.getDescription()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultTableModel getAllActiveRooms(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Room> roomList = roomDaoImpl.getAllActiveRooms();
        for (Room r : roomList) {
            Object[] rowData = {
                r.getRoomID(),
                r.getRoomName(),
                r.getBuildingName(),
                r.getCapacity(),
                r.getStatus() == true ? "Yes" : "No",
                r.getDateCreated(),
                r.getDescription()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultComboBoxModel getAllActiveRooms(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Room> roomList = roomDaoImpl.getAllActiveRooms();
        for (Room room : roomList) {
            comboModel.addElement(room);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getRoomsAvailableFor(String sectionSession, SchoolYear schoolYear) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Room> roomList = roomDaoImpl.getRoomsAvailableFor(sectionSession, schoolYear);
        for (Room room : roomList) {
            comboModel.addElement(room);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllActiveRoomId(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Room> roomList = roomDaoImpl.getAllActiveRooms();
        for (Room r : roomList) {
            comboModel.addElement(r.getRoomID());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    public DefaultTableModel getAllRoomsInfoByWildCard(JTextField jtfSearchBox, JTable jtblRoomMasterList) {
        DefaultTableModel tableModel = (DefaultTableModel) jtblRoomMasterList.getModel();
        tableModel.setRowCount(0);
        Object[] roomList = roomDaoImpl.getAllRoomsInfoByWildCard(jtfSearchBox.getText().trim()).toArray();
        for (Object o : roomList) {
            Room r = (Room) o;
            Object[] rowData = {
                r.getRoomID(),
                r.getRoomName(),
                r.getBuildingName(),
                r.getCapacity(),
                r.getStatus() == true ? "Yes" : "No",
                r.getDateCreated(),
                r.getDescription()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

}
