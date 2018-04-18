
package model.promotionInfo;

import java.util.Date;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;

/**
 *
 * @author Jordan
 */
public class Promotion {
    private Student student;
    private GradeLevel gradeLevelFrom;
    private GradeLevel gradeLevelTo;
    private SchoolYear schoolYearPromoted;
    private Date datePromoted;
    private User promotedBy;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public GradeLevel getGradeLevelFromPromoted() {
        return gradeLevelFrom;
    }

    public void setGradeLevelFrom(GradeLevel gradeLevelFrom) {
        this.gradeLevelFrom = gradeLevelFrom;
    }

    public GradeLevel getGradeLevelToPromoted() {
        return gradeLevelTo;
    }

    public void setGradeLevelTo(GradeLevel gradeLevelTo) {
        this.gradeLevelTo = gradeLevelTo;
    }

    public User getPromotedBy() {
        return promotedBy;
    }

    public void setPromotedBy(User promotedBy) {
        this.promotedBy = promotedBy;
    }
    
    public SchoolYear getSchoolYearPromoted() {
        return schoolYearPromoted;
    }

    public void setSchoolYearPromoted(SchoolYear schoolYearPromoted) {
        this.schoolYearPromoted = schoolYearPromoted;
    }

    public Date getDatePromoted() {
        return datePromoted;
    }

    public void setDatePromoted(Date datePromoted) {
        this.datePromoted = datePromoted;
    }
}
