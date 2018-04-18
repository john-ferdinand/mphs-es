package component_model_loader;

import daoimpl.StudentDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;

public class StudentJCompModelLoader {

    private final StudentDaoImpl studentDaoImpl;

    public StudentJCompModelLoader(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }

    public DefaultTableModel getStudentAndGradesBySectionAndSchoolYear(JTable table, Section section, SchoolYear schoolYear) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Student> studentList = studentDaoImpl.getStudentsBySectionAndSchoolYear(section, schoolYear);
        for (Student s : studentList) {
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(),
                s.getRegistration().getLastName() + ", " + s.getRegistration().getFirstName() + " " + s.getRegistration().getMiddleName()};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultTableModel getStudentsOfAdviser(JTable table, Faculty faculty, SchoolYear schoolYear) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Student> studentList = studentDaoImpl.getStudentsOfAdviser(faculty, schoolYear);
        for(Student s : studentList){
            Object[] rowData = {
                s.getStudentId(),
                s.getStudentNo(),s.getRegistration().getLastName()+", "+s.getRegistration().getFirstName()+" "+s.getRegistration().getMiddleName(),
                s.getGradeLevelNo() == 0? "Kindergarten" : s.getGradeLevelNo(),s.getSection().getSectionName()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

}
