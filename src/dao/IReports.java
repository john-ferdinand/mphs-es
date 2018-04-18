
package dao;

import java.util.List;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.reportcard.ReportCard;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface IReports {
    Student getCOROf(int studentNo, SchoolYear schoolYear);
    List<Student> getClassListOf(Faculty faculty, SchoolYear schoolYear);
    ReportCard getReportCardOf(Student student, GradeLevel gradeLevel, SchoolYear schoolYear);
}
