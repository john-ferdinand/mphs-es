package model.schedule;

import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.room.Room;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subject.Subject;

public class Schedule {

    private int scheduleID;
    private String scheduleSession;
    private SchoolYear schoolYear;
    private GradeLevel gradeLevel;
    private Section section;
    private Room room;
    private Faculty faculty;
    private Subject subject;
    private String day;
    private int startTime;
    private int endTime;
    private boolean isActive;

    public String getScheduleSession() {
        return scheduleSession;
    }

    public void setScheduleSession(String scheduleSession) {
        this.scheduleSession = scheduleSession;
    }

    
    
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    
    
    
}
