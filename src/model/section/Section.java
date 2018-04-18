package model.section;

import java.util.List;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;

public class Section {

    private int sectionId;
    private String sectionName;
    private String sectionSession;
    private String sectionType;
    private SchoolYear schoolYear;
    private GradeLevel gradeLevel;
    private Faculty adviser;
    private boolean isActive;
    private String dateCreated;

    private int sectionCategoryId;
    private List<Student> students;
    private String requiredAverage;
    private String category;
    private int capacity;

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public Faculty getAdviser() {
        return adviser;
    }

    public void setAdviser(Faculty adviser) {
        this.adviser = adviser;
    }

    public String getSectionSession() {
        return sectionSession;
    }

    public void setSectionSession(String sectionSession) {
        this.sectionSession = sectionSession;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setRequiredAverage(String requiredAverage) {
        this.requiredAverage = requiredAverage;
    }

    public String getRequiredAverage() {
        return requiredAverage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSectionCategoryId(int sectionCategoryId) {
        this.sectionCategoryId = sectionCategoryId;
    }

    public int getSectionCategoryId() {
        return sectionCategoryId;
    }
}
