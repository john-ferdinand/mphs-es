/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.room.Room;
import model.section.Section;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author Jordan
 */
public class Controller_AddRow_JButton implements ActionListener{
    
    private final Dialog_CreateSchedule view;

    public Controller_AddRow_JButton(Dialog_CreateSchedule view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJbtnAddRow()){
            Object[] blankRowData = {};
            ((DefaultTableModel)view.getJtblSchedule().getModel()).addRow(blankRowData);
            int lastRow = view.getJtblSchedule().getRowCount()-1;
            String sectionSession = ((Section)view.getJcmbSection().getSelectedItem()).getSectionSession().trim();
            String room = ((Room)view.getJcmbRoom().getSelectedItem()).getRoomName().trim();
            view.getJtblSchedule().getModel().setValueAt(room, lastRow, 5);
            view.getJtblSchedule().getModel().setValueAt(sectionSession, lastRow, 6);
        }
    }
    
}
