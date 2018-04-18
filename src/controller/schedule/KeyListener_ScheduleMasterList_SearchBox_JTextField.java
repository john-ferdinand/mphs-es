/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import component_model_loader.ScheduleJCompModelLoader;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import model.schoolyear.SchoolYear;
import view.schedule.Panel_ClassSchedules;

/**
 *
 * @author Jordan
 */
public class KeyListener_ScheduleMasterList_SearchBox_JTextField implements KeyListener{
    
    private final Panel_ClassSchedules view;
    private final ScheduleJCompModelLoader scheduleJCompModelLoader;
    
    public KeyListener_ScheduleMasterList_SearchBox_JTextField(Panel_ClassSchedules view) {
        this.view = view;
        this.scheduleJCompModelLoader = new ScheduleJCompModelLoader();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            if(!view.getJtfSearch().getText().isEmpty()){
                String searchKeyword = view.getJtfSearch().getText().trim();
                JTable table = view.getJtblScheduleMasterList();
                SchoolYear schoolYear = (SchoolYear) view.getJcmbSchoolYear().getSelectedItem();
                table.setModel(scheduleJCompModelLoader.getSchedulesByWildCardSchoolYearIdAndStatus(table, schoolYear, searchKeyword, true));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    
}
