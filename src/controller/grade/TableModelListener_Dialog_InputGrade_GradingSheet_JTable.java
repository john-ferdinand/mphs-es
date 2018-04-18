package controller.grade;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import view.grades.View_Dialog_InputGrade;

/**
 *
 * @author Jordan
 */
public class TableModelListener_Dialog_InputGrade_GradingSheet_JTable implements TableModelListener {

    private final View_Dialog_InputGrade view;

    public TableModelListener_Dialog_InputGrade_GradingSheet_JTable(View_Dialog_InputGrade view) {
        this.view = view;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int colChanged = e.getColumn();
        DefaultTableModel tableModel = (DefaultTableModel) e.getSource();
        if (colChanged == 3|| colChanged == 4 || colChanged == 5 || colChanged == 6) {
            if (tableModel.getRowCount() > 0) {
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    int sum = 0;
                    int divisor = 0;
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        if (col == 3 || col == 4 || col == 5 || col == 6) {
                            Object value = tableModel.getValueAt(row, col);
                            if (value != null && !value.toString().trim().isEmpty()) {
                                divisor++;
                                sum += Integer.parseInt(tableModel.getValueAt(row, col).toString());
                            }
                            if(col == 6){
                                if(value == null || value.toString().trim().isEmpty()){
                                    tableModel.setValueAt("", row, 7);
                                    tableModel.setValueAt("", row, 8);
                                }
                            }
                        }
                    }
                    if (isInteger(tableModel.getValueAt(row, 6))) {
                        if (divisor != 0) {
                            int finalGrade = sum / divisor;
                            tableModel.setValueAt(finalGrade, row, 7);
                            tableModel.setValueAt(finalGrade >= 75 ? "Passed" : "Summer", row, 8);
                        }
                    }
                    
                }
            }
        }
    }
    
    private boolean isInteger(Object number) {
        boolean isInteger = false;
        if (number != null) {
            try {
                Integer.parseInt(number.toString().trim());
                isInteger = true;
            } catch (NumberFormatException e) {
                isInteger = false;
            }
        }
        return isInteger;
    }
}
