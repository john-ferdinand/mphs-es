/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import component_model_loader.ScheduleJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import view.schedule.Panel_ClassSchedules;

/**
 *
 * @author Jordan
 */
public class ItemListener_ScheduleMasterRecord_GradeLevel_JComboBox implements ItemListener{
    
    private final Panel_ClassSchedules view;
    private final ScheduleJCompModelLoader scheduleJCompModelLoader;
    
    public ItemListener_ScheduleMasterRecord_GradeLevel_JComboBox(Panel_ClassSchedules view){
        this.view = view;
        scheduleJCompModelLoader = new ScheduleJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            JComboBox gradeLevelCombo = (JComboBox) e.getSource();
            JTable table = view.getJtblScheduleMasterList();
            SchoolYear schoolYear = (SchoolYear) view.getJcmbSchoolYear().getSelectedItem();
            GradeLevel gradeLevel = (GradeLevel) gradeLevelCombo.getSelectedItem();
            table.setModel(scheduleJCompModelLoader.getSchedulesBySchoolYearGradeLevelAndStatus(table, schoolYear, gradeLevel, true));
        }
    }
    
    
    
}
