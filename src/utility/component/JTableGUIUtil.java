package component_utility;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JTableGUIUtil {

    public static void deleteAllRows(final DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void copyTableSectionData(JTable aSourceJTable, JTable aDestinationRowTable, double gwa, JComboBox cb1, JComboBox cb2) {
        int selectedRow = aSourceJTable.getSelectedRow(); //selected row which has the data to be added
        Object selected = aSourceJTable.getValueAt(selectedRow, 1);
        DefaultTableModel curriculumSubjectsModel = null;
        int destinationRows = aDestinationRowTable.getRowCount();

        Object[] destinationObj = new Object[destinationRows];

        for (int j = 0; j < destinationRows; j++) {
            destinationObj[j] = aDestinationRowTable.getValueAt(j, 1);
        }

        if (Double.parseDouble(String.valueOf(aSourceJTable.getValueAt(aSourceJTable.getSelectedRow(), 2))) < gwa) {
            Object[] options = {"Yes, please",
                "No!"};
            int n = JOptionPane.showOptionDialog(null, aSourceJTable.getValueAt(aSourceJTable.getSelectedRow(), 1)
                    + " doesn't meet requirements. Still add this student?",
                    "Message",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[1]); //default button title

            if (n == JOptionPane.YES_OPTION) {
                if (cb1.getSelectedItem() != cb2.getSelectedItem()) {
                    JOptionPane.showMessageDialog(null, "Cannot add "
                            + aSourceJTable.getValueAt(aSourceJTable.getSelectedRow(), 1)
                            + " to Grade " + cb2.getSelectedItem());
                } else {
                    curriculumSubjectsModel = (DefaultTableModel) aDestinationRowTable.getModel();

                    Object[] rowData = new Object[aSourceJTable.getColumnCount()];
                    int selectedRowIndex = aSourceJTable.getSelectedRow();
                    for (int i = 0; i < aSourceJTable.getColumnCount(); i++) {
                        rowData[i] = aSourceJTable.getValueAt(selectedRowIndex, i);
                    }

                    curriculumSubjectsModel.addRow(rowData);
                    aDestinationRowTable.setModel(curriculumSubjectsModel);
                }
            }

        } else if (cb1.getSelectedItem() != cb2.getSelectedItem()) {
            JOptionPane.showMessageDialog(null, "Cannot add "
                    + aSourceJTable.getValueAt(aSourceJTable.getSelectedRow(), 1)
                    + " to Grade " + cb2.getSelectedItem());
        } else if (Arrays.asList(destinationObj).contains(selected)) {
            JOptionPane.showMessageDialog(null, selected + " is already on the list.");
        } else {
            curriculumSubjectsModel = (DefaultTableModel) aDestinationRowTable.getModel();

            Object[] rowData = new Object[aSourceJTable.getColumnCount()];
            int selectedRowIndex = aSourceJTable.getSelectedRow();
            for (int i = 0; i < aSourceJTable.getColumnCount(); i++) {
                rowData[i] = aSourceJTable.getValueAt(selectedRowIndex, i);
            }

            curriculumSubjectsModel.addRow(rowData);
            aDestinationRowTable.setModel(curriculumSubjectsModel);
        }

    }

    public static void setCellsAlignment(JTable table, int alignment) {

        //Usage:
        // JTableGUIUtil.setCellsAlignment(table, SwingConstants.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    public static void resizeColumnWidth(JTable table, int minimumCellWidth) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
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
