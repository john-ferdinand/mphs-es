/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.grade;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_MyAdvisory_MasterList extends DefaultTableCellRenderer{
    
    private final int remarksIndex;

    public Renderer_MyAdvisory_MasterList(int remarksIndex) {
        this.remarksIndex = remarksIndex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    
         if(column == remarksIndex){
             if (table.getValueAt(row, 9) != null) {
                 int generalAverage = Integer.parseInt(table.getValueAt(row, 9).toString().trim());
                 if (generalAverage < 75) {
                     ((JLabel) cellComponent).setText("Summer");
//                     cellComponent.setBackground(Color.YELLOW);
//                     cellComponent.setForeground(Color.BLACK);
                 } else if (generalAverage >= 75) {
                     ((JLabel) cellComponent).setText("Passed");
//                     cellComponent.setBackground(Color.GREEN);
//                     cellComponent.setForeground(Color.BLACK);
                 }
             } else {
                 cellComponent.setBackground(Color.WHITE);
                 cellComponent.setForeground(Color.BLACK);
             }
         }
         
         return this;
    }
    
    
    
}
