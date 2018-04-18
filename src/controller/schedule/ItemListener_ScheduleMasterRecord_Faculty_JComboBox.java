package controller.schedule;

import component_model_loader.ScheduleJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import view.schedule.Panel_ClassSchedules;

/**
 *
 * @author Jordan
 */
public class ItemListener_ScheduleMasterRecord_Faculty_JComboBox implements ItemListener{
    
    private final Panel_ClassSchedules view;
    private final ScheduleJCompModelLoader scheduleJCompModelLoader;
    
    public ItemListener_ScheduleMasterRecord_Faculty_JComboBox(Panel_ClassSchedules view){
        this.view = view;
        scheduleJCompModelLoader = new ScheduleJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            JComboBox facultyComboBox = (JComboBox) e.getSource();
            JTable table = view.getJtblScheduleMasterList();
            SchoolYear schoolYear = (SchoolYear) view.getJcmbSchoolYear().getSelectedItem();
            Faculty faculty = (Faculty)facultyComboBox.getSelectedItem();
            
            table.setModel(scheduleJCompModelLoader.getAllSchedulesBySchoolYearFacultyAndStatus(table, schoolYear, faculty, true));
        }
    }
    
}
