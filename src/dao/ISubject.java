
package dao;

import java.util.List;
import model.curriculum.Curriculum;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public interface ISubject {
    Subject getSubjectInfoById(int subjectId);
    List<Subject> getSummerSubjectsOf(Student student, SchoolYear schoolYear);
    List<Subject> getAllSubjectsInfo();
    List<Subject> getSubjectInfoByWildCard(String wildCardChar);
    List<Subject> getAllSubjectsByGradeLevelId(int gradeLevelId);
    List<Subject> getSubjectsByCurriculum(Curriculum aCurriculum);
    List<Subject> getSubjectsBySchoolYear(SchoolYear aSchoolYear);
    List<Subject> getEachSubjectByGradeLevelId(GradeLevel aGradeLevel);
    List<Subject> getAllActiveSubjectsByStatusAndGradeLevelId(boolean isActive, int gradeLevelId);
    List<Subject> getSubjectsHandledByFacultyUsingFacultySectionAndSchoolYear(Faculty f, Section s, SchoolYear sy);
    
    boolean updateSubjectAndGradeLevel(Subject aSubject, GradeLevel aGradeLevel);
    boolean createSubject(Subject aSubject);
    boolean editSubject(Subject aSubject);
    
    int getSubjectId(Subject aSubject);
    int getSubjectMinutesPerWeekOf(Subject subject);
    
    boolean subjectExists(Subject aSubject);
    
    List <Subject> getCreatedSubjectInfoById(Subject aSubject, GradeLevel aGradeLevel);
    
    boolean updateCreatedSubjectById(Subject aSubject, GradeLevel aGradeLevel);
    boolean checkSubjectExists(Subject aSubject);
    
    List <Subject> checkSubjectChanges(Subject aSubject);
    List <Subject> getAllStudentSubjectBySectionId(Section aSection);
}
