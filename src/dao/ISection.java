package dao;

import java.util.List;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;

public interface ISection {

    boolean addSection(Section section);

    boolean addStudentsToSection(Section section);

    boolean updateSection(Section section);

    boolean sectionExists(String sectionName);

    Section getSectionById(int sectionId);

    List<Section> getAllSections();

    List<Section> getSectionsWithNoAssignedScheduleByStatusAndSchoolYearId(boolean isActive, int schoolyearId);

    List<Section> getSectionsWithNoAssignedScheduleBy_Status_SchoolYearId_GradeLevelId(boolean isActive, int schoolyearId, int gradeLevelId);

    List<Section> getSectionsBy(boolean status, int schoolYearId);

    List<Section> getSectionsBy(String wildCardChar);

    List<Section> getSectionsBy(int gradeLevelNo, int schoolYearId);

    List<Student> getSectionStudentsOf(Section section);

    List<Section> getNonAdvisorySectionsOf(Faculty faculty, SchoolYear schoolYear);

    List<Section> getAdvisorySectionsOf(Faculty faculty, SchoolYear schoolYear);

    List<Section> getSectionsBy(GradeLevel gradeLevel, String sectionType, SchoolYear schoolYear);

    List<Section> getSectionsWithNoAssignedSchedBySchoolYearGradeLevelAndSectionType(SchoolYear sy, GradeLevel g, String sectionType);

    Section getSectionOf(Student student, SchoolYear schoolYear);
    
    Section getSectionOf(Student student, GradeLevel gradeLevel, SchoolYear schoolYear, String sectionType);

    List<Section> getSectionsBy(int gradeLevelNo);
   
    
}
