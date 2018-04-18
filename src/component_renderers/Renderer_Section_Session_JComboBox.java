package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Section_Session_JComboBox extends JLabel implements ListCellRenderer<Object> {

    public Renderer_Section_Session_JComboBox(){
        this.setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Object) {
            if (value != null) {
                Object valueToDisplay = getValueToDisplay(value.toString().trim());
                this.setText(String.valueOf(valueToDisplay));
            }else{
                this.setText("Select");
            }
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

    private Object getValueToDisplay(String session) {
        return session.equalsIgnoreCase("WD") ? "Whole Day" : session;
    }

}
