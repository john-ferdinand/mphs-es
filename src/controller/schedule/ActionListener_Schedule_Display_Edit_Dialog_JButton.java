/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.schoolyear.SchoolYear;
import model.section.Section;
import view.schedule.Dialog_EditSchedule;
import view.schedule.Panel_ClassSchedules;

/**
 *
 * @author Jordan
 */
public class ActionListener_Schedule_Display_Edit_Dialog_JButton implements ActionListener{
    
    private final Panel_ClassSchedules view;

    public ActionListener_Schedule_Display_Edit_Dialog_JButton(Panel_ClassSchedules view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getJcmbSection().getSelectedIndex() > -1) {
            Section section = (Section) view.getJcmbSection().getSelectedItem();
            SchoolYear schoolYear = (SchoolYear) view.getJcmbSchoolYear().getSelectedItem();
            Dialog_EditSchedule editDialog = new Dialog_EditSchedule(null, false, section,schoolYear);
            editDialog.setPreferredSize(new Dimension(1250, 700));
            editDialog.pack();
            editDialog.setLocationRelativeTo(null);
            editDialog.setVisible(true);
            editDialog.initForm();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a section from the drop down.");
        }
    }
    
    
    
}
