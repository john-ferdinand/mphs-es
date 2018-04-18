package controller.promotion;

import daoimpl.GradeDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.PromotionDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.promotionInfo.Promotion;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.subject.Subject;
import model.summerstudent.SummerStudent;
import model.user.User;
import view.promotion.Dialog_Promotion;

/**
 *
 * @author Jordan
 */
public class Controller_Promote_JButton implements ActionListener {

    private final PromotionDaoImpl promotionDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final GradeDaoImpl gradeDaoImpl;
    private final Dialog_Promotion view;
    private final SchoolYear currentSchoolYear;
    private final User user;

    public Controller_Promote_JButton(Dialog_Promotion view, SchoolYear currentSchoolYear, User user) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        this.promotionDaoImpl = new PromotionDaoImpl();
        this.studentDaoImpl = new StudentDaoImpl();
        this.gradeLevelDaoImpl = new GradeLevelDaoImpl();
        this.gradeDaoImpl = new GradeDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Clicking YES will execute PROMOTION of passed students and recommend those for SUMMER.\nProceed?", "Promotion confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            List<Student> allStudents = getAllStudents();
            if (subjectGradesComplete(allStudents)) {
                List<Student> studentsToPromote = getStudentsToPromote();
                List<SummerStudent> studentsForSummer = getStudentsForSummer();
                boolean isSuccessful = promotionDaoImpl.promoteStudents(studentsToPromote,studentsForSummer,user);
                if (isSuccessful) {
                    JOptionPane.showMessageDialog(null, "Request successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Encountered problems processing request. Please contact your support.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Some students have incomplete grades.\nYou can only proceed once all subjects are graded.");
                displayWarnings();
            }
        }
    }

    private void clearConsole(){
        view.getJtaWarningConsole().setText("");
    }
    
    private void displayWarnings() {
        JTable t = view.getJtblStudents();
        clearConsole();
        view.getJtaWarningConsole().setText("THE FF. TEACHERS HAVE NOT YET SUBMITTED GRADES FOR THE FF. SUBJECTS : \n\n");
        
        for (int row = 0; row < t.getRowCount(); row++) {
            Object studentNo = t.getValueAt(row, 1);
//            JOptionPane.showMessageDialog(null,"Student No @ row "+row +": "+studentNo);
            Student student = studentDaoImpl.getStudentByStudentNo(Integer.parseInt(studentNo.toString().trim()));
//            JOptionPane.showMessageDialog(null,"Student Id: "+row +" "+student.getStudentId());
            int gradeLevelFrom = Integer.parseInt(t.getValueAt(row, 2).toString().trim());
            int gradeLevelId = gradeLevelDaoImpl.getId(gradeLevelFrom);
            GradeLevel gradeLevel = gradeLevelDaoImpl.getById(gradeLevelId);
            Map<Faculty, Subject> map = gradeDaoImpl.getFacultySubjectPairOfSubjectsNotGradedYet(student, gradeLevel, currentSchoolYear);
            view.getJtaWarningConsole().append(student.getStudentNo() + "");
            for (Map.Entry<Faculty, Subject> me : map.entrySet()) {
                String facultyName = ((Faculty) me.getKey()).getLastName() + ", " + ((Faculty) me.getKey()).getFirstName();
                String subjectCode = ((Subject) me.getValue()).getSubjectCode();
                view.getJtaWarningConsole().append("\tno grades submitted for --> " + " (" + subjectCode + ") --> " + facultyName + "\n");
            }
            view.getJtaWarningConsole().append("\n");
        }
    }

    private List<Student> getStudentsToPromote() {
        List<Student> studentsToPromote = new ArrayList<>();
        JTable t = view.getJtblStudents();
        if (!tableHasNullFields(t)) {
            for (int row = 0; row < t.getRowCount(); row++) {
                Student student = studentDaoImpl.getStudentByStudentNo(Integer.parseInt(t.getValueAt(row, 1).toString().trim()));
                GradeLevel gradeLevelFrom = new GradeLevel();
                gradeLevelFrom.setLevelNo(Integer.parseInt(t.getValueAt(row, 2).toString().trim()));
                GradeLevel gradeLevelTo = new GradeLevel();
                gradeLevelTo.setLevelNo(gradeLevelFrom.getLevelNo() + 1);
                Integer gradeValue = Integer.parseInt(t.getValueAt(row, 3).toString().trim());
                if (gradeValue >= 75) {
                    Promotion promotion = new Promotion();
                    promotion.setStudent(student);
                    promotion.setGradeLevelFrom(gradeLevelFrom);
                    promotion.setGradeLevelTo(gradeLevelTo);
                    promotion.setSchoolYearPromoted(currentSchoolYear);
                    student.setPromotion(promotion);
                    studentsToPromote.add(student);
                }
            }
        } else {
            clearConsole();
            view.getJtaWarningConsole().append("Null Fields found.");
        }
        return studentsToPromote;
    }

    private boolean tableHasNullFields(JTable table) {
        boolean hasNull = false;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object value = table.getValueAt(row, col);
                if (value == null) {
                    hasNull = true;
                    break;
                }
            }
        }
        return hasNull;
    }

    private List<SummerStudent> getStudentsForSummer() {
        List<SummerStudent> summerStudents = new ArrayList<>();
        JTable t = view.getJtblStudents();
        if (!tableHasNullFields(t)) {
            for (int row = 0; row < t.getRowCount(); row++) {
                Student student = studentDaoImpl.getStudentByStudentNo(Integer.parseInt(t.getValueAt(row, 1).toString().trim()));
                GradeLevel summerGradeLevel = new GradeLevel();
                summerGradeLevel.setLevelNo(Integer.parseInt(t.getValueAt(row, 2).toString().trim()));
                Integer gradeValue = Integer.parseInt(t.getValueAt(row, 3).toString().trim());
                if (gradeValue < 75) {
                    SummerStudent summerStudent = new SummerStudent();
                    summerStudent.setStudentId(student.getStudentId());
                    summerStudent.setSummerGradeLevel(summerGradeLevel);
                    summerStudent.setSchoolYearRecommended(currentSchoolYear);
                    summerStudent.setRecommendedBy(user);
                    summerStudents.add(summerStudent);
                }
            }
        } else {
            clearConsole();
            view.getJtaWarningConsole().append("Incomplete grades found. Average of 0 is not valid.");
        }
        return summerStudents;
    }

    /**
     * Use this method with studentssubjectGradesComplete()
     * studentssubjectGradeComplete() needs to check all students, both passed
     * and failed
     *
     * @return
     */
    private List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        JTable t = view.getJtblStudents();
        if (!tableHasNullFields(t)) {
            for (int row = 0; row < t.getRowCount(); row++) {
                Student student = studentDaoImpl.getStudentByStudentNo(Integer.parseInt(t.getValueAt(row, 1).toString().trim()));
                GradeLevel gradeLevelFrom = new GradeLevel();
                gradeLevelFrom.setLevelNo(Integer.parseInt(t.getValueAt(row, 2).toString().trim()));
                GradeLevel gradeLevelTo = new GradeLevel();
                gradeLevelTo.setLevelNo(gradeLevelFrom.getLevelNo() + 1);

                Promotion promotion = new Promotion();
                promotion.setStudent(student);
                promotion.setGradeLevelFrom(gradeLevelFrom);
                promotion.setGradeLevelTo(gradeLevelTo);
                promotion.setSchoolYearPromoted(currentSchoolYear);
                
                student.setPromotion(promotion);
                studentList.add(student);
            }
        } else {
            clearConsole();
            view.getJtaWarningConsole().append("Null Fields found.");
        }
        return studentList;
    }

    /**
     * Checks if all subject grades are submitted already. Method accepts a list
     * of students of a class.
     *
     * @param studentList
     * @return
     */
    private boolean subjectGradesComplete(List<Student> studentList) {
        boolean complete = false;
        List<Student> studentWithIncompleteGrades = new ArrayList<>();
        for (Student student : studentList) {
            GradeLevel gradeLevelFrom = student.getPromotion().getGradeLevelFromPromoted();
            gradeLevelFrom.setGradeLevelID(gradeLevelDaoImpl.getId(gradeLevelFrom.getLevelNo()));
            complete = promotionDaoImpl.areAllStudentGradePerSubjectSubmitted(student, currentSchoolYear, gradeLevelFrom);
            if (!complete) {
                studentWithIncompleteGrades.add(student);
            }
        }
        return studentWithIncompleteGrades.size() == 0;
    }

}
