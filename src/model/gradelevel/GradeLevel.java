
package model.gradelevel;

import model.credential.Credential;
import java.util.List;

/**
 *
 * @author John Ferdinand Antonio
 */
public class GradeLevel {
    private int id;
    private Integer level;
    private boolean isActive;
    private List<Credential> credentialRequirements;
    private int ageFrom;
    private int ageTo;

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public List<Credential> getCredentialRequirements() {
        return credentialRequirements;
    }

    public void setCredentialRequirements(List<Credential> credentialRequirements) {
        this.credentialRequirements = credentialRequirements;
    }
    
    public int getGradeLevelId() {
        return id;
    }

    public void setGradeLevelID(int id) {
        this.id = id;
    }

    public Integer getLevelNo() {
        return level;
    }

    public void setLevelNo(Integer level) {
        this.level = level;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
