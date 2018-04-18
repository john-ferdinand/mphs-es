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
public class Renderer_Grade_ReportCard_JTableCell extends DefaultTableCellRenderer{

    private final int columnToRender;
    
    public Renderer_Grade_ReportCard_JTableCell(int columnToRender){
        this.columnToRender = columnToRender;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (column == columnToRender) {
            if (value instanceof Subject) {
                Subject s = (Subject) value;
                ((JLabel) cellComponent).setText("" + s.getSubjectCode());
            }
        }
        return cellComponent;
    }
}
