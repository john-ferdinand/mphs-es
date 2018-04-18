
package model.subject;

import java.util.Date;
import model.grade.Grade;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;

public class Subject {
    private int subjectId;
    private Grade firstGradingPeriodAverage;
    private Grade secondGradingPeriodAverage;
    private Grade thirdGradingPeriodAverage;
    private Grade fourthGradingPeriodAverage;
    private String subjectTitle;
    private String subjectCode;
    private String subjectDescription;
    private String addedBy;
    private int subjectMinutes;
    private Date dateAdded;
    private Date dateCreated;
    private SchoolYear schoolYearCreated;
    private boolean isActive;
    private GradeLevel gradeLevel;

    public Grade getFirstGradingPeriodAverage() {
        return firstGradingPeriodAverage;
    }

    public void setFirstGradingPeriodAverage(Grade firstGradingPeriodAverage) {
        this.firstGradingPeriodAverage = firstGradingPeriodAverage;
    }

    public Grade getSecondGradingPeriodAverage() {
        return secondGradingPeriodAverage;
    }

    public void setSecondGradingPeriodAverage(Grade secondGradingPeriodAverage) {
        this.secondGradingPeriodAverage = secondGradingPeriodAverage;
    }

    public Grade getThirdGradingPeriodAverage() {
        return thirdGradingPeriodAverage;
    }

    public void setThirdGradingPeriodAverage(Grade thirdGradingPeriodAverage) {
        this.thirdGradingPeriodAverage = thirdGradingPeriodAverage;
    }

    public Grade getFourthGradingPeriodAverage() {
        return fourthGradingPeriodAverage;
    }

    public void setFourthGradingPeriodAverage(Grade fourthGradingPeriodAverage) {
        this.fourthGradingPeriodAverage = fourthGradingPeriodAverage;
    }
    
    

    public SchoolYear getSchoolYearCreated() {
        return schoolYearCreated;
    }

    public void setSchoolYearCreated(SchoolYear schoolYearCreated) {
        this.schoolYearCreated = schoolYearCreated;
    }
    
    public boolean isIsActive() {
        return isActive;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void setSubjectMinutes(int subjectMinutes)
    {
        this.subjectMinutes = subjectMinutes;
    }
    
    public int getSubjectMinutes()
    {
        return subjectMinutes;
    }
    
}
