package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import dao.ICurriculum;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.curriculum.Curriculum;
import model.gradelevel.GradeLevel;
import model.subject.Subject;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class CurriculumDaoImpl implements ICurriculum {

    private GradeLevelDaoImpl gradeLevelDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;

    public CurriculumDaoImpl() {
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public int getCurriculumIdByName(String curriculumName) {
        int curriculumId = 0; 
        String SQL = "{CALL getCurriculumIdByName(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    curriculumId = rs.getInt("curriculum_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curriculumId;
    }

    @Override
    public boolean addCurriculum(Curriculum curriculum) {
        boolean isSuccessful = true;
        String SQLa = "{CALL addCurriculum(?,?,?,?)}";
        String SQLb = "{CALL addCurriculumSubjects(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                csa.setInt(1, curriculum.getSchoolYearId());
                csa.setString(2, curriculum.getTitle());
                csa.setString(3, curriculum.getDescription());
                csa.registerOutParameter(4, java.sql.Types.INTEGER);
                csa.executeUpdate();
                int curriculumId = csa.getInt(4);

                csb.setInt(1, curriculumId);
                Object[] subjectList = curriculum.getSubjects().toArray();
                for (Object o : subjectList) {
                    Subject s = (Subject) o;
                    csb.setInt(2, s.getSubjectId());
                    csb.setDouble(3, s.getSubjectMinutes());
                    csb.executeUpdate();
                }
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                isSuccessful = false;
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean updateCurriculum(Curriculum curriculum) {
        boolean isUpdated = false;
        String SQLa = "{CALL updateCurriculumInfo(?,?,?,?,?)}";
        String SQLb = "{CALL removeCurriculumSubjectsById(?)}";
        String SQLc = "{CALL updateCurriculumSubjects(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);
                    CallableStatement csc = con.prepareCall(SQLc);) {
                csa.setInt(1,curriculum.getCurriculumId());
                csa.setInt(2,curriculum.getSchoolYearId());
                csa.setString(3, curriculum.getTitle());
                csa.setString(4, curriculum.getDescription());
                csa.setBoolean(5, curriculum.getIsActive());
                csa.executeUpdate();
                
                csb.setInt(1, curriculum.getCurriculumId());
                csb.executeUpdate();
                
                csc.setInt(1, curriculum.getCurriculumId());
                Object[] curriculumSubjects = curriculum.getSubjects().toArray();
                for(Object o : curriculumSubjects){
                    Subject s = (Subject)o;
                    csc.setInt(2, s.getSubjectId());
                    csc.setDouble(3,s.getSubjectMinutes());
                    csc.executeUpdate();
                }
                con.commit();
                isUpdated = true;
            } catch (SQLException ex) {
                con.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public Curriculum getCurriculumById(int curriculumId) {
        Curriculum curriculum = new Curriculum();
        String SQL = "{CALL getCurriculumById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,curriculumId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    curriculum.setCurriculumId(rs.getInt("curriculum_id"));
                    curriculum.setSchoolYearId(rs.getInt("schoolyear_id"));
                    curriculum.setTitle(rs.getString("curriculum_title"));
                    curriculum.setDescription(rs.getString("description"));
                    curriculum.setIsActive(rs.getBoolean("isActive"));
                    curriculum.setDateCreated(rs.getString("date_created"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curriculum;
    }

    @Override
    public List<Curriculum> getAllCurriculum() {
        List<Curriculum> curriculumList = new ArrayList<>();
        String SQL = "{CALL getAllCurriculum()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Curriculum c = new Curriculum();
                    c.setCurriculumId(rs.getInt("curriculum_id"));
                    c.setSchoolYearId(rs.getInt("schoolyear_id"));
                    c.setTitle(rs.getString("curriculum_title"));
                    c.setDescription(rs.getString("description"));
                    c.setIsActive(rs.getBoolean("isActive"));
                    c.setDateCreated(rs.getString("date_created"));
                    c.setSubjectCount(rs.getInt("subjectCount"));
                    
                    curriculumList.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curriculumList;
    }

    @Override
    public List<Subject> getCurriculumSubjectsById(int curriculumId) {
        List<Subject> curriculumSubjects = new ArrayList<>();
        String SQL = "{CALL getCurriculumSubjectsById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,curriculumId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Subject s = new Subject();
                    s.setSubjectId(rs.getInt("subject_id"));
                    s.setSubjectTitle(rs.getString("title"));
                    s.setSubjectCode(rs.getString("code"));
                    s.setSubjectMinutes(rs.getInt("subject_minutes_per_week"));
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    s.setGradeLevel(gradeLevel);
                    
                    curriculumSubjects.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curriculumSubjects;
    }

    @Override
    public List<Curriculum> getCurriculumByWildCard(String wildCardChar) {
        List<Curriculum> curriculumList = new ArrayList<>();
        String SQL = "{CALL getCurriculumByWildCard(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, wildCardChar);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Curriculum c = new Curriculum();
                    c.setCurriculumId(rs.getInt("curriculum_id"));
                    c.setSchoolYearId(rs.getInt("schoolyear_id"));
                    c.setTitle(rs.getString("curriculum_title"));
                    c.setDescription(rs.getString("description"));
                    c.setIsActive(rs.getBoolean("isActive"));
                    c.setDateCreated(rs.getString("date_created"));
                    c.setSubjectCount(rs.getInt("subjectCount"));
                    
                    curriculumList.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curriculumList;
    }
    
    
}
