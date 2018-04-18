package dao;

import java.util.List;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface IEnrollment {
    
    boolean enroll(Student student);
    List<Student> getAllEnrolledBy(SchoolYear schoolYear, String wildCardChar);
    List<Student> getAllEnrolledBySchoolYearId(int schoolYearId);
    List<Student> getAllEnrolledBySchoolYearIdAndGradeLevelId(int schoolyearId, int gradelevelId);
    List<Student> getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(int gradelevelId, int schoolYearId);
    List<Student> getAllUnsectionedSummerEnrolleesBy(GradeLevel gradeLevel, SchoolYear schoolYear, String sectionType);
}
