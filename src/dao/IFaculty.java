 
package dao;

import java.util.List;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subjectcategory.SubjectCategory;
import model.user.User;

public interface IFaculty {
    boolean createFaculty(Faculty faculty);
    List <Faculty> getFacultyByName(Faculty faculty);
    List <Faculty> getFacultyInfoById(Faculty faculty);
    int countFacultySpecialization(Faculty faculty);
    void createFacultySpecialization(Faculty faculty, SubjectCategory subjectCategory);
    void deleteFacultySpecialization(Faculty faculty);
    List <SubjectCategory> loadFacultySpecialization(Faculty faculty, SubjectCategory subjectCategory);
    boolean updateFaculty(Faculty faculty);
    List getAllFaculty(Faculty faculty);
    
    // Jordan
    List<Faculty> getAllFacultyHandlingAdvisory(SchoolYear schoolYear);
    List<Faculty> getAllFacultyWithNoAdvisory();
    List<Faculty> getAllFaculty();
    List<Faculty> getAllFacultyByStatus(boolean isAtive);
    List<Faculty> getAllFacultyHandlingSubjectBySubjectCode(String subjectCode, int schoolyearId);
    Faculty getFacultyById(int facultyId);
    Faculty getFacultyByUser(User user);
    boolean isFacultyAvailableAdviserFor(Section section, Faculty faculty, SchoolYear schoolYear);
    boolean isFacultyAvailableAdviserFor(String sectionSession, Faculty faculty, SchoolYear schoolYear);
}
