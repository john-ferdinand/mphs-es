
package dao;

import java.util.List;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.summerstudent.SummerStudent;
import model.user.User;

/**
 *
 * @author Jordan
 */
public interface IPromotion {
    boolean promoteSummerStudents(List<Student> summerStudents, User promotedBy);
    boolean isPromoted(Student student, SchoolYear schoolYear, GradeLevel gradeLevel);
    boolean promoteStudents(List<Student> studentsToPromote, List<SummerStudent> studentsForSummer, User promotedBy);
    boolean areAllStudentGradePerSubjectSubmitted(Student student, SchoolYear schoolYear, GradeLevel gradeLevel);
    List<Student> getAllPromotedStudentsOf(SchoolYear schoolYear);
    List<SummerStudent> getAllSummerStudentsOf(SchoolYear schoolYear);
}
