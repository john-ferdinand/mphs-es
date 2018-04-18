
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.faculty.Faculty;

/**
 *
 * @author Jordan
 */
public class Renderer_Schedule_Faculty_CellJComboBox extends JLabel implements ListCellRenderer<Object>{
    
    public Renderer_Schedule_Faculty_CellJComboBox(){
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Faculty) {
            Faculty f = (Faculty) value;
            this.setText(f.getLastName() + ", " + f.getFirstName() + " " + f.getMiddleName());
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
