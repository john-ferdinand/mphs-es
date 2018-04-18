package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import dao.ISubject;
import model.curriculum.Curriculum;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import model.subject.Subject;
import utility.database.DBType;
import utility.database.DBUtil;

public class SubjectDaoImpl implements ISubject {

    GradeLevel gl = new GradeLevel();
    Subject subject = new Subject();

    @Override
    public List<Subject> getAllStudentSubjectBySectionId(Section aSection) {
        String sql = "call getAllStudentSubjectBySectionId(?)";
        List<Subject> list = new ArrayList();

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setInt(1, aSection.getSectionId());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    list.add(subject);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error at getAllStudentSubjectBySectionId " + ex);
        }

        return list;
    }

    @Override
    public boolean subjectExists(Subject aSubject) {
        return false;
    }

    @Override
    public int getSubjectMinutesPerWeekOf(Subject subject) {
        int subjectMinutesPerWeek = 0;
        String SQL = "{CALL getSubjectMinutesPerWeekOf(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, subject.getSubjectId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    subjectMinutesPerWeek = rs.getInt("subject_minutes_per_week");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectMinutesPerWeek;
    }

    
    
    @Override
    public List<Subject> getAllSubjectsInfo() {
        List<Subject> list = new ArrayList();
        String sql = "{call getAllSubjectsInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql)) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Subject subject = new Subject();

                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectDescription(rs.getString("description"));
                    subject.setIsActive(rs.getBoolean("isActive"));
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    subject.setGradeLevel(gradeLevel);

                    list.add(subject);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error at getAllSubjects" + ex);
        }
        return list;
    }

    @Override
    public List<Subject> getAllActiveSubjectsByStatusAndGradeLevelId(boolean isActive, int gradeLevelId) {
        String SQL = "{CALL getAllSubjectsByStatusAndGradeLevelId(?,?)}";
        List<Subject> subjectList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, isActive == true ? 1 : 0);
            cs.setInt(2, gradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    GradeLevel g = new GradeLevel();
                    g.setLevelNo(rs.getInt("grade_level"));

                    SchoolYear schoolYearCreated = new SchoolYear();
                    schoolYearCreated.setSchoolYearId(rs.getInt("year_created_schoolyear_id"));

                    Subject s = new Subject();
                    s.setSubjectId(rs.getInt("subject_id"));
                    s.setSubjectCode(rs.getString("code"));
                    s.setSubjectTitle(rs.getString("title"));
                    s.setSubjectDescription(rs.getString("description"));
                    s.setGradeLevel(g);
                    s.setSchoolYearCreated(schoolYearCreated);

                    subjectList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    @Override
    public List<Subject> getSubjectInfoByWildCard(String wildCardChar) {
        List<Subject> subjectList = new ArrayList<>();
        String SQL = "{CALL getSubjectInfoByWildCard(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, wildCardChar);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Subject s = new Subject();
                    s.setSubjectId(rs.getInt("subject_id"));
                    s.setSubjectTitle(rs.getString("title"));
                    s.setSubjectCode(rs.getString("code"));
                    s.setSubjectDescription(rs.getString("description"));
                    s.setIsActive(rs.getBoolean("isActive"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    s.setGradeLevel(gradeLevel);
                    subjectList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    @Override
    public Subject getSubjectInfoById(int subjectId) {
        Subject s = new Subject();
        String SQL = "{CALL getSubjectInfoById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, subjectId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    s.setSubjectId(rs.getInt("subject_id"));
                    s.setSubjectTitle(rs.getString("title"));
                    s.setSubjectCode(rs.getString("code"));
                    s.setSubjectDescription(rs.getString("description"));
                    s.setIsActive(rs.getBoolean("isActive"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    s.setGradeLevel(gradeLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Subject> getAllSubjectsByGradeLevelId(int gradeLevelId) {
        List<Subject> list = new ArrayList();
        String SQL = "{call getAllSubjectsByGradeLevelId(?)}";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevelId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();

                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectDescription(rs.getString("description"));
                    subject.setIsActive(rs.getBoolean("isActive"));
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    subject.setGradeLevel(gradeLevel);
                    list.add(subject);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Subject> getSubjectsByCurriculum(Curriculum aCurriculum) {
        List<Subject> subjectList = new ArrayList<>();
        return subjectList;
    }

    @Override
    public List<Subject> getSubjectsBySchoolYear(SchoolYear aSchoolYear) {
        List<Subject> subjectList = new ArrayList<>();
        return subjectList;
    }

    @Override
    public boolean createSubject(Subject subject) {
        boolean isSuccesful;
        String SQL = "{call createSubject(?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, subject.getSubjectCode());
            cs.setString(2, subject.getSubjectTitle());
            cs.setString(3, subject.getSubjectDescription());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.setInt(5, subject.getGradeLevel().getGradeLevelId());
            cs.setInt(6, subject.getSchoolYearCreated().getSchoolYearId());
            cs.executeUpdate();
            subject.setSubjectId(cs.getInt(4));

            isSuccesful = true;
        } catch (SQLException ex) {
            isSuccesful = false;
            System.err.println("Error at createSubject " + ex);
        }
        return isSuccesful;
    }

    @Override
    public boolean editSubject(Subject aSubject) {
        boolean isSuccessful = true;
        String SQLa = "{CALL updateSubject(?,?,?,?,?)}";
        String SQLb = "{CALL updateSubjectGradeLevelAssignment(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csA = con.prepareCall(SQLa);
                    CallableStatement csB = con.prepareCall(SQLb);) {
                csA.setInt(1, aSubject.getSubjectId());
                csA.setString(2, aSubject.getSubjectCode());
                csA.setString(3, aSubject.getSubjectTitle());
                csA.setString(4, aSubject.getSubjectDescription());
                csA.setBoolean(5, aSubject.isIsActive());
                csA.executeUpdate();

                csB.setInt(1, aSubject.getSubjectId());
                csB.setInt(2, aSubject.getGradeLevel().getGradeLevelId());
                csB.executeUpdate();

                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                isSuccessful = false;
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public int getSubjectId(Subject aSubject) {
        int id = 0;
        String sql = "{call getSubjectIdByCode(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setString(1, aSubject.getSubjectCode());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("subject_id");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error at getSubjectId " + ex);
        }
        return id;
    }

    @Override
    public List<Subject> getCreatedSubjectInfoById(Subject aSubject, GradeLevel aGradeLevel) {
        String sql = "{call getCreatedSubjectInfoById(?)}";
        List<Subject> list = new ArrayList();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setInt(1, aSubject.getSubjectId());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectDescription(rs.getString("description"));
//                    subject.gradeLevel.setLevel(rs.getInt("grade_level"));
                    list.add(subject);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error at getCreatedSubjectInfoById " + ex);
        }

        return list;
    }

    @Override
    public boolean updateCreatedSubjectById(Subject aSubject, GradeLevel aGradeLevel) {
        String sql = "{call updateCreatedSubjectById(?,?,?,?,?)}";
        boolean isSuccessful;

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setInt(1, aSubject.getSubjectId());
            cs.setInt(2, aGradeLevel.getGradeLevelId());
            cs.setString(3, aSubject.getSubjectCode());
            cs.setString(4, aSubject.getSubjectTitle());
            cs.setString(5, aSubject.getSubjectDescription());

            cs.executeUpdate();

            isSuccessful = true;
        } catch (SQLException ex) {
            isSuccessful = false;
            System.err.println("Error at updateCreatedSubjectById " + ex);
        }

        return isSuccessful;
    }

    @Override
    public boolean checkSubjectExists(Subject aSubject) {
        String sql = "{call checkSubjectExists(?)}";
        boolean isSuccessful = false;

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setString(1, aSubject.getSubjectCode());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    isSuccessful = true;
                }
            }
        } catch (SQLException ex) {
            isSuccessful = false;
            System.err.println("Error at checkSubjectExists" + ex);
        }

        return isSuccessful;
    }

    @Override
    public List<Subject> checkSubjectChanges(Subject aSubject) {
        String sql = "call checkSubjectChanges(?)";
        List<Subject> list = new ArrayList();

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setInt(1, aSubject.getSubjectId());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();

                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectDescription(rs.getString("description"));
//                    subject.gradeLevel.setLevel(rs.getInt("grade_level"));

                    list.add(subject);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error at checkSubjectChanges " + ex);
        }

        return list;
    }

    @Override
    public boolean updateSubjectAndGradeLevel(Subject aSubject, GradeLevel aGradeLevel) {
        boolean isSuccessful;
        String sql = "{call updateSubjectAndGradeLevel(?,?,?,?,?,?,?)}";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setInt(2, aGradeLevel.getLevelNo());
            cs.setInt(3, aGradeLevel.getGradeLevelId());
            cs.setString(4, aSubject.getSubjectCode());
            cs.setString(5, aSubject.getSubjectTitle());
            cs.setString(6, aSubject.getSubjectDescription());
            cs.setInt(7, aSubject.getSubjectId());
            cs.executeUpdate();
            isSuccessful = true;

            JOptionPane.showMessageDialog(null, "Succesful!");
        } catch (SQLException ex) {
            isSuccessful = false;
            System.err.println("Error " + ex);
        }

        return isSuccessful;
    }

    @Override
    public List<Subject> getEachSubjectByGradeLevelId(GradeLevel aGradeLevel) {
        String sql = "{call getEachSubjectByGradeLevelId(?)}";
        List<Subject> listSubject = new ArrayList();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(sql);) {
            cs.setInt(1, aGradeLevel.getGradeLevelId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Subject aSubject = new Subject();
                    aSubject.setSubjectId(rs.getInt("subject_id"));
                    aSubject.setSubjectCode(rs.getString("code"));
                    aSubject.setSubjectTitle(rs.getString("title"));
//                    aSubject.gradeLevel.setLevel(rs.getInt("grade_level"));
                    listSubject.add(aSubject);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }

        return listSubject;
    }

    @Override
    public List<Subject> getSubjectsHandledByFacultyUsingFacultySectionAndSchoolYear(Faculty f, Section s, SchoolYear sy) {
        String SQL = "{CALL getSubjectsHandledByFacultyUsingFacultyIdSectionIdAndSyId(?,?,?)}";
        List<Subject> subjectList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, f.getFacultyID());
            cs.setInt(2, s.getSectionId());
            cs.setInt(3, sy.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectDescription(rs.getString("description"));
                    subjectList.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    @Override
    public List<Subject> getSummerSubjectsOf(Student student, SchoolYear schoolYear) {
        String SQL = "{CALL getSummerSubjectsOf(?,?)}";
        List<Subject> subjects = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,student.getStudentId());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectCode(rs.getString("code"));
                    subject.setSubjectTitle(rs.getString("title"));
                    subject.setSubjectDescription(rs.getString("description"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
    
}
