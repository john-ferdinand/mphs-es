
package dao;

import java.util.List;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;


public interface IStudent {
    List<Student> getStudentsBySectionAndSchoolYear(Section section, SchoolYear schoolYear);
    List<Student> getStudentsOfAdviser(Faculty faculty, SchoolYear schoolYear);
    Student getStudentByStudentId(int studentId);
    Student getStudentByStudentNo(int studentNo);
    boolean studentExist(int studentNo);
    boolean hasTuitionRecord(int studentNo, int schoolyearId);
    List<Student> getStudentsWithTuitionBalance();
    
    /**
     * This method can be used to search student by wild card character.
     * Student ID, Admission ID, Registration ID, Last name, First name, or Middle name can be used to 
     * search student.
     * This returns a list of Student where the user can select a specific student.
     * This method is used commonly in Payment Module.
     * @param keyword
     * @return 
     */
    List<Student> getStudentsByKeyword(String keyword);
}
