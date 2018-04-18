package model.summerstudent;

import java.util.Date;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;

/**
 *
 * @author Jordan
 */
public class SummerStudent extends Student{
    private int summerId;
    private GradeLevel summerGradeLevel;
    private Date dateRecommendedForSummer;
    private User recommendedBy;
    private SchoolYear schoolYearRecommended;
    private boolean isEnrolledInSummer;
    private String sectionName;
    private boolean isAlreadyPromoted;

    public boolean getIsAlreadyPromoted() {
        return isAlreadyPromoted;
    }

    public void setIsAlreadyPromoted(boolean isAlreadyPromoted) {
        this.isAlreadyPromoted = isAlreadyPromoted;
    }
    
    
    
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public boolean getIsEnrolledInSummer() {
        return isEnrolledInSummer;
    }

    public void setIsEnrolledInSummer(boolean isEnrolledInSummer) {
        this.isEnrolledInSummer = isEnrolledInSummer;
    }

    public int getSummerId() {
        return summerId;
    }

    public void setSummerId(int summerId) {
        this.summerId = summerId;
    }

    public GradeLevel getSummerGradeLevel() {
        return summerGradeLevel;
    }

    public void setSummerGradeLevel(GradeLevel summerGradeLevel) {
        this.summerGradeLevel = summerGradeLevel;
    }

    public Date getDateRecommendedForSummer() {
        return dateRecommendedForSummer;
    }

    public void setDateRecommendedForSummer(Date dateRecommendedForSummer) {
        this.dateRecommendedForSummer = dateRecommendedForSummer;
    }

    public User getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(User recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public SchoolYear getSchoolYearRecommendedToSummer() {
        return schoolYearRecommended;
    }

    public void setSchoolYearRecommended(SchoolYear schoolYearRecommended) {
        this.schoolYearRecommended = schoolYearRecommended;
    }
    
    
}
