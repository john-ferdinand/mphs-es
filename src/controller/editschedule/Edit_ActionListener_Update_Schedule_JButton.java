package controller.editschedule;

import daoimpl.ScheduleDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.room.Room;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;
import utility.form.FormValidator;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author Jordan
 */
public class Edit_ActionListener_Update_Schedule_JButton implements ActionListener, FormValidator {

    private final Dialog_EditSchedule view;
    private final SchoolYear schoolYear;
    private final ScheduleDaoImpl scheduleDaoImpl;

    public Edit_ActionListener_Update_Schedule_JButton(Dialog_EditSchedule view) {
        schoolYear = view.getSchoolYear();
        scheduleDaoImpl = new ScheduleDaoImpl();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (formIsValid()) {
            if (JOptionPane.showConfirmDialog(null, "Update Schedule", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (updateSchedule()) {
                    Section section = (Section) view.getJcmbSection().getSelectedItem();
                    JOptionPane.showMessageDialog(null, "Successfully update schedule for " + section.getSectionName());
                    view.dispose();
                } else {
//                    JOptionPane.showMessageDialog(null, "Encountered problems while creating schedule. Please contact your support.");
                }
            }
        }
    }

    private boolean updateSchedule() {
        List<Schedule> scheduleList = getScheduleFromScheduleSummaryTable();
        Section section = (Section) view.getJcmbSection().getSelectedItem();
        boolean isSuccessful = scheduleDaoImpl.update(scheduleList,section,schoolYear);
        return isSuccessful;
    }

    private List<Schedule> getScheduleFromScheduleSummaryTable() {
        List<Schedule> scheduleList = new ArrayList<>();
        Section section = (Section) view.getJcmbSection().getSelectedItem();
        GradeLevel gradeLevel = (GradeLevel) view.getJcmbGradeLevel().getSelectedItem();
        Room room = (Room) view.getJcmbRoom().getSelectedItem();
        for (int row = 0; row < view.getJtblSchedule().getRowCount(); row++) {
            Schedule schedule = new Schedule();
            schedule.setSection(section);
            schedule.setGradeLevel(gradeLevel);
            schedule.setSchoolYear(schoolYear);
            schedule.setDay(dayValueForDB(view.getJtblSchedule().getValueAt(row, 0).toString()));
            schedule.setStartTime(Integer.parseInt(view.getJtblSchedule().getValueAt(row, 1).toString().trim().replace(":", "").trim()));
            schedule.setEndTime(Integer.parseInt(view.getJtblSchedule().getValueAt(row, 2).toString().trim().replace(":", "").trim()));
            schedule.setSubject((Subject) view.getJtblSchedule().getValueAt(row, 3));
            schedule.setFaculty((Faculty) view.getJtblSchedule().getValueAt(row, 4));
            schedule.setRoom(room);
            schedule.setScheduleSession(view.getJtblSchedule().getValueAt(row, 6).toString().trim());
            scheduleList.add(schedule);
        }
        return scheduleList;
    }

    private String dayValueForDB(Object day) {
        if (day.toString().trim().equalsIgnoreCase("Mon")) {
            return "M";
        } else if (day.toString().trim().equalsIgnoreCase("Tue")) {
            return "T";
        } else if (day.toString().trim().equalsIgnoreCase("Wed")) {
            return "W";
        } else if (day.toString().trim().equalsIgnoreCase("Thu")) {
            return "TH";
        } else {
            return "F";
        }
    }

    @Override
    public boolean formIsValid() {
        return true;
    }

}
