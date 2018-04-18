
package model.quarter;

import model.schoolyear.SchoolYear;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class Quarter {
    
    private String description;
    private SchoolYear schoolYear;
    
    private int quarterId;
    private int quarterNo;
    private Date startDate;
    private Date endDate;
    private Date gradingOpenDate;
    private Date gradingDueDate;
    private boolean isActive;
    private boolean isCurrentQuarter;
    private Date dateAdded;

    public Date getGradingOpenDate() {
        return gradingOpenDate;
    }

    public void setGradingOpenDate(Date gradingOpenDate) {
        this.gradingOpenDate = gradingOpenDate;
    }
    
    public Date getGradingDueDate() {
        return gradingDueDate;
    }

    public void setGradingDueDate(Date gradingDueDate) {
        this.gradingDueDate = gradingDueDate;
    }

    public boolean getIsCurrentQuarter() {
        return isCurrentQuarter;
    }

    public void setIsCurrentQuarter(boolean isCurrentQuarter) {
        this.isCurrentQuarter = isCurrentQuarter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(int quarterId) {
        this.quarterId = quarterId;
    }

    public int getQuarterNo() {
        return quarterNo;
    }

    public void setQuarterNo(int quarterNo) {
        this.quarterNo = quarterNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
}
