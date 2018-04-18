
package dao;

import java.util.List;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface ISchedule {
    
    boolean add(List<Schedule> schedule);
    boolean update(List<Schedule> schedule, Section section, SchoolYear schoolYear);
    boolean facultyhasScheduleAt(Schedule schedule);
    Faculty getScheduleFacultyOf(Subject subject, SchoolYear schoolYear);
    List<Schedule> getScheduleDayTimeRoomOf(Subject subject, SchoolYear schoolYear);
    List<Schedule> getAllSchedulesBySchoolYearFacultyAndStatus(int schoolYearId, int facultyId, boolean isSchedActive);
    List<Schedule> getSchedulesByWildCardSchoolYearIdAndStatus(String aWildCardChar, int schoolYearId, boolean isActive);
    List<Schedule> getSchedulesByDaySchoolYearAndStatus(String day, int schoolyearId, boolean isActive);
    List<Schedule> getSchedulesBy(SchoolYear schoolYear, GradeLevel gradeLevel, boolean isActive);
    List<Schedule> getSchedulesBy(Section section, SchoolYear schoolYear);
}
