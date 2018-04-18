
package model.classtype;

import java.util.Date;

/**
 *
 * @author Jordan
 */
public class ClassType {
    
    private int classTypeID;
    private String classTypeName;
    private boolean isActive;
    private Date dateAdded;

    public int getClassTypeID() {
        return classTypeID;
    }

    public void setClassTypeID(int classTypeID) {
        this.classTypeID = classTypeID;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    public boolean isIsActive() {
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
