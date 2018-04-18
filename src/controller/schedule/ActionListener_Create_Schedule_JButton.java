package controller.schedule;

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
import utility.string.StringUtil;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author Jordan
 */
public class ActionListener_Create_Schedule_JButton implements ActionListener, FormValidator {

    private final Dialog_CreateSchedule view;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final ScheduleDaoImpl scheduleDaoImpl;

    public ActionListener_Create_Schedule_JButton(Dialog_CreateSchedule view) {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        scheduleDaoImpl = new ScheduleDaoImpl();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (formIsValid()) {
            if (JOptionPane.showConfirmDialog(null, "Create Schedule", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (createSchedule()) {
                    Section section = (Section) view.getJcmbSection().getSelectedItem();
                    JOptionPane.showMessageDialog(null, "Successfully created schedule for " + section.getSectionName());
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Encountered problems while creating schedule. Please contact your support.");
                }
            }
        }
    }

    private boolean createSchedule() {
        List<Schedule> scheduleList = getScheduleFromScheduleSummaryTable();
        boolean isSuccessful = scheduleDaoImpl.add(scheduleList);
        return isSuccessful;
    }

    private List<Schedule> getScheduleFromScheduleSummaryTable() {
        List<Schedule> scheduleList = new ArrayList<>();
        Section section = (Section) view.getJcmbSection().getSelectedItem();
        GradeLevel gradeLevel = (GradeLevel) view.getJcmbGradeLevel().getSelectedItem();
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
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
