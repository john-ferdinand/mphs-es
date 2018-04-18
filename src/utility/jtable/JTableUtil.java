package utility.jtable;

import component_renderers.Renderer_Master_JTableHeader;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jordan
 */
public class JTableUtil {

    public static void removeSelectedRows(int[] selectedRows, JTable jTable) {
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.removeRow(selectedRows[i]);
        }
    }

    public static void moveRowData(JTable tableSource, JTable tableDestination) {
        DefaultTableModel tableModelSource = (DefaultTableModel) tableSource.getModel();
        DefaultTableModel tableModelDestination = (DefaultTableModel) tableDestination.getModel();
        int tableSourceColCount = tableSource.getColumnCount();
        int[] selectedRows = tableSource.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            Object[] rowData = new Object[tableSourceColCount];
            boolean added = false;
            for (int col = 0; col < tableSourceColCount; col++) {
                if (!exists(tableDestination, tableModelSource.getValueAt(i, 0).toString().trim())) {
                    rowData[col] = tableModelSource.getValueAt(selectedRows[i], col);
                    added = true;
                }
            }
            if (added) {
                tableModelDestination.addRow(rowData);
            } else {
//                JOptionPane.showMessageDialog(null, tableModelSource.getValueAt(i, 0) + " already on table");
            }
        }
        removeSelectedRows(selectedRows, tableSource);
    }

    private static boolean exists(JTable table, String valueToCheck) {
        boolean exists = false;
        for (int i = 0; i < table.getRowCount(); i++) {
            exists = table.getValueAt(i, 0).toString().trim().equalsIgnoreCase(valueToCheck.trim());
            if (exists) {
                break;
            }
        }
        return exists;
    }

    public static void applyCustomHeaderRenderer(JTable table) {
        Renderer_Master_JTableHeader customerHeader = new Renderer_Master_JTableHeader(table.getTableHeader().getDefaultRenderer());
        TableColumnModel columnModel = table.getColumnModel();
        for (int col = 0; col < columnModel.getColumnCount(); col++) {
            columnModel.getColumn(col).setHeaderRenderer(customerHeader);
        }
    }

    public static void resizeColumnWidthsOf(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 30; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
