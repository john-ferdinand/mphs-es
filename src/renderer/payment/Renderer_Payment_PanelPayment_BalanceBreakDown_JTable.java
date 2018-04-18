package renderer.payment;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Payment_PanelPayment_BalanceBreakDown_JTable extends DefaultTableCellRenderer{
    
    private final int duedateColIdx;

    public Renderer_Payment_PanelPayment_BalanceBreakDown_JTable(int duedateColIdx) {
        this.duedateColIdx = duedateColIdx;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (column == duedateColIdx) {
            if (value != null) {
                String dueDate = value.toString().trim();
                if (dueDate.contains("1970")) {
                    ((JLabel) cellComponent).setText("");
                }
            }
        }
        return this;
    }
    
    
}
