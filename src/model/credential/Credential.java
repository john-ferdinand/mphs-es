
package model.credential;

import model.schoolyear.SchoolYear;
import java.util.Date;
import java.util.List;
import model.gradelevel.GradeLevel;
import model.user.User;


public class Credential {
    private Integer credentialId;
    private String credentialName;
    private Date dateAdded;
    private Date dateReceived;
    private List<GradeLevel> gradeLevelsAssigned;
    private SchoolYear yearCreated;
    private String credentialDescription;
    private User createdBy;
    private boolean isActive;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public SchoolYear getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(SchoolYear yearCreated) {
        this.yearCreated = yearCreated;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getCredentialDescription() {
        return credentialDescription;
    }

    public void setCredentialDescription(String credentialDescription) {
        this.credentialDescription = credentialDescription;
    }
    
    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public List<GradeLevel> getGradeLevelsAssigned() {
        return gradeLevelsAssigned;
    }

    public void setGradeLevelsAssigned(List<GradeLevel> gradeLevelsAssigned) {
        this.gradeLevelsAssigned = gradeLevelsAssigned;
    }
}
