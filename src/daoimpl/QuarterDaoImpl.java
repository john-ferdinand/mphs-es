/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import constants.SchoolYearTable;
import constants.QuarterTable;
import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.schoolyear.SchoolYear;
import model.quarter.Quarter;
import dao.IQuarter;

/**
 *
 * @author Acer
 */
public class QuarterDaoImpl implements IQuarter{

    private SchoolYearDaoImpl schoolYearDaoImpl;
    
    public QuarterDaoImpl(){
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public Quarter getQuarterBy(int quarterNo, SchoolYear schoolYear) {
        Quarter quarter = new Quarter();
        String SQL = "{CALL getQuarterBy(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,quarterNo);
            cs.setInt(2,schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    quarter.setQuarterNo(rs.getInt("quarter_no"));
                    quarter.setStartDate(rs.getDate("start_date"));
                    quarter.setEndDate(rs.getDate("end_date"));
                    quarter.setGradingOpenDate(rs.getDate("grading_open_date"));
                    quarter.setGradingDueDate(rs.getDate("grading_due_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quarter;
    }
    
    
    @Override
    public Quarter getCurrentQuarterOf(SchoolYear schoolYear) {
        Quarter currentQuarter = new Quarter();
        String SQL = "{CALL getCurrentQuarterOf(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    currentQuarter.setQuarterNo(rs.getInt("quarter_no"));
                    currentQuarter.setStartDate(rs.getDate("start_date"));
                    currentQuarter.setEndDate(rs.getDate("end_date"));
                    currentQuarter.setGradingOpenDate(rs.getDate("grading_open_date"));
                    currentQuarter.setGradingDueDate(rs.getDate("grading_due_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentQuarter;
    }
    
}
