package component_model_loader;

import daoimpl.EnrollmentDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.enrollment.Enrollment;
import model.gradelevel.GradeLevel;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;

/**
 *
 * @author Jordan
 */
public class EnrollmentJCompModelLoader {

    private final EnrollmentDaoImpl enrollmentDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final SchoolYear currentSchoolYear;
    
    public EnrollmentJCompModelLoader(SchoolYear currentSchoolYear) {
        this.enrollmentDaoImpl = new EnrollmentDaoImpl();
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        this.gradeLevelDaoImpl = new GradeLevelDaoImpl();
        this.currentSchoolYear = currentSchoolYear;
    }
    
    
    public DefaultTableModel getAllEnrolledOfCurrentSchoolYear(JTable t) {
        DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
        tableModel.setRowCount(0);
        SectionDaoImpl sectionDaoImpl = new SectionDaoImpl();
        
        int currentSchoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        List<Student> studentList = enrollmentDaoImpl.getAllEnrolledBySchoolYearId(currentSchoolYearId);
        for (Student s : studentList) {
            Section section = sectionDaoImpl.getSectionOf(s, currentSchoolYear);
            Registration r = s.getRegistration();
            Enrollment e = s.getEnrollment();
            String sectionName = "";
            String adviserLastName = "";
            String adviserMiddleName ="";
            String adviserFirstName = "";
            if(section.getAdviser() != null){
                sectionName = section.getSectionName();
                adviserLastName = section.getAdviser().getLastName();
                adviserMiddleName = section.getAdviser().getMiddleName();
                adviserFirstName = section.getAdviser().getFirstName();
            }
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), r.getLastName()+", " + r.getFirstName()+" "+ r.getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old",
                s.getGradeLevelNo(), 
//                s.getSection().getSectionName(), 
                sectionName,
                adviserLastName +" "+ adviserFirstName +" "+
                adviserMiddleName,
                s.isActive() == true ? "Active" : "Inactive",
                e.getEnrollmentDate(), e.getEnrollmentType().equalsIgnoreCase("R") ? "Regular" : "Summer"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultTableModel getAllEnrolledOfCurrentSchoolYearByGradeLevel(JTable t, JComboBox jcmbGradeLevelFilter) {
        DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
        tableModel.setRowCount(0);
        int currentSchoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        int gradeLevelId = gradeLevelDaoImpl.getId(Integer.parseInt(jcmbGradeLevelFilter.getSelectedItem().toString().trim()));
        List<Student> studentList = enrollmentDaoImpl.getAllEnrolledBySchoolYearIdAndGradeLevelId(currentSchoolYearId, gradeLevelId);
        for (Student s : studentList) {
            Registration r = s.getRegistration();
            Enrollment e = s.getEnrollment();
           
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), r.getLastName() + ", " + r.getFirstName() + " " + r.getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old",
                s.getGradeLevelNo(),
                s.getSection().getSectionName(),
                (s.getSection().getAdviser().getLastName() == null ? "" : s.getSection().getAdviser().getLastName() + ", ")
                 + (s.getSection().getAdviser().getFirstName() == null ? "" : s.getSection().getAdviser().getFirstName())
                + " "
                + (s.getSection().getAdviser().getMiddleName() == null ? "" : s.getSection().getAdviser().getMiddleName()),
                s.isActive() == true ? "Active" : "Inactive",
                e.getEnrollmentDate(), e.getEnrollmentType().equalsIgnoreCase("R") ? "Regular" : "Summer"
            };
            
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultTableModel getAllEnrolledOfCurrentSchoolYearByWildCard(JTable t, JTextField jtfSearchBox) {
        DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
        tableModel.setRowCount(0);
        String searchKeyword = jtfSearchBox.getText().trim();
        List<Student> studentList = enrollmentDaoImpl.getAllEnrolledBy(currentSchoolYear, searchKeyword);
        for (Student s : studentList) {
            Registration r = s.getRegistration();
            Enrollment e = s.getEnrollment();
           
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), r.getLastName() + ", " + r.getFirstName() + " " + r.getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old",
                s.getGradeLevelNo(),
                s.getSection().getSectionName(),
                (s.getSection().getAdviser().getLastName() == null ? "" : s.getSection().getAdviser().getLastName() + ", ")
                 + (s.getSection().getAdviser().getFirstName() == null ? "" : s.getSection().getAdviser().getFirstName())
                + " "
                + (s.getSection().getAdviser().getMiddleName() == null ? "" : s.getSection().getAdviser().getMiddleName()),
                s.isActive() == true ? "Active" : "Inactive",
                e.getEnrollmentDate(), e.getEnrollmentType().equalsIgnoreCase("R") ? "Regular" : "Summer"
            };
            
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    
    public DefaultTableModel getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(JTable table, JComboBox jcmbGradeLevel, int schoolYearId) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        GradeLevel gradeLevel = (GradeLevel) jcmbGradeLevel.getSelectedItem();
        int gradeLevelId = gradeLevelDaoImpl.getId(gradeLevel.getLevelNo());
        List<Student> studentList = enrollmentDaoImpl.getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(gradeLevelId, schoolYearId);
        for (Student s : studentList) {
            Registration r = s.getRegistration();
            Enrollment e = s.getEnrollment();
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), r.getLastName(), r.getFirstName(), r.getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old",
                s.getGradeLevelNo(), "--section--", "--adviser--", s.isActive() == true ? "Active" : "Inactive",
                e.getEnrollmentDate(), e.getEnrollmentType().equalsIgnoreCase("R") ? "Regular" : "Summer"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    
    public DefaultTableModel getAllUnsectionedSummerEnrolleesBy(JTable table, JComboBox jcmbGradeLevel, SchoolYear schoolYear) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        GradeLevel gradeLevel = (GradeLevel) jcmbGradeLevel.getSelectedItem();
        int gradeLevelId = gradeLevelDaoImpl.getId(gradeLevel.getLevelNo());
        gradeLevel.setGradeLevelID(gradeLevelId);
        List<Student> studentList = enrollmentDaoImpl.getAllUnsectionedSummerEnrolleesBy(gradeLevel, schoolYear,"S");
        for (Student s : studentList) {
            Registration r = s.getRegistration();
            Enrollment e = s.getEnrollment();
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), r.getLastName(), r.getFirstName(), r.getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old",
                s.getGradeLevelNo(), "--section--", "--adviser--", s.isActive() == true ? "Active" : "Inactive",
                e.getEnrollmentDate(), e.getEnrollmentType().equalsIgnoreCase("R") ? "Regular" : "Summer"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
