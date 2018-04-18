/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.IPenalty;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.penalty.Penalty;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class PenaltyDaoImpl implements IPenalty{

    @Override
    public boolean hasExistingPenaltyFor(String balancebreakdownName, int schoolYearId, int registrationId) {
        boolean hasExistingPenalty = false;                       
        String SQL = "{CALL hasExistingPenaltyFor(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, balancebreakdownName.trim());
            cs.setInt(2, schoolYearId);
            cs.setInt(3, registrationId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    hasExistingPenalty = rs.getBoolean("hasExistingPenalty");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasExistingPenalty;
    }


    @Override
    public List<Penalty> getPenaltyBy(int orNo) {
        String SQL = "{CALL getPenaltyListByOrNo(?)}";
        List<Penalty> penaltyList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, orNo);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Penalty p = new Penalty();
                    p.setPenaltyId(rs.getInt("penalty_id"));
                    p.setPenaltyName(rs.getString("penalty_name"));
                    p.setPenaltyAmount(rs.getBigDecimal("penalty_amount"));
                    penaltyList.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return penaltyList;
    }
 
    
    
}
