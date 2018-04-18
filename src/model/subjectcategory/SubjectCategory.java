
package model.subjectcategory;

import java.util.List;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class SubjectCategory {
    private int subjectCategoryId;
    private String subjectCategoryName;
    private List<Subject> subjectsAssigned;
    private String description;
    private boolean isActive;

    public int getSubjectCategoryId() {
        return subjectCategoryId;
    }

    public void setSubjectCategoryId(int subjectCategoryId) {
        this.subjectCategoryId = subjectCategoryId;
    }

    public String getSubjectCategoryName() {
        return subjectCategoryName;
    }

    public void setSubjectCategoryName(String subjectCategoryName) {
        this.subjectCategoryName = subjectCategoryName;
    }

    public List<Subject> getSubjectsAssigned() {
        return subjectsAssigned;
    }

    public void setSubjectsAssigned(List<Subject> subjectsAssigned) {
        this.subjectsAssigned = subjectsAssigned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
