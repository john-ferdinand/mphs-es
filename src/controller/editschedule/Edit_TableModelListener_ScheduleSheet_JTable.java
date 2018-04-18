package controller.editschedule;

import daoimpl.ScheduleDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author Jordan
 */
public class Edit_TableModelListener_ScheduleSheet_JTable implements TableModelListener {

    private final Dialog_EditSchedule view;
    private final SubjectDaoImpl subjectDaoImpl;

    public Edit_TableModelListener_ScheduleSheet_JTable(Dialog_EditSchedule view) {
        this.view = view;
        this.subjectDaoImpl = new SubjectDaoImpl();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        TableModel tableModel = (TableModel) e.getSource();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            SchoolYear schoolYear = new SchoolYearDaoImpl().getCurrentSchoolYear();
            Object day = tableModel.getValueAt(row, 0);
            Object startTime = tableModel.getValueAt(row, 1);
            Object endTime = tableModel.getValueAt(row, 2);
            Object subject = tableModel.getValueAt(row, 3);
            Faculty faculty = (Faculty) tableModel.getValueAt(row, 4);
            Object session = tableModel.getValueAt(row, 6);
            setSubjectMinsSummary(tableModel);
            if (day != null && startTime != null && endTime != null && faculty != null && session != null) {
                int sTime = Integer.parseInt(startTime.toString().trim().replace(":", ""));
                int eTime = Integer.parseInt(endTime.toString().trim().replace(":", ""));
                if (hasDuplicateStartTimeOn(day.toString(), 1, startTime, tableModel)) {
                    view.getJtaWarnings().setText("Duplicate time found for " + startTime);
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } else if (hasDuplicateEndTimeOn(day.toString(), 2, endTime, tableModel)) {
                    view.getJtaWarnings().setText("Duplicate time found for " + startTime);
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } else if (startTimeEqEndtime(startTime, endTime)) {
                    view.getJtaWarnings().setText("Start time must not be equal to End time\n");
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } else if (startTimeGtEndtime(startTime, endTime)) {
                    view.getJtaWarnings().setText("Start time must be less than End time\n");
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } else if (endTimeLtStartTime(startTime, endTime)) {
                    view.getJtaWarnings().setText("End time must be greater than Start time.\n");
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } 
//                else if (facultyHasScheduleAt(day.toString(), sTime, eTime, faculty, schoolYear)) {
//                    displayFacultyConflictMessage(day.toString(), startTime, endTime, faculty);
//                    view.getJbtnUpdate().setEnabled(false);
//                    break;
//                } 
                else if (hasDuplicateSubjectOn(day, 3, subject, tableModel)) {
                    view.getJtaWarnings().setText("More than one occurence of a subject found on the same day.");
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                } else if (hasExceededSubjectMinutes(subject, tableModel)) {
                    SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
                    Subject s = (Subject) subject;
                    int allowedMinutes = subjectDaoImpl.getSubjectMinutesPerWeekOf(s);
                    view.getJtaWarnings().setText(
                            "" + ((Subject) subject).getSubjectCode() 
                               + " does not match or has exceeded its allowed minutes per week.\nExpected: "+allowedMinutes+" mins/week\n"
                            + "add/remove rows for "+((Subject) subject).getSubjectCode() + " to match "+allowedMinutes+" mins/week."
                    );
                    view.getJbtnUpdate().setEnabled(false);
                    break;
                }else if(!hasCompleteSubjects(tableModel)){
                    view.getJtaWarnings().setText("Incomplete subjects.");
                }else if(!adviserExists(tableModel)){
                    view.getJtaWarnings().setText("Adviser must teach at least 1 subject.");
                } 
                
                else {
                    view.getJtaWarnings().setText("Schedule ready.");
                    view.getJbtnUpdate().setEnabled(true);
                }
            }
        }
    }
    
    private void setSubjectMinsSummary(TableModel tModel) {
        DefaultTableModel subjMinsSummary = (DefaultTableModel) view.getJtblSubjectHrsSummary().getModel();
        for (int r = 0; r < subjMinsSummary.getRowCount(); r++) {
            String subjectCode = subjMinsSummary.getValueAt(r, 0).toString().trim();
            int totalminutes = 0;
            for (int row = 0; row < tModel.getRowCount(); row++) {
                Object startTime = tModel.getValueAt(row, 1);
                Object endTime = tModel.getValueAt(row, 2);
                Object sCode = tModel.getValueAt(row, 3);
                if (startTime != null && endTime != null && sCode != null) {
                    int sT = Integer.parseInt(tModel.getValueAt(row, 1).toString().trim().replace(":", ""));
                    int eT = Integer.parseInt(tModel.getValueAt(row, 2).toString().trim().replace(":", ""));
                    String code = ((Subject) tModel.getValueAt(row, 3)).getSubjectCode().trim();
                    int duration = durationOf(toMinute(sT), toMinute(eT));
                    if (subjectCode.equalsIgnoreCase(code)) {
                        totalminutes += duration;
                    }
                }
            }
            subjMinsSummary.setValueAt(totalminutes, r, 1);
        }
    }
    
    private boolean adviserExists(TableModel tableModel){
        int rowCount = 0;
        SectionDaoImpl sectionDaoImpl = new SectionDaoImpl();
        Section s = (Section) view.getJcmbSection().getSelectedItem();
        Section section = sectionDaoImpl.getSectionById(s.getSectionId());
        if (section != null) {
            Faculty adviser = (Faculty) section.getAdviser();
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (tableModel.getValueAt(row, 4) != null) {
                    Faculty a = (Faculty) tableModel.getValueAt(row, 4);
                    System.out.println("a.getFacultyId: " + a.getFacultyID());
                    System.out.println("adviser.getFacultyId: " + adviser.getFacultyID());
                    System.out.println();
                    if(a.getFacultyID() == adviser.getFacultyID()){
                        rowCount++;
                    }
                }
            }
        }
        return rowCount > 0;
    }
    
    private boolean hasCompleteSubjects(TableModel tableModel){
        boolean exists = true;
        int gradeLevelId  = ((GradeLevel) view.getJcmbGradeLevel().getSelectedItem()).getGradeLevelId();
        List<Subject> subjects = subjectDaoImpl.getAllSubjectsByGradeLevelId(gradeLevelId);
        ArrayList<String> sbjcts = new ArrayList<>();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 3) != null) {
                sbjcts.add(((Subject) tableModel.getValueAt(row, 3)).getSubjectCode());
            }
        }
        for (Subject s : subjects) {
            exists = exists && sbjcts.contains(s.getSubjectCode());
        }
        return exists;
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
                System.out.println("toMinute(sT) : "+toMinute(sT));
                System.out.println("toMinute(eT) : "+toMinute(eT));
                System.out.println("Duration: " + duration);
                Subject sbj = (Subject) tableModel.getValueAt(row, 3);
                if (s == sbj) {
                    totalMinutes += duration;
                }
            }
        }
        System.out.println("Total Subject Minutes : "+totalMinutes);
        System.out.println("Allowed Minutes Per Week: "+minutesPerWeek);
        return totalMinutes != minutesPerWeek;
    }
    
    private int durationOf(int startTime, int endTime){
        return endTime - startTime;
    }
    
    private int toMinute(int time) {
        
        int hr = (time / 100) ;
        int min = (time % 100);
        System.out.println("Time HR: "+hr);
        System.out.println("Time MIN: "+min);
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
    
    private boolean startTimeEqEndtime(Object startTime, Object endTime) {
        return Integer.parseInt(startTime.toString().trim().replace(":", "")) == Integer.parseInt(endTime.toString().trim().replace(":", ""));
    }

    private boolean startTimeGtEndtime(Object startTime, Object endTime) {
        return Integer.parseInt(startTime.toString().trim().replace(":", "")) > Integer.parseInt(endTime.toString().trim().replace(":", ""));
    }

    private boolean endTimeLtStartTime(Object startTime, Object endTime) {
        return Integer.parseInt(endTime.toString().trim().replace(":", "")) < Integer.parseInt(startTime.toString().trim().replace(":", ""));
    }

    private void displayFacultyConflictMessage(String day, Object startTime, Object endTime, Faculty faculty) {
        view.getJtaWarnings().setText(
                "*** Faculty avialability conflict found! *** \n"
                + ((Faculty) faculty).getFirstName() + ((Faculty) faculty).getLastName() + "\n"
                + "has an existing schedule at (" + startTime + "-" + endTime + " , " + day + ")\n"
                + "Please select a different faculty member OR change the other schedule."
        );
    }
    
    private boolean facultyHasScheduleAt(String day, int startTime, int endTime, Faculty faculty, SchoolYear schoolYear) {
        boolean hasSchedule = false;
        ScheduleDaoImpl scheduleDaoImpl = new ScheduleDaoImpl();
        Schedule schedule = new Schedule();
        schedule.setDay(getDayEquivalent(day));
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setFaculty(faculty);
        schedule.setSchoolYear(schoolYear);
        hasSchedule = scheduleDaoImpl.facultyhasScheduleAt(schedule);
        return hasSchedule;
    }

    private String getDayEquivalent(String longDay) {
        String oneCharDay = longDay.trim().equalsIgnoreCase("Mon") ? "M"
                : longDay.trim().equalsIgnoreCase("Tue") ? "T"
                : longDay.trim().equalsIgnoreCase("Wed") ? "W"
                : longDay.trim().equalsIgnoreCase("Thu") ? "TH" : "F";
        return oneCharDay;
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
    
    private boolean hasDuplicateAt(int col, Object value, TableModel tableModel) {
        int countOfRecord = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (value.equals(tableModel.getValueAt(row, col))) {
                countOfRecord++;
            }
        }
        return countOfRecord > 1;
    }

}
