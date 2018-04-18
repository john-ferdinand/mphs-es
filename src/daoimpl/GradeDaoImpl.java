package daoimpl;

import dao.IGrade;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.grade.Grade;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.subject.Subject;
import model.user.User;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class GradeDaoImpl implements IGrade {

    @Override
    public boolean addStudentGrades(List<Grade> gradeList) {
        boolean isSuccessful = false;
        String SQLa = "{CALL deleteGradesBySubjectIdAndSchoolYearId(?,?)}";
        String SQLb = "{CALL addGrade(?,?,?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                csa.setInt(1,gradeList.get(0).getSubjectId());
                csa.setInt(2, gradeList.get(0).getSchoolYear().getSchoolYearId());
                csa.executeUpdate();
                
                for (Grade g : gradeList) {
                    csb.setInt(1, g.getStudentId());
                    csb.setInt(2, g.getGradingPeriod());
                    csb.setInt(3, g.getSubjectId());
                    csb.setInt(4, g.getValue());
                    csb.setInt(5, g.getSchoolYear().getSchoolYearId());
                    csb.setString(6, g.getGradeType());
                    csb.setInt(7, g.getAddedBy().getUserId());
                    csb.setInt(8, g.getStudentGradeLevel().getGradeLevelId());
                    csb.executeUpdate();
                }
                isSuccessful = true;
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return isSuccessful;
    }

    @Override
    public List<Grade> getGradesOf(Student student, Subject subject, SchoolYear schoolYear) {
        String SQL = "{CALL getGradesByStudentIdSubjectIdAndSchoolYearId(?,?,?)}";
        List<Grade> gradeList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, subject.getSubjectId());
            cs.setInt(3, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    User user = new User();
                    user.setUserID(rs.getInt("added_by_user_id"));
                    SchoolYear sy = new SchoolYear();
                    sy.setSchoolYearId(rs.getInt("schoolyear_id"));

                    Grade grade = new Grade();
                    grade.setGradeId(rs.getInt("grade_id"));
                    grade.setGradingPeriod(rs.getInt("gradingperiod"));
                    grade.setValue(rs.getInt("gradevalue"));
                    grade.setGradeType(rs.getString("grade_type"));
                    grade.setAddedBy(user);
                    grade.setDateSubmitted(rs.getDate("date_submitted"));
                    grade.setSchoolYear(sy);

                    gradeList.add(grade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeList;
    }

    @Override
    public Grade getGradeByStudentGradingPeriodAndSchoolYear(Student student, int gradingPeriod, SchoolYear schoolYear) {
        Grade grade = new Grade();
        String SQL = "{CALL getGradeByStudentIdGradingPeriodAndSyId(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
//            JOptionPane.showMessageDialog(null,"StudentId: "+student.getStudentId()+"\n"+"GradingPeriod: "+gradingPeriod+"\n"+"SchoolyearId: "+schoolYear.getSchoolYearId());
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, gradingPeriod);
            cs.setInt(3, schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    grade.setValue(rs.getInt("gradingPeriodAverage"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    
    @Override
    public Grade getGradeBySubjectGradingPeriodSchoolYearAndStudent(Subject subject, int gradingPeriod, SchoolYear schoolYear,Student student) {
        Grade grade = new Grade();
        String SQL = "{CALL getGradeBySubjectIdGradingPeriodAndSyId(?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, subject.getSubjectId());
            cs.setInt(2, gradingPeriod);
            cs.setInt(3, schoolYear.getSchoolYearId());
            cs.setInt(4, student.getStudentId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    grade.setValue(rs.getInt("gradingPeriodAverage"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public Grade getFinalGradeOf(Student student, SchoolYear schoolYear) {
        Grade grade = new Grade();
        String SQL = "{CALL getStudentFinalGradeForSchoolYear(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1,student.getStudentId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    grade.setValue(rs.getInt("schoolYearAverage"));
                    grade.setSchoolYear(schoolYear);
                    grade.setStudentId(rs.getInt("student_id"));
                }
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grade;
    }

    @Override
    public Map<Faculty, Subject> getFacultySubjectPairOfSubjectsNotGradedYet(Student student, GradeLevel gradeLevel, SchoolYear schoolYear) {
        Map<Faculty,Subject> map = new HashMap<>();
        String SQL = "{CALL getFacultySubjectPairOfSubjectsNotGradedYet(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, gradeLevel.getGradeLevelId());
            cs.setInt(3, schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));
                    
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectDescription(rs.getString("description"));
                    
                    map.put(faculty, subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    
    
}
