
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.gradelevel.GradeLevel;

public class Renderer_GradeLevel_JComboBox extends JLabel implements ListCellRenderer<Object> {

    public Renderer_GradeLevel_JComboBox() {
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Integer){
            if(((Integer)value) == 0){
                this.setText("Kindergarten");
            }else{
                this.setText(""+value);
            }
        }else if(value instanceof GradeLevel){
            GradeLevel gradeLevel = (GradeLevel) value;
            String levelNo = gradeLevel.getLevelNo() == 0? "Kindergarten" : gradeLevel.getLevelNo()+""; 
            this.setText(levelNo);
        }
        else {
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
