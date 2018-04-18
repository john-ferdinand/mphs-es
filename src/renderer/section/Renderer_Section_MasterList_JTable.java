/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.section;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Section_MasterList_JTable extends DefaultTableCellRenderer{

    private final int sessionColumnIndex;
    private final int gradeLevelValueColumnIndex;
    private final int sectionTypeColumnIndex;

    public Renderer_Section_MasterList_JTable(int sessionColumnIndex, int gradeLevelValueColumnIndex, int sectionTypeColumnIndex) {
        this.sessionColumnIndex = sessionColumnIndex;
        this.gradeLevelValueColumnIndex = gradeLevelValueColumnIndex;
        this.sectionTypeColumnIndex = sectionTypeColumnIndex;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        if(column == sessionColumnIndex){
            if(value instanceof String){
                String session = value.toString().trim();
                if(session.equalsIgnoreCase("WD")){
                    ((JLabel)cellComponent).setText("Whole Day");
                }
            }
        }
        
        if(column == gradeLevelValueColumnIndex){
            if (value instanceof Integer) {
                if (Integer.parseInt(value.toString().trim()) == 0) {
                    table.setValueAt("Kindergarten", row, column);
                }
            }
        }
        
        if(column == sectionTypeColumnIndex){
            if(value instanceof String){
                if(value.toString().equalsIgnoreCase("R")){
                    ((JLabel)cellComponent).setText("Regular");
                }else if(value.toString().equalsIgnoreCase("S")){
                    ((JLabel)cellComponent).setText("Summer");
                }
            }
        }
        return this;
    }
    
    
    
}
