
package daoimpl;
import constants.SchoolYearTable;
import utility.database.DBType;
import utility.database.DBUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.schoolyear.SchoolYear;
import model.quarter.Quarter;
import dao.ISchoolYear;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import utility.date.DateUtil;


public class SchoolYearDaoImpl implements ISchoolYear{

    private DateUtil dateUtil;
    
    public SchoolYearDaoImpl(){
        dateUtil = new DateUtil();
    }
    
    
    public static int getCurrentSchoolYearFrom() {
        int aYearFrom = 0;
        String SQL = "{CALL getCurrentSchoolYearFrom()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    aYearFrom = rs.getInt(SchoolYearTable.YEARFROM);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aYearFrom;
    }

    @Override
    public int getCurrentSchoolYearId() {
        int idOfCurrentSchoolYear = 0;
        String SQL = "{CALL getCurrentSchoolYearId()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    idOfCurrentSchoolYear = rs.getInt(SchoolYearTable.SCHOOLYEARID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idOfCurrentSchoolYear;
    }

    public static void setCurrentSchoolYear(){
        String SQL = "{CALL setCurrentSchoolYear()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public SchoolYear getCurrentSchoolYear(){
        String SQL = "{CALL getCurrentSchoolYear()}";
        SchoolYear schoolYear = new SchoolYear();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));
                    schoolYear.setSchoolYearStartDate(rs.getDate("start_date"));
                    schoolYear.setSchoolYearEndDate(rs.getDate("end_date"));
                    schoolYear.setRegularEnrollmentStartDate(rs.getDate("reg_enroll_start_date"));
                    schoolYear.setRegularEnrollmentEndDate(rs.getDate("reg_enroll_end_date"));
                    schoolYear.setSummerEnrollmentStartDate(rs.getDate("summer_enroll_start_date"));
                    schoolYear.setSummerEnrollmentEndDate(rs.getDate("summer_enroll_end_date"));
                    schoolYear.setSummerClassStartDate(rs.getDate("summer_class_start_date"));
                    schoolYear.setSummerClassEndDate(rs.getDate("summer_class_end_date"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    
                    List<Quarter> quarters = new ArrayList<>();
                    Quarter first = new Quarter();
                    first.setQuarterNo(1);
                    first.setStartDate(rs.getDate("Q1Start"));
                    first.setEndDate(rs.getDate("Q1End"));
                    first.setGradingDueDate(rs.getDate("Q1GradingDueDate"));
                    quarters.add(first);
                    
                    Quarter second = new Quarter();
                    second.setQuarterNo(2);
                    second.setStartDate(rs.getDate("Q2Start"));
                    second.setEndDate(rs.getDate("Q2End"));
                    second.setGradingDueDate(rs.getDate("Q2GradingDueDate"));
                    quarters.add(second);
                    
                    Quarter third = new Quarter();
                    third.setQuarterNo(3);
                    third.setStartDate(rs.getDate("Q3Start"));
                    third.setEndDate(rs.getDate("Q3End"));
                    third.setGradingDueDate(rs.getDate("Q3GradingDueDate"));
                    quarters.add(third);
                    
                    Quarter fourth = new Quarter();
                    fourth.setQuarterNo(4);
                    fourth.setStartDate(rs.getDate("Q4Start"));
                    fourth.setEndDate(rs.getDate("Q4End"));
                    fourth.setGradingDueDate(rs.getDate("Q4GradingDueDate"));
                    quarters.add(fourth);
                    
                    schoolYear.setQuarters(quarters);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYear;
    }

    @Override
    public List<SchoolYear> getAllSchoolYear() {
        List<SchoolYear> schoolYearList = new ArrayList<>();
        String SQL = "{CALL getAllSchoolYear()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));
                    schoolYear.setSchoolYearStartDate(rs.getDate("start_date"));
                    schoolYear.setSchoolYearEndDate(rs.getDate("end_date"));
                    schoolYear.setRegularEnrollmentStartDate(rs.getDate("reg_enroll_start_date"));
                    schoolYear.setRegularEnrollmentEndDate(rs.getDate("reg_enroll_end_date"));
                    schoolYear.setSummerEnrollmentStartDate(rs.getDate("summer_enroll_start_date"));
                    schoolYear.setSummerEnrollmentEndDate(rs.getDate("summer_enroll_end_date"));
                    schoolYear.setSummerClassStartDate(rs.getDate("summer_class_start_date"));
                    schoolYear.setSummerClassEndDate(rs.getDate("summer_class_end_date"));
                    schoolYear.setIsCurrentSchoolYear(rs.getBoolean("isCurrentSchoolYear"));
                    
                    List<Quarter> quarters = new ArrayList<>();
                    Quarter first = new Quarter();
                    first.setQuarterNo(1);
                    first.setStartDate(rs.getDate("Q1Start"));
                    first.setEndDate(rs.getDate("Q1End"));
                    first.setGradingDueDate(rs.getDate("Q1GradingDueDate"));
                    quarters.add(first);
                    
                    Quarter second = new Quarter();
                    second.setQuarterNo(2);
                    second.setStartDate(rs.getDate("Q2Start"));
                    second.setEndDate(rs.getDate("Q2End"));
                    second.setGradingDueDate(rs.getDate("Q2GradingDueDate"));
                    quarters.add(second);
                    
                    Quarter third = new Quarter();
                    third.setQuarterNo(3);
                    third.setStartDate(rs.getDate("Q3Start"));
                    third.setEndDate(rs.getDate("Q3End"));
                    third.setGradingDueDate(rs.getDate("Q3GradingDueDate"));
                    quarters.add(third);
                    
                    Quarter fourth = new Quarter();
                    fourth.setQuarterNo(4);
                    fourth.setStartDate(rs.getDate("Q4Start"));
                    fourth.setEndDate(rs.getDate("Q4End"));
                    fourth.setGradingDueDate(rs.getDate("Q4GradingDueDate"));
                    quarters.add(fourth);
                    
                    schoolYear.setQuarters(quarters);
                    
                    schoolYearList.add(schoolYear);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYearList;
    }
    
    
    @Override
    public boolean isCurrent(SchoolYear schoolyear) {
        boolean isCurrent = false;
        int rowCount = 0;
        String SQL = "{CALL isCurrentSchoolYear(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCurrent;
    }

    @Override
    public List<SchoolYear> getAllSchoolYearInfo() {
        List<SchoolYear> list = new ArrayList<>();
        String SQL = "{CALL getAllSchoolYearInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SchoolYear sy = new SchoolYear();
                    sy.setSchoolYearId(rs.getInt("schoolyear_id"));
                    sy.setYearFrom(rs.getInt("yearFrom"));
                    sy.setYearTo(rs.getInt("yearTo"));
                    sy.setIsActive(rs.getBoolean("isActive"));
                    sy.setSchoolYearStartDate(rs.getDate("start_date"));
                    sy.setSchoolYearEndDate(rs.getDate("end_date"));
                    sy.setRegularEnrollmentStartDate(rs.getDate("reg_enroll_start_date"));
                    sy.setRegularEnrollmentEndDate(rs.getDate("reg_enroll_end_date"));
                    sy.setSummerEnrollmentStartDate(rs.getDate("summer_enroll_start_date"));
                    sy.setSummerEnrollmentEndDate(rs.getDate("summer_enroll_end_date"));
                    sy.setSummerClassStartDate(rs.getDate("summer_class_start_date"));
                    sy.setSummerClassEndDate(rs.getDate("summer_class_end_date"));
                    sy.setTotalSchoolDays(rs.getInt("totalSchoolDays"));
                    
                    List<Quarter> quarters = new ArrayList<>();
                    Quarter first = new Quarter();
                    first.setQuarterNo(1);
                    first.setStartDate(rs.getDate("Q1Start"));
                    first.setEndDate(rs.getDate("Q1End"));
                    first.setGradingOpenDate(rs.getDate("Q1GradingOpenDate"));
                    first.setGradingDueDate(rs.getDate("Q1GradingDueDate"));
                    quarters.add(first);
                    
                    Quarter second = new Quarter();
                    second.setQuarterNo(2);
                    second.setStartDate(rs.getDate("Q2Start"));
                    second.setEndDate(rs.getDate("Q2End"));
                    second.setGradingOpenDate(rs.getDate("Q2GradingOpenDate"));
                    second.setGradingDueDate(rs.getDate("Q2GradingDueDate"));
                    quarters.add(second);
                    
                    Quarter third = new Quarter();
                    third.setQuarterNo(3);
                    third.setStartDate(rs.getDate("Q3Start"));
                    third.setEndDate(rs.getDate("Q3End"));
                    third.setGradingOpenDate(rs.getDate("Q3GradingOpenDate"));
                    third.setGradingDueDate(rs.getDate("Q3GradingDueDate"));
                    quarters.add(third);
                    
                    Quarter fourth = new Quarter();
                    fourth.setQuarterNo(4);
                    fourth.setStartDate(rs.getDate("Q4Start"));
                    fourth.setEndDate(rs.getDate("Q4End"));
                    fourth.setGradingOpenDate(rs.getDate("Q4GradingOpenDate"));
                    fourth.setGradingDueDate(rs.getDate("Q4GradingDueDate"));
                    quarters.add(fourth);
                    
                    sy.setQuarters(quarters);
                    
                    list.add(sy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SchoolYear> getAllYearFrom() {
        List<SchoolYear> schoolYearList = new ArrayList<>();
        String SQL = "{CALL getAllSchoolYearInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SchoolYear sy = new SchoolYear();
                    sy.setYearFrom(rs.getInt("yearFrom"));
                    schoolYearList.add(sy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYearList;
    }

    @Override
    public List<SchoolYear> getAllYearTo() {
        List<SchoolYear> schoolYearList = new ArrayList<>();
        String SQL = "{CALL getAllSchoolYearInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYearList.add(schoolYear);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYearList;
    }

    @Override
    public SchoolYear getSchoolYearById(int schoolYearId) {
        String SQL = "{CALL getSchoolYearById(?)}";
        SchoolYear sy = new SchoolYear();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, schoolYearId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    sy.setSchoolYearId(rs.getInt("schoolyear_id"));
                    sy.setYearFrom(rs.getInt("yearFrom"));
                    sy.setYearTo(rs.getInt("yearTo"));
                    sy.setIsActive(rs.getBoolean("isActive"));
                    sy.setSchoolYearStartDate(rs.getDate("start_date"));
                    sy.setSchoolYearEndDate(rs.getDate("end_date"));
                    sy.setRegularEnrollmentStartDate(rs.getDate("reg_enroll_start_date"));
                    sy.setRegularEnrollmentEndDate(rs.getDate("reg_enroll_end_date"));
                    sy.setSummerEnrollmentStartDate(rs.getDate("summer_enroll_start_date"));
                    sy.setSummerEnrollmentEndDate(rs.getDate("summer_enroll_end_date"));
                    sy.setSummerClassStartDate(rs.getDate("summer_class_start_date"));
                    sy.setSummerClassEndDate(rs.getDate("summer_class_end_date"));
                    sy.setTotalSchoolDays(rs.getInt("totalSchoolDays"));
                    
                    List<Quarter> quarters = new ArrayList<>();
                    Quarter first = new Quarter();
                    first.setQuarterNo(1);
                    first.setStartDate(rs.getDate("Q1Start"));
                    first.setEndDate(rs.getDate("Q1End"));
                    first.setGradingOpenDate(rs.getDate("Q1GradingOpenDate"));
                    first.setGradingDueDate(rs.getDate("Q1GradingDueDate"));
                    quarters.add(first);
                    
                    Quarter second = new Quarter();
                    second.setQuarterNo(2);
                    second.setStartDate(rs.getDate("Q2Start"));
                    second.setEndDate(rs.getDate("Q2End"));
                    second.setGradingOpenDate(rs.getDate("Q2GradingOpenDate"));
                    second.setGradingDueDate(rs.getDate("Q2GradingDueDate"));
                    quarters.add(second);
                    
                    Quarter third = new Quarter();
                    third.setQuarterNo(3);
                    third.setStartDate(rs.getDate("Q3Start"));
                    third.setEndDate(rs.getDate("Q3End"));
                    third.setGradingOpenDate(rs.getDate("Q3GradingOpenDate"));
                    third.setGradingDueDate(rs.getDate("Q3GradingDueDate"));
                    quarters.add(third);
                    
                    Quarter fourth = new Quarter();
                    fourth.setQuarterNo(4);
                    fourth.setStartDate(rs.getDate("Q4Start"));
                    fourth.setEndDate(rs.getDate("Q4End"));
                    fourth.setGradingOpenDate(rs.getDate("Q4GradingOpenDate"));
                    fourth.setGradingDueDate(rs.getDate("Q4GradingDueDate"));
                    quarters.add(fourth);
                    
                    sy.setQuarters(quarters);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sy;
    }

    @Override
    public int getId(int schoolYearFrom) {
        Integer schoolYearId = null;
        String SQL = "{CALL getSchoolYearIdByYearFrom(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, schoolYearFrom);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    schoolYearId = rs.getInt("schoolyear_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYearId;
    }

    @Override
    public boolean add(SchoolYear schoolYear) {
        boolean isAdded = false;
        String SQLa = "{CALL addSchoolYear(?,?,?,?,?,?,?,?,?,?,?)}";
        String SQLb = "{CALL addSchoolYearQuarterSchedule(?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);){
                csa.setInt(1,schoolYear.getYearFrom());
                csa.setInt(2, schoolYear.getYearTo());
                csa.setDate(3,dateUtil.toSqlDate(schoolYear.getSchoolYearStartDate()));
                csa.setDate(4,dateUtil.toSqlDate(schoolYear.getSchoolYearEndDate()));
                csa.setDate(5, dateUtil.toSqlDate(schoolYear.getRegularEnrollmentStartDate()));
                csa.setDate(6,dateUtil.toSqlDate(schoolYear.getRegularEnrollmentEndDate()));
                csa.setDate(7,dateUtil.toSqlDate(schoolYear.getSummerEnrollmentStartDate()));
                csa.setDate(8,dateUtil.toSqlDate(schoolYear.getSummerEnrollmentEndDate()));
                csa.setDate(9, dateUtil.toSqlDate(schoolYear.getSummerClassStartDate()));
                csa.setDate(10, dateUtil.toSqlDate(schoolYear.getSummerClassEndDate()));
                csa.registerOutParameter(11, Types.INTEGER);
                csa.executeUpdate();
                int schoolYearId = csa.getInt(11);
                
                csb.setInt(1,schoolYearId);
                for(Object o : schoolYear.getQuarters().toArray()){
                    Quarter quarter = (Quarter)o;
                    csb.setInt(2, quarter.getQuarterNo());
                    csb.setDate(3, dateUtil.toSqlDate(quarter.getStartDate()));
                    csb.setDate(4, dateUtil.toSqlDate(quarter.getEndDate()));
                    csb.setDate(5, dateUtil.toSqlDate(quarter.getGradingOpenDate()));
                    csb.setDate(6,dateUtil.toSqlDate(quarter.getGradingDueDate()));
                    csb.executeUpdate();
                }
                con.commit();
                isAdded = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean update(SchoolYear schoolYear) {
        boolean isUpdated = false;
        String SQLa = "{CALL updateSchoolYear(?,?,?,?,?,?,?,?,?,?,?,?)}";
        String SQLb = "{CALL updateSchoolYearQuarterSchedule(?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                csa.setInt(1, schoolYear.getYearFrom());
                csa.setInt(2, schoolYear.getYearTo());
                csa.setDate(3, dateUtil.toSqlDate(schoolYear.getSchoolYearStartDate()));
                csa.setDate(4, dateUtil.toSqlDate(schoolYear.getSchoolYearEndDate()));
                csa.setDate(5, dateUtil.toSqlDate(schoolYear.getRegularEnrollmentStartDate()));
                csa.setDate(6, dateUtil.toSqlDate(schoolYear.getRegularEnrollmentEndDate()));
                csa.setDate(7, dateUtil.toSqlDate(schoolYear.getSummerEnrollmentStartDate()));
                csa.setDate(8, dateUtil.toSqlDate(schoolYear.getSummerEnrollmentEndDate()));
                csa.setDate(9, dateUtil.toSqlDate(schoolYear.getSummerClassStartDate()));
                csa.setDate(10, dateUtil.toSqlDate(schoolYear.getSummerClassEndDate()));
                csa.setInt(11, schoolYear.getSchoolYearId());
                csa.setInt(12, schoolYear.getIsActive() == true ? 1 : 0);
                csa.executeUpdate();
                
                csb.setInt(1, schoolYear.getSchoolYearId());
                for (Object o : schoolYear.getQuarters().toArray()) {
                    Quarter quarter = (Quarter) o;
                    csb.setInt(2, quarter.getQuarterNo());
                    csb.setDate(3, dateUtil.toSqlDate(quarter.getStartDate()));
                    csb.setDate(4, dateUtil.toSqlDate(quarter.getEndDate()));
                    csb.setDate(5, dateUtil.toSqlDate(quarter.getGradingOpenDate()));
                    csb.setDate(6, dateUtil.toSqlDate(quarter.getGradingDueDate()));
                    csb.executeUpdate();
                }
                con.commit();
                isUpdated = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    
    @Override
    public boolean open(SchoolYear schoolYear) {
        boolean isOpened = false;
        String SQL = "{CALL openSchoolYear(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, schoolYear.getSchoolYearId());
            cs.executeUpdate();
            isOpened = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isOpened;
    }

    @Override
    public boolean close(SchoolYear schoolYear) {
        boolean isClosed = false;
        String SQLa = "{CALL closeSchoolYear(?)}";
        String SQLb = "{CALL deactivateAllStudents()}";
        String SQLc = "{CALL markAllNewStudentsToOld()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);
                    CallableStatement csc = con.prepareCall(SQLc);) {
                csa.setInt(1, schoolYear.getSchoolYearId());
                csa.executeUpdate();
                csb.executeUpdate();
                csc.executeUpdate();
                con.commit();
                isClosed = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isClosed;
    }

    

    

    


}
