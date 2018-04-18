/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.editschedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author Jordan
 */
public class Edit_Controller_RemoveRow_JButton implements ActionListener{

    private Dialog_EditSchedule view;

    public Edit_Controller_RemoveRow_JButton(Dialog_EditSchedule view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJbtnRemoveEntry()){
            int[] rowsSelected = view.getJtblSchedule().getSelectedRows();
            if(rowsSelected.length > 0){
                removeSelectedRows(rowsSelected, view.getJtblSchedule());
            }
        }
    }
    
    private void removeSelectedRows(int[] selectedRows, JTable jTable) {
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.removeRow(selectedRows[i]);
        }
    }
    
}
