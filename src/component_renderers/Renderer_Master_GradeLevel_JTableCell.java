/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Master_GradeLevel_JTableCell extends DefaultTableCellRenderer{

    private final int gradeLevelValueColumnIndex;

    public Renderer_Master_GradeLevel_JTableCell(int gradeLevelValueColumnIndex) {
        this.gradeLevelValueColumnIndex = gradeLevelValueColumnIndex;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == gradeLevelValueColumnIndex){
            if (value instanceof Integer) {
                if (Integer.parseInt(value.toString().trim()) == 0) {
                    table.setValueAt("Kindergarten", row, column);
                }
            }
        }
        return cellComponent;
    }
    
    
    
}
