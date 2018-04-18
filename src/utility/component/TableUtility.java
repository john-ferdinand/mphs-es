package utility.component;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author John Ferdinand Antonio
 */
public class TableUtility {
    public static void setTableColumnWidth(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
            
        }
    }
    
    
    public static void resizeColumnWidth(JTable table) {
    TableColumnModel columnModel = table.getColumnModel();
    int cumulativeActual = 0;
    int padding = 15;
    for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
        int width = 50; // Min width
        TableColumn column = columnModel.getColumn(columnIndex);
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, columnIndex);
            Component comp = table.prepareRenderer(renderer, row, columnIndex);
            width = Math.max(comp.getPreferredSize().width + padding, width);
        }
        if (columnIndex < table.getColumnCount() - 1) {
            column.setPreferredWidth(width);
            cumulativeActual += column.getWidth();
        } else { //LAST COLUMN
            //Use the parent's (viewPort) width and subtract the previous columbs actual widths.
            column.setPreferredWidth((int) table.getParent().getSize().getWidth() - cumulativeActual);
        }
    }
}
}
