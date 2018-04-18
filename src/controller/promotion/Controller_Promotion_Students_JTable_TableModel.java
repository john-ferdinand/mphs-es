
package controller.promotion;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import view.promotion.Dialog_Promotion;

/**
 *
 * @author Jordan
 */
public class Controller_Promotion_Students_JTable_TableModel implements TableModelListener{

    private final Dialog_Promotion view;

    public Controller_Promotion_Students_JTable_TableModel(Dialog_Promotion view) {
        this.view = view;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        view.getJbtnPromote().setEnabled(view.getJtblStudents().getRowCount() > 0);
        if(e.getColumn() == 5){
            TableModel tableModel = (TableModel) e.getSource();
            int passingGrade = 75;
            for(int row = 0; row < tableModel.getRowCount(); row++){
                if(tableModel.getValueAt(row, 3) != null){
                    int currentGradeLevelNo = Integer.parseInt(tableModel.getValueAt(row, 2).toString().trim());
                    int finalGrade = Integer.parseInt(tableModel.getValueAt(row,3).toString().trim());
                    if(finalGrade >= passingGrade){
                        tableModel.setValueAt("Passed", row, 4);
                        tableModel.setValueAt(currentGradeLevelNo+1, row, 6);
                    }else{
                        tableModel.setValueAt("Failed", row, 4);
                        tableModel.setValueAt("Summer", row, 6);
                    }
                }
            }
        }
    }
}
