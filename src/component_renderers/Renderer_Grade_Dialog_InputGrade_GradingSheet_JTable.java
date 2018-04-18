
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Grade_Dialog_InputGrade_GradingSheet_JTable extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        if(column == 7){
            if(value instanceof Integer){
                int grade = Integer.parseInt(value.toString());
                if(grade <= 74){
                    cellComponent.setBackground(Color.RED);
                    cellComponent.setForeground(Color.YELLOW);
                }
                else if(grade >= 75){
                    cellComponent.setBackground(Color.GREEN);
                    cellComponent.setForeground(Color.BLACK);
                }
            }else{
                cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(Color.BLACK);
            }
        }else if(column == 8){
            if(value instanceof Object){
                String remark = value.toString();
                if(remark.equalsIgnoreCase("Passed")){
                    cellComponent.setBackground(Color.YELLOW);
                    cellComponent.setForeground(Color.BLACK);
                }
                else if(remark.equalsIgnoreCase("Summer")){
                    cellComponent.setBackground(Color.PINK);
                    cellComponent.setForeground(Color.BLACK);
                }
            }else{
                cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(Color.BLACK);
            }
        }
        else{
            cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(Color.BLACK);
        }
        
        return cellComponent;
    }
    
    
}
