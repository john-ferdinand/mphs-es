/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class Renderer_Reports_COR_Schedule_Subject_JTableCell extends DefaultTableCellRenderer{
    
    private final int subjectColumnIndex;
    
    public Renderer_Reports_COR_Schedule_Subject_JTableCell(int subjectColumnIndex){
        this.subjectColumnIndex = subjectColumnIndex;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == subjectColumnIndex){
            if(value instanceof Subject){
                Subject s = (Subject) value;
                StringBuilder sb = new StringBuilder();
                sb.append(s.getSubjectTitle());
                sb.append(" (");
                sb.append(s.getSubjectCode());
                sb.append(")");
                ((JLabel)cellComponent).setText(sb.toString());
            }
        }
        return cellComponent;
    }
    
    
    
}
