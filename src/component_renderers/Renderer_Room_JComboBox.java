
package component_renderers;

import daoimpl.RoomDaoImpl;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.room.Room;

/**
 *
 * @author Jordan
 */
public class Renderer_Room_JComboBox extends JLabel implements ListCellRenderer<Object>{

    private RoomDaoImpl roomDaoImpl;
    
    public Renderer_Room_JComboBox(){
        this.setOpaque(true);
        roomDaoImpl = new RoomDaoImpl();
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Room){
           Room room = (Room)value;
           this.setText(""+room.getRoomName());
        }else{
            this.setText("Select");
        }
        
        if (isSelected) {
            this.setBackground(Color.YELLOW);
//            this.setBackground(list.getSelectionBackground());
            this.setForeground(Color.BLACK);
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        return this;
    }
}
