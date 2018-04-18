
package dao;

import java.util.List;
import java.util.Map;
import model.faculty.Faculty;
import model.grade.Grade;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.subject.Subject;



/**
 *
 * @author John Ferdinand Antonio
 */
public interface IGrade {
    boolean addStudentGrades(List<Grade> gradeList);
    Map<Faculty,Subject> getFacultySubjectPairOfSubjectsNotGradedYet(Student student, GradeLevel gradeLevel, SchoolYear schoolYear);
    List<Grade> getGradesOf(Student student, Subject subject, SchoolYear schoolYear);
    Grade getFinalGradeOf(Student student, SchoolYear schoolYear);
    Grade getGradeByStudentGradingPeriodAndSchoolYear(Student student, int gradingPeriod, SchoolYear schoolYear);
    Grade getGradeBySubjectGradingPeriodSchoolYearAndStudent(Subject subject, int gradingPeriod, SchoolYear schoolYear,Student student);
}
