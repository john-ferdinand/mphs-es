
package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import utility.database.DBType;
import utility.database.DBUtil;
import dao.IOfficialReceipt;
import java.util.ArrayList;
import java.util.List;
import model.officialreceipt.OfficialReceipt;
import model.particulars.Particular;
import model.payment.Payment;
import model.schoolyear.SchoolYear;
import model.user.User;

/**
 *
 * @author Jordan
 */
public class OfficialReceiptDaoImpl implements IOfficialReceipt{

    @Override
    public String getNextAvailableOrNoForPaymentBySchoolYearId(int schoolyearId) {
        String OrNo = "";
        String SQL = "{CALL getNextAvailableORNOforPaymentBySchoolYearId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, schoolyearId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                   OrNo = rs.getString("ORNO");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OrNo;
    }

    @Override
    public int getIdByOrNo(int orNo) {
        int orNoID = 0;
        String SQL = "{CALL getOrIdByOrNo(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    orNoID = rs.getInt("or_no_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orNoID;
    }

    @Override
    public boolean markOrNoAsUsed(int orNo) {
        boolean isSuccessful = false;
        String SQL = "{CALL markOrNoAsUsed(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,orNo);
            cs.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public OfficialReceipt getOfficialReceiptByOrNo(int orNo) {
        OfficialReceipt OR = new OfficialReceipt();
        SchoolYear schoolYear = new SchoolYear();
        Payment payment = new Payment();
        List<Particular> particularList = new ArrayList<>();
        String SQLa = "{CALL getParticularsByOrNo(?)}"; //Particular object extends BalanceBreakDown
        String SQLb = "{CALL getTransactionInfoByOrNo(?)}"; //gets payment information under transaction_mt table
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement csa = con.prepareCall(SQLa);
                CallableStatement csb = con.prepareCall(SQLb);){
            csa.setInt(1,orNo);
            try(ResultSet rsa = csa.executeQuery();){
                while(rsa.next()){
                    Particular particular = new Particular();
                    particular.setId(rsa.getInt("particularId"));
                    particular.setName(rsa.getString("particular"));
                    particular.setAmountDue(rsa.getBigDecimal("particularAmount"));
                    particular.setAmountPaid(rsa.getBigDecimal("amountPaid"));
                    particularList.add(particular);
                }
            }
            csb.setInt(1, orNo);
            try(ResultSet rsb = csb.executeQuery();){
                while(rsb.next()){
                    User cashier = new User();
                    cashier.setUserID(rsb.getInt("user_id"));
                    cashier.setUsername(rsb.getString("username"));
                    cashier.setPassword(rsb.getString("password"));
                    cashier.setIsActive(rsb.getBoolean("isActive"));
                    cashier.setIsLocked(rsb.getBoolean("isLocked"));
                    cashier.setLastName(rsb.getString("lastname"));
                    cashier.setFirstName(rsb.getString("firstname"));
                    cashier.setMiddleName(rsb.getString("middlename"));
                    
                    schoolYear.setSchoolYearId(rsb.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rsb.getInt("yearFrom"));
                    schoolYear.setYearTo(rsb.getInt("yearTo"));
                    payment.setAmountReceived(rsb.getBigDecimal("amount_received"));
                    payment.setAmountCharged(rsb.getBigDecimal("amount_charged"));
                    payment.setChange(rsb.getBigDecimal("change_amount"));
                    payment.setDateOfPayment(rsb.getTimestamp("date_charged"));
                    payment.setOrNo(rsb.getInt("or_no_attached"));
                    payment.setCashier(cashier);
                }
            }
            payment.setParticulars(particularList);
            OR.setSchoolYear(schoolYear);
            OR.setPayment(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OR;
    }

    @Override
    public List<OfficialReceipt> getAllOfficialReceiptsByStudentIdandSchoolYearId(int studentId, int schoolyearId) {
        List<OfficialReceipt> officialReceiptList = new ArrayList<>();
        String SQL = "{CALL getAllOfficialReceiptsByStudentIdandSchoolYearId(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,studentId);
            cs.setInt(2, schoolyearId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    OfficialReceipt OR = new OfficialReceipt();
                    Payment payment = new Payment();
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearfrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    payment.setAmountReceived(rs.getBigDecimal("amount_received"));
                    payment.setAmountCharged(rs.getBigDecimal("amount_charged"));
                    payment.setChange(rs.getBigDecimal("change_amount"));
                    payment.setDateOfPayment(rs.getDate("date_charged"));
                    payment.setOrNo(rs.getInt("or_no_attached"));
                    System.out.println(rs.getInt("or_no_id"));
                    System.out.println(schoolYear.getYearFrom()+"-"+schoolYear.getYearTo());
                    OR.setId(rs.getInt("or_no_id"));
                    OR.setSchoolYear(schoolYear);
                    OR.setPayment(payment);
                    
                    officialReceiptList.add(OR);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return officialReceiptList;
    }
    
    
    @Override
    public List<OfficialReceipt> getAllOfficialReceiptsByStudentId(int studentId) {
        List<OfficialReceipt> officialReceiptList = new ArrayList<>();
        String SQL = "{CALL getAllOfficialReceiptsByStudentId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,studentId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    OfficialReceipt OR = new OfficialReceipt();
                    Payment payment = new Payment();
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearfrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    payment.setAmountReceived(rs.getBigDecimal("amount_received"));
                    payment.setAmountCharged(rs.getBigDecimal("amount_charged"));
                    payment.setChange(rs.getBigDecimal("change_amount"));
                    payment.setDateOfPayment(rs.getDate("date_charged"));
                    payment.setOrNo(rs.getInt("or_no_attached"));
                    System.out.println(rs.getInt("or_no_id"));
                    System.out.println(schoolYear.getYearFrom()+"-"+schoolYear.getYearTo());
                    OR.setId(rs.getInt("or_no_id"));
                    OR.setSchoolYear(schoolYear);
                    OR.setPayment(payment);
                    
                    officialReceiptList.add(OR);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return officialReceiptList;
    }
    
}
