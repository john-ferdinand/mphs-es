
package dao;

import java.util.List;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * @author Jordan
 */
public interface IGradeLevel {
    int getId(int level);
    int getId(GradeLevel gradeLevel);
    GradeLevel getById(int gradeLevelId);
    GradeLevel getCurrentGradeLevelOf(Student student);
    
    List<GradeLevel> getSummerGradeLevelsOf(SchoolYear schoolYear);
    List<GradeLevel> getAllGradeLevelsInfo();
    List<GradeLevel> getAllActiveGradeLevels();
}
