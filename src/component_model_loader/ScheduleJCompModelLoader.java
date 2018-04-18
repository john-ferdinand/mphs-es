
package component_model_loader;

import daoimpl.ScheduleDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.section.Section;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ScheduleJCompModelLoader {
    
    private ScheduleDaoImpl scheduleDaoImpl;
    
    public ScheduleJCompModelLoader(){
        scheduleDaoImpl = new ScheduleDaoImpl();
    }
    
    public DefaultTableModel getAllSchedulesBySchoolYearFacultyAndStatus(JTable table, SchoolYear schoolYear, Faculty faculty,boolean isActive){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        int schoolYearId = schoolYear.getSchoolYearId();
        int facultyId = faculty.getFacultyID();
        List<Schedule> schedList = scheduleDaoImpl.getAllSchedulesBySchoolYearFacultyAndStatus(schoolYearId, facultyId, isActive);
        for(Schedule s : schedList){
            Object[] rowData = {
                s.getScheduleID(),s.getDay(),intToTimeFormat(s.getStartTime()), intToTimeFormat(s.getEndTime()),
                s.getSection().getSectionName(),s.getSubject().getSubjectCode(),s.getRoom().getRoomName(),
                s.getFaculty().getLastName()+", "+s.getFaculty().getFirstName()+" "+ s.getFaculty().getMiddleName(),
                s.getScheduleSession()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getSchedulesBySchoolYearGradeLevelAndStatus(JTable table, SchoolYear schoolYear, GradeLevel gradeLevel,boolean isActive){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Schedule> schedList = scheduleDaoImpl.getSchedulesBy(schoolYear, gradeLevel, isActive);
        for(Schedule s : schedList){
            Object[] rowData = {
                s.getScheduleID(),s.getDay(),intToTimeFormat(s.getStartTime()), intToTimeFormat(s.getEndTime()),
                s.getSection().getSectionName(),s.getSubject().getSubjectCode(),s.getRoom().getRoomName(),
                s.getFaculty().getLastName()+", "+s.getFaculty().getFirstName()+" "+ s.getFaculty().getMiddleName(),
                s.getScheduleSession()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getSchedulesBySectionAndSchoolYear(JTable table, Section section, SchoolYear schoolYear){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Schedule> schedList = scheduleDaoImpl.getSchedulesBy(section,schoolYear);
        for(Schedule s : schedList){
            Object[] rowData = {
                s.getScheduleID(),s.getDay(),intToTimeFormat(s.getStartTime()), intToTimeFormat(s.getEndTime()),
                s.getSection().getSectionName(),s.getSubject().getSubjectCode(),s.getRoom().getRoomName(),
                s.getFaculty().getLastName()+", "+s.getFaculty().getFirstName()+" "+ s.getFaculty().getMiddleName(),
                s.getScheduleSession()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getSchedulesByWildCardSchoolYearIdAndStatus(JTable table, SchoolYear schoolYear, String searchKeyword,boolean isActive){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        int schoolYearId = schoolYear.getSchoolYearId();
        List<Schedule> scheduleList = scheduleDaoImpl.getSchedulesByWildCardSchoolYearIdAndStatus(searchKeyword, schoolYearId, isActive);
        for(Schedule s : scheduleList){
            Object[] rowData = {
                s.getScheduleID(),s.getDay(),intToTimeFormat(s.getStartTime()), intToTimeFormat(s.getEndTime()),
                s.getSection().getSectionName(),s.getSubject().getSubjectCode(),s.getRoom().getRoomName(),
                s.getFaculty().getLastName()+", "+s.getFaculty().getFirstName()+" "+ s.getFaculty().getMiddleName(),
                s.getScheduleSession()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getSchedulesByDaySchoolYearAndStatus(JTable table, SchoolYear schoolYear, String day,boolean isActive){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        int schoolYearId = schoolYear.getSchoolYearId();
        List<Schedule> scheduleList = scheduleDaoImpl.getSchedulesByDaySchoolYearAndStatus(day, schoolYearId, isActive);
        for(Schedule s : scheduleList){
            Object[] rowData = {
                s.getScheduleID(),s.getDay(),intToTimeFormat(s.getStartTime()), intToTimeFormat(s.getEndTime()),
                s.getSection().getSectionName(),s.getSubject().getSubjectCode(),s.getRoom().getRoomName(),
                s.getFaculty().getLastName()+", "+s.getFaculty().getFirstName()+" "+ s.getFaculty().getMiddleName(),
                s.getScheduleSession()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    private String intToTimeFormat(int time) {
        int hours = time/100;
        int minutes = (time%100 * 10)/10;
        System.out.println("hours: "+hours);
        System.out.println("minutes: "+minutes);
        String str = String.format("%02d%s%02d", hours,":",minutes);
        return str;
    }
    
    
}
