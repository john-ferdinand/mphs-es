/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import model.fee.Fee;

/**
 *
 * @author Acer
 */
public class Renderer_Fee_JTable extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int col) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        if (row % 2 == 0) {
            cellComponent.setBackground(Color.YELLOW);
            ((JLabel) cellComponent).setForeground(Color.BLACK);
        } else {
            cellComponent.setBackground(new Color(20, 190, 214));
            ((JLabel) cellComponent).setForeground(Color.BLACK);
        }
        return cellComponent;
    }
}
    

