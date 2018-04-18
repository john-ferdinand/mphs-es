
package component_renderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.faculty.Faculty;
import model.room.Room;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class Renderer_Schedule_Summary_JTable extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Faculty) {
            Faculty f = (Faculty) value;
            ((JLabel) cellComponent).setText("" + f.getLastName() + ", " + f.getFirstName() + " " + f.getMiddleName());
        } else if (value instanceof Room) {
            Room r = (Room) value;
            ((JLabel) cellComponent).setText("" + r.getRoomName());
        } else if( value instanceof Subject){
            Subject s = (Subject) value;
            ((JLabel) cellComponent).setText(""+s.getSubjectCode());
        }
        return cellComponent;
    }
    
    
    
}
