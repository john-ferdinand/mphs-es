package model.grade;

import java.util.Date;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.user.User;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Grade {

    private int gradeId;
    private int studentId;
    private int subjectId;
    private int gradeValue;
    private int gradingPeriod;
    private GradeLevel studentGradeLevel;
    private String gradeType; //summer or regular
    private SchoolYear schoolYear;
    private Date dateSubmitted;
    private User addedBy;
    private boolean isPass;
    private boolean isFail;

    public GradeLevel getStudentGradeLevel() {
        return studentGradeLevel;
    }

    public void setStudentGradeLevel(GradeLevel studentGradeLevel) {
        this.studentGradeLevel = studentGradeLevel;
    }
    
    public boolean isIsPass() {
        return isPass;
    }

    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }

    public boolean isIsFail() {
        return isFail;
    }

    public void setIsFail(boolean isFail) {
        this.isFail = isFail;
    }

    public boolean isIsPassing() {
        return isPass;
    }

    public void setIsPassing(boolean isPassing) {
        this.isPass = isPassing;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public int getValue() {
        return gradeValue;
    }

    public void setValue(int value) {
        this.gradeValue = value;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGradingPeriod() {
        return gradingPeriod;
    }

    public void setGradingPeriod(int gradingPeriod) {
        this.gradingPeriod = gradingPeriod;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

}
