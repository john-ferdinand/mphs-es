package daoimpl;

import dao.ISchedule;
import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.room.Room;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;

public class ScheduleDaoImpl implements ISchedule {

    SubjectDaoImpl subjectDaoImpl;
    RoomDaoImpl roomDaoImpl;
    SectionDaoImpl sectionDaoImpl;

    public ScheduleDaoImpl() {
        subjectDaoImpl = new SubjectDaoImpl();
        roomDaoImpl = new RoomDaoImpl();
        sectionDaoImpl = new SectionDaoImpl();
    }

    @Override
    public boolean update(List<Schedule> schedule, Section section, SchoolYear schoolYear) {
        String SQLa = "{CALL deleteScheduleBySectionIdAndSchoolYearId(?,?)}";
        String SQLb = "{CALL addSchedule(?,?,?,?,?,?,?,?,?,?,?)}";
        boolean isUpdated = false;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement cs_deleteSchedule = con.prepareCall(SQLa);
                    CallableStatement cs_addSchedule = con.prepareCall(SQLb);) {
                cs_deleteSchedule.setInt(1, section.getSectionId());
                cs_deleteSchedule.setInt(2, schoolYear.getSchoolYearId());
                cs_deleteSchedule.executeUpdate();
                for (Schedule sched : schedule) {
                    cs_addSchedule.setString(1, sched.getDay());
                    cs_addSchedule.setInt(2, sched.getStartTime());
                    cs_addSchedule.setInt(3, sched.getEndTime());
                    cs_addSchedule.setInt(4, sched.getSchoolYear().getSchoolYearId());
                    cs_addSchedule.setInt(5, sched.getSubject().getSubjectId());
                    cs_addSchedule.setInt(6, sched.getSection().getSectionId());
                    cs_addSchedule.setInt(7, sched.getRoom().getRoomID());
                    cs_addSchedule.setInt(8, sched.getGradeLevel().getGradeLevelId());
                    cs_addSchedule.setInt(9, sched.getFaculty().getFacultyID());
                    cs_addSchedule.setString(10, sched.getSection().getSectionSession());
                    cs_addSchedule.registerOutParameter(11, Types.INTEGER);
                    cs_addSchedule.executeUpdate();
                    int schedule_id = cs_addSchedule.getInt(11);
                }
                con.commit();
                isUpdated = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    
    @Override
    public boolean add(List<Schedule> scheduleList) {
        boolean isAdded = false;
        String SQL = "{CALL addSchedule(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQL);) {
                for (Schedule schedule : scheduleList) {
                    csa.setString(1, schedule.getDay());
                    csa.setInt(2, schedule.getStartTime());
                    csa.setInt(3, schedule.getEndTime());
                    csa.setInt(4, schedule.getSchoolYear().getSchoolYearId());
                    csa.setInt(5, schedule.getSubject().getSubjectId());
                    csa.setInt(6, schedule.getSection().getSectionId());
                    csa.setInt(7, schedule.getRoom().getRoomID());
                    csa.setInt(8, schedule.getGradeLevel().getGradeLevelId());
                    csa.setInt(9, schedule.getFaculty().getFacultyID());
                    csa.setString(10, schedule.getSection().getSectionSession());
                    csa.registerOutParameter(11, Types.INTEGER);
                    csa.executeUpdate();
                    int schedule_id = csa.getInt(11);
                }
                con.commit();
                isAdded = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public List<Schedule> getAllSchedulesBySchoolYearFacultyAndStatus(int schoolYearId, int facultyId, boolean isSchedActive) {
        List<Schedule> scheduleList = new ArrayList<>();
        String SQL = "{CALL getAllSchedulesBySchoolYearFacultyAndStatus(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYearId);
            cs.setInt(2, facultyId);
            cs.setInt(3, isSchedActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));

                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));

                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));

                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));

                    Schedule schedule = new Schedule();
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setScheduleSession(rs.getString("schedule_session"));
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setScheduleID(rs.getInt("schedule_id"));
                    schedule.setIsActive(rs.getBoolean("schedule_status"));
                    schedule.setSchoolYear(schoolYear);
                    schedule.setSubject(subject);
                    schedule.setSection(section);
                    schedule.setRoom(room);
                    schedule.setGradeLevel(gradeLevel);
                    schedule.setFaculty(faculty);

                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> getSchedulesBy(Section section, SchoolYear schoolYear) {
        List<Schedule> scheduleList = new ArrayList<>();
        String SQL = "{CALL getSchedulesBySectionIdAndSyId(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, section.getSectionId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));

                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));

                    Section s = new Section();
                    s.setSectionId(rs.getInt("section_id"));
                    s.setSectionName(rs.getString("sectionName"));

                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));

                    Schedule schedule = new Schedule();
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setScheduleSession(rs.getString("schedule_session"));
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setScheduleID(rs.getInt("schedule_id"));
                    schedule.setIsActive(rs.getBoolean("schedule_status"));
                    schedule.setSchoolYear(schoolYear);
                    schedule.setSubject(subject);
                    schedule.setSection(s);
                    schedule.setRoom(room);
                    schedule.setGradeLevel(gradeLevel);
                    schedule.setFaculty(faculty);

                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
    
    
    @Override
    public List<Schedule> getSchedulesBy(SchoolYear schoolYear, GradeLevel gradeLevel, boolean isActive) {
        List<Schedule> scheduleList = new ArrayList<>();
        String SQL = "{CALL getSchedulesBySyIdGradeLevelIdAndStatus(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            cs.setInt(2, gradeLevel.getGradeLevelId());
            cs.setInt(3, isActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));

                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));

                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));

                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));

                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));

                    Schedule schedule = new Schedule();
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setScheduleSession(rs.getString("schedule_session"));
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setScheduleID(rs.getInt("schedule_id"));
                    schedule.setIsActive(rs.getBoolean("schedule_status"));
                    schedule.setSchoolYear(schoolYear);
                    schedule.setSubject(subject);
                    schedule.setSection(section);
                    schedule.setRoom(room);
                    schedule.setGradeLevel(gradeLevel);
                    schedule.setFaculty(faculty);

                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
    

    @Override
    public List<Schedule> getSchedulesByWildCardSchoolYearIdAndStatus(String aWildCardChar, int schoolYearId, boolean isActive) {
        String SQL = "{CALL getSchedulesByWildCardSchoolYearIdAndStatus(?,?,?)}";
        List<Schedule> scheduleList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, aWildCardChar);
            cs.setInt(2, schoolYearId);
            cs.setInt(3, isActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));

                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));

                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));

                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));

                    Schedule schedule = new Schedule();
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setScheduleSession(rs.getString("schedule_session"));
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setScheduleID(rs.getInt("schedule_id"));
                    schedule.setIsActive(rs.getBoolean("schedule_status"));
                    schedule.setSchoolYear(schoolYear);
                    schedule.setSubject(subject);
                    schedule.setSection(section);
                    schedule.setRoom(room);
                    schedule.setGradeLevel(gradeLevel);
                    schedule.setFaculty(faculty);

                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> getSchedulesByDaySchoolYearAndStatus(String day, int schoolyearId, boolean isActive) {
        String SQL = "{CALL getSchedulesByDaySchoolYearAndStatus(?,?,?)}";
        List<Schedule> scheduleList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, day);
            cs.setInt(2, schoolyearId);
            cs.setInt(3, isActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));

                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));

                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));

                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));

                    Schedule schedule = new Schedule();
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setScheduleSession(rs.getString("schedule_session"));
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setScheduleID(rs.getInt("schedule_id"));
                    schedule.setIsActive(rs.getBoolean("schedule_status"));
                    schedule.setSchoolYear(schoolYear);
                    schedule.setSubject(subject);
                    schedule.setSection(section);
                    schedule.setRoom(room);
                    schedule.setGradeLevel(gradeLevel);
                    schedule.setFaculty(faculty);

                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    @Override
    public boolean facultyhasScheduleAt(Schedule schedule) {
        boolean hasSchedule = false;
        String SQL = "{CALL facultyHasScheduleAt(?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,schedule.getStartTime());
            cs.setInt(2,schedule.getEndTime());
            cs.setString(3, schedule.getDay());
            cs.setInt(4, schedule.getSchoolYear().getSchoolYearId());
            cs.setInt(5, schedule.getFaculty().getFacultyID());
            try(ResultSet rs = cs.executeQuery()){
                while(rs.next()){
                    hasSchedule = rs.getBoolean("hasSchedule");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return hasSchedule;
    }

    @Override
    public Faculty getScheduleFacultyOf(Subject subject, SchoolYear schoolYear) {
        Faculty faculty = new Faculty();
        String SQL = "{CALL getScheduleFacultyOf(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, subject.getSubjectId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public List<Schedule> getScheduleDayTimeRoomOf(Subject subject, SchoolYear schoolYear) {
        List<Schedule> scheduleList = new ArrayList<>();
        String SQL = "{CALL getScheduleDayTimeRoomOf(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,subject.getSubjectId());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setDescription(rs.getString("notes"));
                    
                    Schedule schedule = new Schedule();
                    schedule.setDay(rs.getString("schedule_day"));
                    schedule.setStartTime(rs.getInt("startTime"));
                    schedule.setEndTime(rs.getInt("endTime"));
                    schedule.setRoom(room);
                    
                    scheduleList.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
 
    
    
}
