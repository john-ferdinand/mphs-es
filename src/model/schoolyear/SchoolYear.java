package model.schoolyear;

import java.util.Date;
import java.util.List;
import model.quarter.Quarter;

/**
 *
 * @author Jordan
 */
public class SchoolYear {
    private int schoolYearId;
    private Integer yearFrom;
    private Integer yearTo;
    private boolean isCurrentSchoolYear;
    
    private Date schoolYearStartDate;
    private Date schoolYearEndDate;
    private Date regularEnrollmentStartDate;
    private Date regularEnrollmentEndDate;
    private Date summerEnrollmentStartDate;
    private Date summerEnrollmentEndDate;
    private Date summerClassStartDate;
    private Date summerClassEnd;
    private boolean isActive;
    private List<Quarter> quarters;
    private int totalSchoolDays;

    public int getTotalSchoolDays() {
        return totalSchoolDays;
    }

    public void setTotalSchoolDays(int totalSchoolDays) {
        this.totalSchoolDays = totalSchoolDays;
    }
    
    

    public Date getSummerClassStartDate() {
        return summerClassStartDate;
    }

    public void setSummerClassStartDate(Date summerClassStartDate) {
        this.summerClassStartDate = summerClassStartDate;
    }

    public Date getSummerClassEndDate() {
        return summerClassEnd;
    }

    public void setSummerClassEndDate(Date summerClassEnd) {
        this.summerClassEnd = summerClassEnd;
    }

    public Date getRegularEnrollmentStartDate() {
        return regularEnrollmentStartDate;
    }

    public void setRegularEnrollmentStartDate(Date regularEnrollmentStartDate) {
        this.regularEnrollmentStartDate = regularEnrollmentStartDate;
    }

    public Date getRegularEnrollmentEndDate() {
        return regularEnrollmentEndDate;
    }

    public void setRegularEnrollmentEndDate(Date regularEnrollmentEndDate) {
        this.regularEnrollmentEndDate = regularEnrollmentEndDate;
    }

    public Date getSummerEnrollmentStartDate() {
        return summerEnrollmentStartDate;
    }

    public void setSummerEnrollmentStartDate(Date summerEnrollmentStartDate) {
        this.summerEnrollmentStartDate = summerEnrollmentStartDate;
    }

    public Date getSummerEnrollmentEndDate() {
        return summerEnrollmentEndDate;
    }

    public void setSummerEnrollmentEndDate(Date summerEnrollmentEndDate) {
        this.summerEnrollmentEndDate = summerEnrollmentEndDate;
    }
    
    public List<Quarter> getQuarters() {
        return quarters;
    }

    public void setQuarters(List<Quarter> quarters) {
        this.quarters = quarters;
    }
    
    public boolean isCurrentSchoolYear() {
        return isCurrentSchoolYear;
    }

    public void setIsCurrentSchoolYear(boolean isCurrentSchoolYear) {
        this.isCurrentSchoolYear = isCurrentSchoolYear;
    }
    
    public Date getSchoolYearStartDate() {
        return schoolYearStartDate;
    }

    public void setSchoolYearStartDate(Date schoolYearStartDate) {
        this.schoolYearStartDate = schoolYearStartDate;
    }

    public Date getSchoolYearEndDate() {
        return schoolYearEndDate;
    }

    public void setSchoolYearEndDate(Date schoolYearEndDate) {
        this.schoolYearEndDate = schoolYearEndDate;
    }
    
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(int schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }
}
