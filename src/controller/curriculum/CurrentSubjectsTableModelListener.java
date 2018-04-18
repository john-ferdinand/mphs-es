
package controller.curriculum;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Jordan
 */
public class CurrentSubjectsTableModelListener implements TableModelListener{

    private final JLabel jlblTotalHours;
    private final JLabel jlblTotalMinutes;
    private final JTable jtblCurrentSubjects;
    
    public CurrentSubjectsTableModelListener(JLabel jlblTotalMinutes,JLabel jlblTotalHours,JTable jtblCurrentSubjects){
        this.jlblTotalHours = jlblTotalHours;
        this.jlblTotalMinutes = jlblTotalMinutes;
        this.jtblCurrentSubjects = jtblCurrentSubjects;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        TableModel tableModel = (TableModel) e.getSource();
        int total = (getTotal(tableModel));
        int hr = total/60;
        int mins = total%60;
        jlblTotalHours.setText(""+hr);
        jlblTotalMinutes.setText(""+mins);
    }
    
    private int getTotal(TableModel tableModel) {
        int total = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 4) != null) {
                total += Integer.parseInt(tableModel.getValueAt(row, 4).toString().trim());
            }
        }
        return total;
    }
}
