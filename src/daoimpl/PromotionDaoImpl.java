package daoimpl;

import dao.IPromotion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.gradelevel.GradeLevel;
import model.promotionInfo.Promotion;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.summerstudent.SummerStudent;
import model.user.User;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class PromotionDaoImpl implements IPromotion {

    @Override
    public boolean isPromoted(Student student, SchoolYear schoolYear, GradeLevel gradeLevel) {
        boolean isPromoted = false;
        String SQL = "{CALL isStudentPromotedTo(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            cs.setInt(3, gradeLevel.getGradeLevelId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    isPromoted = rs.getBoolean("isPromoted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isPromoted;
    }

    @Override
    public boolean promoteSummerStudents(List<Student> summerStudents, User promotedBy) {
        boolean isSuccessful = false;
        GradeLevelDaoImpl gradeLevelDaoImpl = new GradeLevelDaoImpl();
        String SQLa = "{CALL markStudentGradeLevelAsPassed(?,?,?)}";
        String SQLb = "{CALL addPromotionRecord(?,?,?,?,?)}";
        String SQLc = "{CALL addStudentGradeLevelRecord(?,?,?)}";
        try (Connection connection = DBUtil.getConnection(DBType.MYSQL);) {
            connection.setAutoCommit(false);
            try (CallableStatement csa = connection.prepareCall(SQLa);
                    CallableStatement csb = connection.prepareCall(SQLb);
                    CallableStatement csc = connection.prepareCall(SQLc);) {
                for (Student promotedSummerStudents : summerStudents) {
                    Promotion promotion = promotedSummerStudents.getPromotion();
                    int studentId = promotedSummerStudents.getStudentId();
                    int gradeLevelFromId = gradeLevelDaoImpl.getId(promotion.getGradeLevelFromPromoted().getLevelNo());
                    int gradeLevelToId = gradeLevelDaoImpl.getId(promotion.getGradeLevelToPromoted().getLevelNo());
                    int yearPromotedSchoolYearId = promotion.getSchoolYearPromoted().getSchoolYearId();
                    csa.setInt(1, studentId);
                    csa.setInt(2, gradeLevelFromId);
                    csa.setInt(3, yearPromotedSchoolYearId);
                    csa.executeUpdate();

                    csb.setInt(1, studentId);
                    csb.setInt(2, gradeLevelFromId);
                    csb.setInt(3, gradeLevelToId);
                    csb.setInt(4, yearPromotedSchoolYearId);
                    csb.setInt(5, promotedBy.getUserId());
                    csb.executeUpdate();

                    csc.setInt(1, yearPromotedSchoolYearId);
                    csc.setInt(2, studentId);
                    csc.setInt(3, gradeLevelToId);
                    csc.executeUpdate();
                }
                connection.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }
    
    @Override
    public boolean promoteStudents(List<Student> studentsToPromote, List<SummerStudent> summerStudents, User user) {
        boolean isSuccessful = false;
        GradeLevelDaoImpl gradeLevelDaoImpl = new GradeLevelDaoImpl();
        String SQLa = "{CALL markStudentGradeLevelAsPassed(?,?,?)}";
        String SQLb = "{CALL addPromotionRecord(?,?,?,?,?)}";
        String SQLc = "{CALL addStudentGradeLevelRecord(?,?,?)}";
        String SQLd = "{CALL addStudentForSummer(?,?,?,?)}";
        try (Connection connection = DBUtil.getConnection(DBType.MYSQL);) {
            connection.setAutoCommit(false);
            try (CallableStatement csa = connection.prepareCall(SQLa);
                    CallableStatement csb = connection.prepareCall(SQLb);
                    CallableStatement csc = connection.prepareCall(SQLc);
                    CallableStatement csd = connection.prepareCall(SQLd);) {
                for (Student promotedStudent : studentsToPromote) {
                    Promotion promotion = promotedStudent.getPromotion();
                    int studentId = promotedStudent.getStudentId();
                    int gradeLevelFromId = gradeLevelDaoImpl.getId(promotion.getGradeLevelFromPromoted().getLevelNo());
                    int gradeLevelToId = gradeLevelDaoImpl.getId(promotion.getGradeLevelToPromoted().getLevelNo());
                    int yearPromotedSchoolYearId = promotion.getSchoolYearPromoted().getSchoolYearId();
                    csa.setInt(1, studentId);
                    csa.setInt(2, gradeLevelFromId);
                    csa.setInt(3, yearPromotedSchoolYearId);
                    csa.executeUpdate();

                    csb.setInt(1, studentId);
                    csb.setInt(2, gradeLevelFromId);
                    csb.setInt(3, gradeLevelToId);
                    csb.setInt(4, yearPromotedSchoolYearId);
                    csb.setInt(5, user.getUserId());
                    csb.executeUpdate();

                    csc.setInt(1, yearPromotedSchoolYearId);
                    csc.setInt(2, studentId);
                    csc.setInt(3, gradeLevelToId);
                    csc.executeUpdate();
                }

                for (SummerStudent summerStudent : summerStudents) {
                    int studentId = summerStudent.getStudentId();
                    int schoolYearRecommendedToSummer = summerStudent.getSchoolYearRecommendedToSummer().getSchoolYearId();
                    int summerGradeLevelId = gradeLevelDaoImpl.getId(summerStudent.getSummerGradeLevel().getLevelNo());
                    csd.setInt(1, studentId);
                    csd.setInt(2, schoolYearRecommendedToSummer);
                    csd.setInt(3, summerGradeLevelId);
                    csd.setInt(4, user.getUserId());
                    csd.executeUpdate();
                }
                connection.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean areAllStudentGradePerSubjectSubmitted(Student student, SchoolYear schoolYear, GradeLevel gradeLevel) {
        boolean areAllGradesSubmitted = false;
        String SQL = "{CALL areAllStudentGradePerSubjectSubmitted(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            cs.setInt(3, gradeLevel.getGradeLevelId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    areAllGradesSubmitted = rs.getBoolean("all_student_subjects_grade_complete");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areAllGradesSubmitted;
    }

    @Override
    public List<Student> getAllPromotedStudentsOf(SchoolYear schoolYear) {
        String SQL = "{CALL getAllPromotedStudentsOf(?)}";
        List<Student> promotedStudents = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Registration registration = new Registration();
                    registration.setLastName(rs.getString("lastname"));
                    registration.setFirstName(rs.getString("firstname"));
                    registration.setMiddleName(rs.getString("middlename"));

                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setRegistration(registration);

                    GradeLevel gradeLevelFrom = new GradeLevel();
                    gradeLevelFrom.setGradeLevelID(rs.getInt("gradeLevelFromId"));
                    gradeLevelFrom.setLevelNo(rs.getInt("gradeLevelFrom"));

                    GradeLevel gradeLevelTo = new GradeLevel();
                    gradeLevelTo.setGradeLevelID(rs.getInt("gradeLevelToId"));
                    gradeLevelTo.setLevelNo(rs.getInt("gradeLevelTo"));

                    User user = new User();
                    user.setUserID(rs.getInt("user_id"));
                    user.setLastName(rs.getString("userLastName"));
                    user.setFirstName(rs.getString("userFirstName"));
                    user.setMiddleName(rs.getString("userMiddleName"));

                    Promotion promotion = new Promotion();
                    promotion.setStudent(student);
                    promotion.setGradeLevelFrom(gradeLevelFrom);
                    promotion.setGradeLevelTo(gradeLevelTo);
                    promotion.setDatePromoted(rs.getDate("date_of_promotion"));
                    promotion.setPromotedBy(user);

                    student.setPromotion(promotion);
                    promotedStudents.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotedStudents;
    }

    
    @Override
    public List<SummerStudent> getAllSummerStudentsOf(SchoolYear schoolYear) {
        String SQL = "{CALL getAllSummerStudentsOf(?)}";
        List<SummerStudent> summerStudents = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Registration registration = new Registration();
                    registration.setLastName(rs.getString("lastname"));
                    registration.setFirstName(rs.getString("firstname"));
                    registration.setMiddleName(rs.getString("middlename"));

                    GradeLevel summerGradeLevel = new GradeLevel();
                    summerGradeLevel.setGradeLevelID(rs.getInt("summerGradeLevelId"));
                    summerGradeLevel.setLevelNo(rs.getInt("summerGradeLevelNo"));

                    SchoolYear summerSchoolYear = new SchoolYear();
                    summerSchoolYear.setSchoolYearId(rs.getInt("summerSchoolYearId"));
                    summerSchoolYear.setYearFrom(rs.getInt("summerSyFrom"));
                    summerSchoolYear.setYearTo(rs.getInt("summerSyTo"));
                    
                    User user = new User();
                    user.setUserID(rs.getInt("user_id"));
                    user.setLastName(rs.getString("userLastName"));
                    user.setFirstName(rs.getString("userFirstName"));
                    user.setMiddleName(rs.getString("userMiddleName"));
                    
                    SummerStudent summerStudent = new SummerStudent();
                    summerStudent.setStudentId(rs.getInt("student_id"));
                    summerStudent.setStudentNo(rs.getInt("student_no"));
                    summerStudent.setRegistration(registration);
                    summerStudent.setSummerGradeLevel(summerGradeLevel);
                    summerStudent.setSchoolYearRecommended(summerSchoolYear);
                    summerStudent.setRecommendedBy(user);
                    summerStudent.setDateRecommendedForSummer(rs.getDate("dateRecommendedToSummer"));
                    summerStudent.setIsEnrolledInSummer(rs.getBoolean("is_enrolled_in_summer"));
                    summerStudent.setSectionName(rs.getString("sectionName"));
                    summerStudent.setIsAlreadyPromoted(rs.getBoolean("isPromoted"));
                    
                    summerStudents.add(summerStudent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summerStudents;
    }
}
