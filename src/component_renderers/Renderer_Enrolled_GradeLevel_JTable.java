
package component_renderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Enrolled_GradeLevel_JTable extends DefaultTableCellRenderer{

    private final int gradeLevelColIndex;
    
    public Renderer_Enrolled_GradeLevel_JTable(int gradeLevelColIndex){
        this.gradeLevelColIndex = gradeLevelColIndex;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        if(column == gradeLevelColIndex){
            if(value instanceof Integer){
                int gradeLevel = Integer.parseInt(value.toString().trim());
                if(gradeLevel == 0){
                    ((JLabel)cellComponent).setText("Kindergarten");
                }else{
                  ((JLabel)cellComponent).setText(""+gradeLevel);
                }
            }
        }
        return this;
    }
    
}
