/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.editschedule;

import daoimpl.ScheduleDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.faculty.Faculty;
import model.room.Room;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class Edit_Renderer_Schedule_JTable extends DefaultTableCellRenderer{
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        Object day = table.getModel().getValueAt(row, 0);
        Object startTime = table.getModel().getValueAt(row, 1);
        Object endTime = table.getModel().getValueAt(row, 2);
        Object subject = table.getModel().getValueAt(row, 3);
        Object session = table.getModel().getValueAt(row, 6);

        renderCellValues(value, row, col, c);
        if (allRowColsHasInput(table, row)) {
//            if (hasFacultyConflict(table, row)) {
//                System.out.println("hasFacultyConflict");
//                applyWarningColorTo(c);
//            } 
            
            if (hasDuplicateStartTimeOn(day, 1, startTime, table.getModel())) {
                System.out.println("hasDuplicateStartTimeOn");
                applyWarningColorTo(c);
            } else if (hasDuplicateEndTimeOn(day, 2, endTime, table.getModel())) {
                System.out.println("hasDuplicateEndTimeOn");
                applyWarningColorTo(c);
            } else if (hasDuplicateSubjectOn(day, 3, subject, table.getModel())) {
                System.out.println("hasDuplicateSubjectOn");
                applyWarningColorTo(c);
            } else if (startTimeEqEndtime(startTime, endTime)) {
                System.out.println("startTimeEqEndtime");
                applyWarningColorTo(c);
            } else if (startTimeGtEndtime(startTime, endTime)) {
                System.out.println("startTimeGtEndtime");
                applyWarningColorTo(c);
            } else if (endTimeLtStartTime(startTime, endTime)) {
                System.out.println("endTimeLtStartTime");
                applyWarningColorTo(c);
            } else if(hasExceededSubjectMinutes(subject,table.getModel())){
                System.out.println("hasExceededSubjectMinutes");
                applyWarningColorTo(c);
            } else {
                applyDefaultColorTo(c, row);
            }
        } else {
            applyDefaultColorTo(c, row);
        }

        if (isSelected) {
            c.setBackground(new Color(0, 153, 255));
            c.setForeground(Color.BLACK);
        }
        return c;
    }

    private boolean hasExceededSubjectMinutes(Object subject, TableModel tableModel) {
        SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
        Subject s = (Subject) subject;
        int minutesPerWeek = subjectDaoImpl.getSubjectMinutesPerWeekOf(s);
        int totalMinutes = 0;
       
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if ((tableModel.getValueAt(row, 1) != null) && (tableModel.getValueAt(row, 2) != null)) {
                int sT = Integer.parseInt(tableModel.getValueAt(row, 1).toString().trim().replace(":", ""));
                int eT = Integer.parseInt(tableModel.getValueAt(row, 2).toString().trim().replace(":", ""));
                int duration = durationOf(toMinute(sT),toMinute(eT));
                Subject sbj = (Subject) tableModel.getValueAt(row, 3);
                if (s == sbj) {
                    totalMinutes += duration;
                }
            }
        }
        return totalMinutes != minutesPerWeek;
    }
    
    private int durationOf(int startTime, int endTime){
        return endTime - startTime;
    }
    
    private int toMinute(int time) {
        int hr = (time / 100) ;
        int min = (time % 100);
        int hr_min_equivalent = hr*(60/1);
        int min_min_equivalent = min*(1/1);
        int time_min_conversion = (hr_min_equivalent + min_min_equivalent);
        return time_min_conversion;
    }
    
    private boolean hasDuplicateSubjectOn(Object day, int subjectCol, Object subject, TableModel tableModel) {
        int countOfRecord = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0) != null) {
                String d = tableModel.getValueAt(row, 0).toString().trim();
                if (day.toString().trim().equalsIgnoreCase(d)) {
                    if (tableModel.getValueAt(row, subjectCol) != null) {
                        Subject s = (Subject)subject;
                        Subject sbj = (Subject)tableModel.getValueAt(row, subjectCol);
                        if (s == sbj) {
                            countOfRecord++;
                        }
                    }
                }
            }
        }
        return countOfRecord > 1;
    }
    
    private boolean hasDuplicateStartTimeOn(Object day, int startTimeCol, Object startTime, TableModel tableModel) {
        int countOfRecord = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0) != null) {
                String d = tableModel.getValueAt(row, 0).toString().trim();
                if (day.toString().trim().equalsIgnoreCase(d)) {
                    if (tableModel.getValueAt(row, startTimeCol) != null) {
                        int sTime = Integer.parseInt(startTime.toString().trim().replace(":", ""));
                        int sT = Integer.parseInt(tableModel.getValueAt(row, startTimeCol).toString().trim().replace(":", ""));
                        if (sTime == sT) {
                            countOfRecord++;
                        }
                    }
                }
            }
        }
        return countOfRecord > 1;
    }

    private boolean hasDuplicateEndTimeOn(Object day, int endTimeCol, Object endTime, TableModel tableModel) {
        int countOfRecord = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0) != null) {
                String d = tableModel.getValueAt(row, 0).toString().trim();
                if (day.toString().trim().equalsIgnoreCase(d)) {
                    if (tableModel.getValueAt(row, endTimeCol) != null) {
                        int sTime = Integer.parseInt(endTime.toString().trim().replace(":", ""));
                        int sT = Integer.parseInt(tableModel.getValueAt(row, endTimeCol).toString().trim().replace(":", ""));
                        if (sTime == sT) {
                            countOfRecord++;
                        }
                    }
                }
            }
        }
        return countOfRecord > 1;
    }

    private boolean allRowColsHasInput(JTable table, int tRow) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        boolean hasDay = (tableModel.getValueAt(tRow, 0) != null);
        boolean hasStartTime = (tableModel.getValueAt(tRow, 1) != null);
        boolean hasEndTime = (tableModel.getValueAt(tRow, 2) != null);
        boolean hasSubject = (tableModel.getValueAt(tRow, 3) != null);
        boolean hasFaculty = (tableModel.getValueAt(tRow, 4) != null);
        boolean hasRoom = (tableModel.getValueAt(tRow, 5) != null);
        boolean hasSession = (tableModel.getValueAt(tRow, 6) != null);
        return (hasDay && hasStartTime && hasEndTime && hasSubject && hasFaculty && hasRoom && hasSession);
    }

    private void renderCellValues(Object value, int row, int col, Component c) {
        JLabel cellLabel = ((JLabel) c);
        if (col == 3 || col == 4 || col == 5) {
            if (value instanceof Subject) {
                cellLabel.setText(((Subject) value).getSubjectCode());
            } else if (value instanceof Faculty) {
                cellLabel.setText(((Faculty) value).getLastName() + ", " + ((Faculty) value).getFirstName() + " " + ((Faculty) value).getMiddleName());
                applyDefaultColorTo(c, row);
            } else if (value instanceof Room) {
                ((JLabel) c).setText(((Room) value).getRoomName());
                applyDefaultColorTo(c, row);
            }
        }
    }

    private boolean hasFacultyConflict(JTable table, int row) {
        boolean isConflict;
        ScheduleDaoImpl scheduleDaoImpl = new ScheduleDaoImpl();
        SchoolYear schoolYear = new SchoolYearDaoImpl().getCurrentSchoolYear();
        Schedule schedule = new Schedule();
        schedule.setDay(getDayEquivalent(table.getValueAt(row, 0).toString().trim()));
        schedule.setStartTime(Integer.parseInt(table.getValueAt(row, 1).toString().trim().replace(":", "")));
        schedule.setEndTime(Integer.parseInt(table.getValueAt(row, 2).toString().trim().replace(":", "")));
        schedule.setFaculty((Faculty) table.getValueAt(row, 4));
        schedule.setSchoolYear(schoolYear);
        isConflict = scheduleDaoImpl.facultyhasScheduleAt(schedule);
        return isConflict;
    }

    private String getDayEquivalent(String longDay) {
        String oneCharDay = longDay.trim().equalsIgnoreCase("Mon") ? "M"
                : longDay.trim().equalsIgnoreCase("Tue") ? "T"
                : longDay.trim().equalsIgnoreCase("Wed") ? "W"
                : longDay.trim().equalsIgnoreCase("Thu") ? "TH" : "F";
        return oneCharDay;
    }

    private boolean startTimeEqEndtime(Object startTime, Object endTime) {
        return Integer.parseInt(startTime.toString().trim().replace(":", "")) == Integer.parseInt(endTime.toString().trim().replace(":", ""));
    }

    private boolean startTimeGtEndtime(Object startTime, Object endTime) {
        return Integer.parseInt(startTime.toString().trim().replace(":", "")) > Integer.parseInt(endTime.toString().trim().replace(":", ""));
    }

    private boolean endTimeLtStartTime(Object startTime, Object endTime) {
        return Integer.parseInt(endTime.toString().trim().replace(":", "")) < Integer.parseInt(startTime.toString().trim().replace(":", ""));
    }

    private void applyWarningColorTo(Component c) {
        c.setBackground(Color.RED);
        ((JLabel) c).setForeground(Color.WHITE);
    }

    private void applyDefaultColorTo(Component c, int row) {
        if (row % 2 == 0) {
            c.setBackground(Color.WHITE);
            ((JLabel) c).setForeground(Color.BLACK);
        } else {
            c.setBackground(Color.WHITE);
            ((JLabel) c).setForeground(Color.BLACK);
        }
    }

    private boolean startTimeMatchesSession(int startTime, String session) {
        boolean valid;
        if (session.trim().equalsIgnoreCase("PM")) {
            valid = ((startTime >= 1200) && (startTime <= 1600));
        } else if (session.trim().equalsIgnoreCase("AM")) {
            valid = ((startTime >= 700) && (startTime <= 1100));
        } else {//WD
            valid = ((startTime >= 700) && (startTime <= 1600));
        }
        return valid;
    }

    private boolean endTimeMatchesSession(int et, String s) {
        boolean valid;
        if (s.trim().equalsIgnoreCase("PM")) {
            valid = ((et >= 1300) && (et <= 1700));
        } else if (s.trim().equalsIgnoreCase("AM")) {
            valid = ((et >= 800) && (et <= 1200));
        } else {//WD
            valid = ((et >= 800) && (et <= 1700));
        }
        return valid;
    }

    private boolean hasDuplicateAt(int col, Object value, JTable table) {
        int countOfRecord = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            if (value.equals(table.getValueAt(row, col))) {
                countOfRecord++;
            }
        }
        return countOfRecord > 1;
    }

    private boolean hasDuplicateStartTimeOn(String day, int startTimeCol, Object startTime, TableModel tableModel) {
        int countOfRecord = 0;
        int dayCol = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, dayCol) != null) {
                String d = tableModel.getValueAt(row, dayCol).toString().trim();
                if (day.equalsIgnoreCase(d)) {
                    if (tableModel.getValueAt(row, startTimeCol) != null) {
                        countOfRecord++;
                    }
                }
            }
        }
        return countOfRecord > 1;
    }

    private boolean hasDuplicate(Object startTime, Object endTime, Object faculty, JTable table) {
        int countOfRecord = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            Object sTime = table.getValueAt(row, 1);
            Object eTime = table.getValueAt(row, 2);
            Object f = table.getValueAt(row, 4);
            if (startTime.equals(sTime) && endTime.equals(eTime) && faculty.equals(f)) {
                countOfRecord++;
            }
        }
        return countOfRecord > 1;
    }
    
}
