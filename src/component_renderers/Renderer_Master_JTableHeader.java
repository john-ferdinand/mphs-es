/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Master_JTableHeader implements UIResource, TableCellRenderer{
    
    private TableCellRenderer original;

    public Renderer_Master_JTableHeader(TableCellRenderer original) {
        this.original = original;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = original.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setFont(component.getFont().deriveFont(Font.BOLD,12));
        return component;
    }
    
    
}
