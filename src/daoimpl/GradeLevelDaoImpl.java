package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.gradelevel.GradeLevel;
import dao.IGradeLevel;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * GradeLevelDaoImpl class serves as base class for GradeLevelDaoImpl entity /
 * object
 *
 * @author Antonio, John Ferdinand
 */
public class GradeLevelDaoImpl implements IGradeLevel {

    @Override
    public int getId(int level) {
        int gradeLevelId = 0;
        String SQL = "{CALL getGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, level);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    gradeLevelId = rs.getInt("gradelevel_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeLevelId;
    }

    @Override
    public List<GradeLevel> getSummerGradeLevelsOf(SchoolYear schoolYear) {
        List<GradeLevel> summerGradeLevels = new ArrayList<>();
        String SQL = "{CALL getSummerGradeLevelsOf(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id_summer"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    gradeLevel.setIsActive(rs.getBoolean("isActive"));
                    gradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    gradeLevel.setAgeTo(rs.getInt("ageTo"));
                    summerGradeLevels.add(gradeLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summerGradeLevels;
    }

    @Override
    public GradeLevel getCurrentGradeLevelOf(Student student) {
        GradeLevel currentGradeLevel = new GradeLevel();
        String SQL = "{CALL getCurrentGradeLevelOf(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, student.getStudentId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    currentGradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    currentGradeLevel.setLevelNo(rs.getInt("grade_level"));
                    currentGradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    currentGradeLevel.setAgeTo(rs.getInt("ageTo"));
                    currentGradeLevel.setIsActive(rs.getBoolean("isActive"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentGradeLevel;
    }

    
    @Override
    public List<GradeLevel> getAllGradeLevelsInfo() {
        List<GradeLevel> gradeLevelList = new ArrayList();
        String SQL = "{CALL getAllGradeLevelsInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    gradeLevel.setIsActive(rs.getBoolean("isActive"));
                    gradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    gradeLevel.setAgeTo(rs.getInt("ageTo"));
                    gradeLevelList.add(gradeLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeLevelList;
    }

    @Override
    public List<GradeLevel> getAllActiveGradeLevels() {
        List<GradeLevel> list = new ArrayList<>();
        String SQL = "{CALL getAllActiveGradeLevels()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    gradeLevel.setIsActive(rs.getBoolean("isActive"));
                    gradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    gradeLevel.setAgeTo(rs.getInt("ageTo"));
                    list.add(gradeLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getId(GradeLevel gradelevel) {
        int gradeLevelId = 0;
        String SQL = "{CALL getGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradelevel.getLevelNo());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    gradeLevelId = rs.getInt("gradelevel_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeLevelId;
    }

    @Override
    public GradeLevel getById(int gradeLevelId) {
        GradeLevel gradeLevel = new GradeLevel();
        String SQL = "{CALL getGradeLevelById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    gradeLevel.setIsActive(rs.getBoolean("isActive"));
                    gradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    gradeLevel.setAgeTo(rs.getInt("ageTo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeLevel;
    }

}
