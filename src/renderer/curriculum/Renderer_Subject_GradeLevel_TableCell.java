
package renderer.curriculum;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.write.WritableFont;

/**
 *
 * @author Jordan
 */
public class Renderer_Subject_GradeLevel_TableCell extends DefaultTableCellRenderer{

    private final int subjectListGradeLevelColumn;

    public Renderer_Subject_GradeLevel_TableCell(int subjectListGradeLevelColumn) {
        this.subjectListGradeLevelColumn = subjectListGradeLevelColumn;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (column == subjectListGradeLevelColumn) {
            if (value instanceof Integer) {
                int level = Integer.parseInt(value.toString().trim());
                if (level == 0) {
                    ((JLabel) cellComponent).setText("Kindergarten");
                } else {
                    ((JLabel) cellComponent).setText("" + level);
                }
            }
        }
        if (row % 2 == 0) {
            cellComponent.setBackground(Color.YELLOW);
            ((JLabel) cellComponent).setForeground(Color.BLACK);
        } else {
            cellComponent.setBackground(new Color(20, 190, 214));
            ((JLabel) cellComponent).setForeground(Color.BLACK);
        }
        
        if(isSelected){
            ((JLabel)cellComponent).setFont(new Font("Tahoma", Font.BOLD, 12));
        }
        return cellComponent;
    }
    
    
    
}
