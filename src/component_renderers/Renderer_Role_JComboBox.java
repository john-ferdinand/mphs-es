
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.role.Role;

/**
 *
 * @author Jordan
 */
public class Renderer_Role_JComboBox extends JLabel implements ListCellRenderer<Object>{
    
    public Renderer_Role_JComboBox(){
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Role){
            Role role = (Role) value;
            this.setText(role.getRoleName().trim());
        }else{
            this.setText("Select");
        }

        //selection formatting
        if (isSelected) {
            this.setBackground(Color.YELLOW);
//            this.setBackground(list.getSelectionBackground());
//            this.setForeground(list.getSelectionForeground());
            this.setForeground(Color.BLACK);
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }

        return this;
    }
    
    
}
