
package component_editor;

import daoimpl.RoomDaoImpl;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.room.Room;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ScheduleRoomCellEditor extends DefaultCellEditor {

    private final RoomDaoImpl roomDaoImpl;
    private final JComboBox jcmbRoom;
    private final DefaultComboBoxModel jcmbRoomBoxModel;

    public ScheduleRoomCellEditor() {
        super(new JComboBox());
        roomDaoImpl = new RoomDaoImpl();
        jcmbRoom = new JComboBox();
        jcmbRoomBoxModel = getRoomsModel();
        jcmbRoom.setModel(jcmbRoomBoxModel);
        jcmbRoomBoxModel.setSelectedItem(null);
        jcmbRoom.setEditable(false);
        
        jcmbRoom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jcmbRoom;
    }

    @Override
    public Object getCellEditorValue() {
        return jcmbRoom.getSelectedItem();
    }

    private DefaultComboBoxModel getRoomsModel() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Room> list = roomDaoImpl.getAllRoomInfo();
        list.stream().forEach((r) -> {
            model.addElement(r.getRoomName());
        });
        return model;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        boolean cellEditable = super.isCellEditable(anEvent);
        if (cellEditable && anEvent instanceof MouseEvent) {
            cellEditable = ((MouseEvent) anEvent).getClickCount() == 2;
        }
        
        return cellEditable;
    }
    
}
