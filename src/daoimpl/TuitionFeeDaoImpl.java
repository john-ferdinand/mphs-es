
package daoimpl;

import dao.ITuitionFee;
import java.math.BigDecimal;
import java.math.BigInteger;
import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.discount.Discount;
import model.particulars.Particular;
import model.payment.Payment;
import model.paymentterm.PaymentTerm;
import model.penalty.Penalty;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.tuitionfee.Tuition;
import utility.date.DateUtil;

/**
 *
 * @author John Ferdinand Antonio
 */
public class TuitionFeeDaoImpl implements ITuitionFee {
    
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    
    public TuitionFeeDaoImpl (){
        this.gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }

    @Override
    public BigDecimal getTuitionBalanceOf(Student student, SchoolYear schoolYear) {
        String SQL = "{CALL getTuitionBalanceOf(?,?)}";
        BigDecimal balance = new BigDecimal(BigInteger.ZERO);
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, student.getStudentId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    balance = rs.getBigDecimal("tuitionBalance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    
    @Override
    public boolean add(Tuition tuitionFee) {
        boolean isAdded = false;
        DateUtil dateUtil = new DateUtil();
        String SQLa = "{CALL addBalanceBreakDownFee(?,?,?,?,?,?,?,?)}";
        PaymentTerm paymentTerm = tuitionFee.getPaymentTerm();
        Student student = tuitionFee.getStudent();
        int schoolYearId = tuitionFee.getSchoolYearId();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);) {
                for (BalanceBreakDownFee b : tuitionFee.getBalanceBreakDownFees()) {
                    csa.setString(1, b.getName().trim());
                    csa.setBigDecimal(2, b.getAmountDue());
                    csa.setDate(3, dateUtil.toSqlDate(b.getDeadline()));
                    csa.setString(4, b.getCategory().trim());
                    csa.setInt(5, student.getStudentId());
                    csa.setInt(6, paymentTerm.getPaymentTermId());
                    csa.setInt(7, schoolYearId);
                    csa.registerOutParameter(8, Types.INTEGER);
                    csa.executeUpdate();
                    int balancebreakdownId = csa.getInt(8);
                    System.out.println(b.getName()+" added.");
                }
                con.commit();
                isAdded = true;
            } catch (Exception e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean hasTuitionBalance(int studentId) {
        boolean hasTuitionBalance = false;
        String SQL = "{CALL hasTuitionBalance(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1,studentId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    hasTuitionBalance = rs.getBoolean("hasTuitionBalance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasTuitionBalance;
    }

    @Override
    public boolean hasTuitionOn(int studentId, int schoolYearId) {
        boolean has = false;
        String SQL = "{CALL hasTuitionOnSchoolYear(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, studentId);
            cs.setInt(2, schoolYearId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    has = rs.getBoolean("hasTuitionOnSchoolYear");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return has;
    }
    
    
    
    @Override
    public Tuition getBy(int studentId, int schoolyearId) {
        Tuition tuitionFee = new Tuition();
        PaymentTerm paymentTerm = new PaymentTerm();
        String SQLa = "{CALL getTuitionByStudentIdAndSchoolYearId(?,?)}";
        String SQLb = "{CALL getTuitionTotalPaidAndRemainingBalance(?,?)}";
        String SQLc = "{CALL getTuitionPaymentTermByStudentIdAndSchoolYearId(?,?)}";
        String SQLd = "{CALL getStudentDiscountBy(?,?)}";
        List<BalanceBreakDownFee> bbFeeList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement csa = con.prepareCall(SQLa);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);
                CallableStatement csd = con.prepareCall(SQLd);){
            csa.setInt(1,studentId);
            csa.setInt(2,schoolyearId);
            csb.setInt(1, studentId);
            csb.setInt(2, schoolyearId);
            csc.setInt(1, studentId);
            csc.setInt(2,schoolyearId);
            csd.setInt(1, studentId);
            csd.setInt(2, schoolyearId);
            try(ResultSet rs = csa.executeQuery();){
                while(rs.next()){
                    BalanceBreakDownFee bb = new BalanceBreakDownFee();
                    bb.setId(rs.getInt("balancebreakdown_id"));
                    bb.setName(rs.getString("balancebreakdown_name").trim());
                    bb.setAmountDue(rs.getBigDecimal("balancebreakdown_amount"));
                    bb.setBalance(rs.getBigDecimal("balance"));
                    bb.setIsPaid(rs.getBoolean("isPaid"));
                    bb.setHasPenalty(rs.getBoolean("hasPenalty"));
                    bb.setIsPastDueDate(rs.getBoolean("isPastDueDate"));
                    bb.setDeadline(rs.getDate("due_date"));
                    String category = rs.getString("category").equalsIgnoreCase("B")? "Balance" :
                            rs.getString("category").equalsIgnoreCase("D")? "Downpayment" :
                            rs.getString("category").equalsIgnoreCase("O")? "Other" : 
                            rs.getString("category").equalsIgnoreCase("S")? "Summer" :"";
                    bb.setCategory(category);
                    bb.setSchoolyearId(rs.getInt("schoolyear_id"));
                    bbFeeList.add(bb);
                }
            }
            
            try(ResultSet rs = csb.executeQuery();){
                while(rs.next()){
//                    tuitionFee.setTotalPaid(rs.getBigDecimal("totalPaid"));
//                    tuitionFee.setRemainingBalance(rs.getBigDecimal("remainingbalance"));
                }
            }
            
            try(ResultSet rs = csc.executeQuery();){
                while(rs.next()){
                    paymentTerm.setPaymentTermId(rs.getInt("paymentterm_id"));
                    paymentTerm.setPaymentTermName(rs.getString("paymentterm_name"));
                    paymentTerm.setIsPaymentTermActive(rs.getBoolean("isPaymentTermActive"));
                    paymentTerm.setDivisor(rs.getInt("divisor"));
                    paymentTerm.setPenaltyAmount(rs.getBigDecimal("penalty_amount"));
                }
            }
            
            List<Discount> discounts = new ArrayList<>();
            try(ResultSet rs = csd.executeQuery();){
                while(rs.next()){
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    
                    Discount discount = new Discount();
                    discount.setDiscountID(rs.getInt("discount_id"));
                    discount.setDiscountName(rs.getString("discount_name"));
                    discount.setPercent(rs.getInt("percentage"));
                    discount.setDescription(rs.getString("description"));
                    discount.setDateCreated(rs.getDate("date_created"));
                    discount.setDateApplied(rs.getDate("date_applied"));
                    discount.setProvision(rs.getString("provision"));
                    discount.setAmount(rs.getBigDecimal("discount_amount"));
                    discount.setSchoolYear(schoolYear);
                    discounts.add(discount);
                }
            }
            tuitionFee.setDiscounts(discounts);
            tuitionFee.setSchoolyearId(schoolyearId);
            tuitionFee.setBalanceBreakDownFees(bbFeeList);
            tuitionFee.setPaymentTerm(paymentTerm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tuitionFee;
    }

    @Override
    public int getBalanceBreakDownId(String balancebreakdownName, int studentId, int schoolyearId) {
        int balancebreakdownId = 0;
        String SQL = "{CALL getBalanceBreakDownId(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, balancebreakdownName.trim());
            cs.setInt(2, studentId);
            cs.setInt(3, schoolyearId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    balancebreakdownId = rs.getInt("balancebreakdown_id");
                    System.out.println("BalanceBreakDown Id: "+balancebreakdownId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balancebreakdownId;
    }

    @Override
    public boolean payNewSchoolYear(Tuition tuition) {
        boolean isSuccessful = false;
        DateUtil dateUtil = new DateUtil();
        int studentId = tuition.getStudent().getStudentId();
        int schoolYearId = tuition.getSchoolYearId();
        int gradeLevelId = gradeLevelDaoImpl.getCurrentGradeLevelOf(tuition.getStudent()).getGradeLevelId();
        Payment payment = tuition.getPayment();
        PaymentTerm paymentTerm = tuition.getPaymentTerm();
        String sqlA = "{CALL addBalanceBreakDownFee(?,?,?,?,?,?,?,?)}";
        String sqlB = "{CALL addTransaction(?,?,?,?,?,?)}";
        String sqlC = "{CALL addTransactionBalanceBreakDown(?,?,?)}";
        String sqlD = "{CALL markOrNoAsUsed(?)}";
        String sqlE = "{CALL addEnrollment(?,?,?,?,?)}";
        String sqlF = "{CALL activateStudent(?)}";
        String sqlG = "{CALL addStudentDiscounts(?,?,?,?,?)}";
        String sqlH = "{CALL addPenalty(?,?,?,?,?)}";
        String sqlI = "{CALL addTransactionPenalty(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement cs_addBalanceBreakDownFee = con.prepareCall(sqlA);
                    CallableStatement cs_addTransaction = con.prepareCall(sqlB);
                    CallableStatement cs_addTransactionBalanceBreakDown = con.prepareCall(sqlC);
                    CallableStatement cs_markOrNoAsUsed = con.prepareCall(sqlD);
                    CallableStatement cs_addEnrollment = con.prepareCall(sqlE);
                    CallableStatement cs_activateStudent = con.prepareCall(sqlF);
                    CallableStatement cs_addStudentDiscounts = con.prepareCall(sqlG);
                    CallableStatement cs_addPenalty = con.prepareCall(sqlH);
                    CallableStatement cs_addTransactionPenalty = con.prepareCall(sqlI);){

                
                Map<Integer,String> breakdownFeeId_and_breakdownFeeName_pair = new HashMap<>();
                for (BalanceBreakDownFee bbFee : tuition.getBalanceBreakDownFees()) {
                    cs_addBalanceBreakDownFee.setString(1, bbFee.getName().trim());
                    cs_addBalanceBreakDownFee.setBigDecimal(2, bbFee.getAmountDue());
                    cs_addBalanceBreakDownFee.setDate(3, dateUtil.toSqlDate(bbFee.getDeadline()));
                    cs_addBalanceBreakDownFee.setString(4, bbFee.getCategory().trim());
                    cs_addBalanceBreakDownFee.setInt(5, studentId);
                    cs_addBalanceBreakDownFee.setInt(6, paymentTerm.getPaymentTermId());
                    cs_addBalanceBreakDownFee.setInt(7, schoolYearId);
                    cs_addBalanceBreakDownFee.registerOutParameter(8, Types.INTEGER);
                    cs_addBalanceBreakDownFee.executeUpdate();
                    int balancebreakdownId = cs_addBalanceBreakDownFee.getInt(8);
                    breakdownFeeId_and_breakdownFeeName_pair.put(balancebreakdownId, bbFee.getName());
                }
                
                cs_addTransaction.setInt(1, studentId);
                cs_addTransaction.setBigDecimal(2, payment.getAmountReceived());
                cs_addTransaction.setBigDecimal(3, payment.getAmountCharged());
                cs_addTransaction.setInt(4, payment.getOrNoAttached());
                cs_addTransaction.setInt(5, payment.getCashier().getUserId());
                cs_addTransaction.registerOutParameter(6, Types.INTEGER);
                cs_addTransaction.executeUpdate();
                int transactionId = cs_addTransaction.getInt(6);
                
                for (Particular p : payment.getParticulars()) {
                    int particularId = 0;
                    for(Map.Entry<Integer,String> entry : breakdownFeeId_and_breakdownFeeName_pair.entrySet()){
                        if(entry.getValue().equalsIgnoreCase(p.getName().trim())){
                            particularId = entry.getKey();
                            break;
                        }
                    }
                    cs_addTransactionBalanceBreakDown.setInt(1, transactionId);
                    cs_addTransactionBalanceBreakDown.setInt(2, particularId);
                    cs_addTransactionBalanceBreakDown.setBigDecimal(3, p.getAmountPaid());
                    cs_addTransactionBalanceBreakDown.executeUpdate();
                }
                
                cs_markOrNoAsUsed.setInt(1, payment.getOrNoAttached());
                cs_markOrNoAsUsed.executeUpdate();
                
                cs_addEnrollment.setInt(1, schoolYearId);
                cs_addEnrollment.setInt(2, studentId);
                cs_addEnrollment.setString(3, tuition.getStudent().getEnrollment().getEnrollmentType().trim());
                cs_addEnrollment.setInt(4, gradeLevelId);
                cs_addEnrollment.registerOutParameter(5, Types.INTEGER);
                cs_addEnrollment.executeUpdate();
                int enrollmentId = cs_addEnrollment.getInt(5);
                
                cs_activateStudent.setInt(1, studentId);
                cs_activateStudent.executeUpdate();
                for(Discount d : tuition.getDiscounts()){
                    cs_addStudentDiscounts.setInt(1, studentId);
                    cs_addStudentDiscounts.setInt(2, d.getDiscountID());
                    cs_addStudentDiscounts.setInt(3, schoolYearId);
                    cs_addStudentDiscounts.setInt(4, d.getCreatedBy().getUserId());
                    cs_addStudentDiscounts.setBigDecimal(5, d.getAmount());
                    cs_addStudentDiscounts.executeUpdate();
                }
                
                for (Particular p : payment.getParticulars()) {
                    if (p.getPenalty() != null) {
                        int particularId = 0;
                        for (Map.Entry<Integer, String> entry : breakdownFeeId_and_breakdownFeeName_pair.entrySet()) {
                            if (entry.getValue().equalsIgnoreCase(p.getName().trim())) {
                                particularId = entry.getKey();
                                break;
                            }
                        }
                        cs_addPenalty.setString(1, p.getPenalty().getPenaltyName());
                        cs_addPenalty.setBigDecimal(2, p.getPenalty().getPenaltyAmount());
                        cs_addPenalty.setInt(3, particularId);
                        cs_addPenalty.setInt(4,tuition.getStudent().getRegistration().getRegistrationId());
                        cs_addPenalty.registerOutParameter(5, Types.INTEGER);
                        cs_addPenalty.executeUpdate();
                        int penaltyId = cs_addPenalty.getInt(5);
                        cs_addTransactionPenalty.setInt(1, transactionId);
                        cs_addTransactionPenalty.setInt(2, penaltyId);
                        cs_addTransactionPenalty.setBigDecimal(3, p.getPenalty().getAmountPaid());
                        cs_addTransactionPenalty.executeUpdate();
                    }
                }
                con.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    
    @Override
    public boolean payBalanceOf(Tuition tuitionFee) {
        boolean isSuccessful = false;
        Payment payment = tuitionFee.getPayment();
        String SQLa = "{CALL addTransaction(?,?,?,?,?,?)}";
        String SQLb = "{CALL addTransactionBalanceBreakDown(?,?,?)}";
        String SQLc = "{CALL markOrNoAsUsed(?)}";
        String SQLd = "{CALL addPenalty(?,?,?,?,?)}";
        String SQLe = "{CALL addTransactionPenalty(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);
                    CallableStatement csc = con.prepareCall(SQLc);
                    CallableStatement cs_addPenalty = con.prepareCall(SQLd);
                    CallableStatement cs_addTransactionPenalty = con.prepareCall(SQLe);) {
                csa.setInt(1, tuitionFee.getStudent().getStudentId());
                csa.setBigDecimal(2, payment.getAmountReceived());
                csa.setBigDecimal(3, payment.getAmountCharged());
                csa.setInt(4, payment.getOrNoAttached());
                csa.setInt(5, payment.getCashier().getUserId());
                csa.registerOutParameter(6, Types.INTEGER);
                csa.executeUpdate();
                int transactionId = csa.getInt(6);
    
                int studentId = tuitionFee.getStudent().getStudentId();
                int schoolYearId = tuitionFee.getSchoolYearId();
                
                for (Particular p : payment.getParticulars()) {
                    int balancebreakdownId = getBalanceBreakDownId(p.getName().trim(), studentId, schoolYearId);
                    csb.setInt(1,transactionId);
                    csb.setInt(2,balancebreakdownId);
                    csb.setBigDecimal(3, p.getAmountPaid());
                    csb.executeUpdate();
                }
                
                csc.setInt(1, payment.getOrNoAttached());
                csc.executeUpdate();
                
                for (Particular p : payment.getParticulars()) {
                    if (p.getPenalty() != null) {
                        int balancebreakdownId = getBalanceBreakDownId(p.getName().trim(), studentId, schoolYearId);
                        cs_addPenalty.setString(1, p.getPenalty().getPenaltyName());
                        cs_addPenalty.setBigDecimal(2, p.getPenalty().getPenaltyAmount());
                        cs_addPenalty.setInt(3, balancebreakdownId);
                        cs_addPenalty.setInt(4,tuitionFee.getStudent().getRegistration().getRegistrationId());
                        cs_addPenalty.registerOutParameter(5, Types.INTEGER);
                        cs_addPenalty.executeUpdate();
                        int penaltyId = cs_addPenalty.getInt(5);
                        cs_addTransactionPenalty.setInt(1, transactionId);
                        cs_addTransactionPenalty.setInt(2, penaltyId);
                        cs_addTransactionPenalty.setBigDecimal(3, p.getPenalty().getAmountPaid());
                        cs_addTransactionPenalty.executeUpdate();
                    }
                }
                con.commit();
                isSuccessful = true;
            } catch (Exception e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    
    /**
     * Use this method only when processing payment of a student applicant for the first time.
     * This applies to ALL student applicants who are not yet assigned with student no and inactive.<br/>
     * This is used for processing payment for NEW and TRANSFEREE students who don't have student no yet.
     * This method performs the following routines:
     * <br/><br/>
     * 1.) Adds a new Student record (Generates student no) See <code>student_mt</code> table.<br/><br/>
     * 2.) Adds student to school year record. See <code>schoolyear_student_lt</code> table.<br/><br/>
     * 3.) Adds Tuition balance breakdown fees and assigns them to student. See <code>balancebreakdownfee</code> table.<br/><br/>
     * 4.) Adds Transaction record for first time payment of student. (Usually the down payment or full payment) See <code>transaction_mt</code> and <code>transaction_balancebreakdownfee</code> table.<br/><br/>
     * 5.) Updates OrNo attached to payment information as "used" See <code>or_mt</code> table.<br/><br/>
     * 6.) Adds a new Enrollment record. See <code>enrollment_mt</code> table.<br/><br/>
     * 7.) Activates the student. See <code>student_mt</code> table, <code>is_active</code> column.<br/><br/>
     * @param tuition
     * @return 
     */
    
    @Override
    public boolean payPrimary(Tuition tuition) {
        boolean isSuccessful = false;
        DateUtil dateUtil = new DateUtil();
        int schoolYearId = tuition.getSchoolYearId();
        int gradeLevelId = gradeLevelDaoImpl.getId(tuition.getStudent().getRegistration().getGradeLevelNo());
        Payment payment = tuition.getPayment();
        PaymentTerm paymentTerm = tuition.getPaymentTerm();
        String sqlA = "{CALL addStudent(?,?,?)}";
        String sqlB = "{CALL addSchoolYearStudent(?,?,?)}";
        String sqlC = "{CALL addBalanceBreakDownFee(?,?,?,?,?,?,?,?)}";
        String sqlD = "{CALL addTransaction(?,?,?,?,?,?)}";
        String sqlE = "{CALL addTransactionBalanceBreakDown(?,?,?)}";
        String sqlF = "{CALL markOrNoAsUsed(?)}";
        String sqlG = "{CALL addEnrollment(?,?,?,?,?)}";
        String SQLH = "{CALL activateStudent(?)}";
        String SQLI = "{CALL addStudentDiscounts(?,?,?,?,?)}";
        String SQLJ = "{CALL addPenalty(?,?,?,?,?)}";
        String SQLK = "{CALL addTransactionPenalty(?,?,?)}";
        
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement cs_addStudent = con.prepareCall(sqlA);
                    CallableStatement cs_addSchoolYearStudent = con.prepareCall(sqlB);
                    CallableStatement cs_addBalanceBreakDownFee = con.prepareCall(sqlC);
                    CallableStatement cs_addTransaction = con.prepareCall(sqlD);
                    CallableStatement cs_addTransactionBalanceBreakDown = con.prepareCall(sqlE);
                    CallableStatement cs_markOrNoAsUsed = con.prepareCall(sqlF);
                    CallableStatement cs_addEnrollment = con.prepareCall(sqlG);
                    CallableStatement cs_activateStudent = con.prepareCall(SQLH);
                    CallableStatement cs_addStudentDiscounts = con.prepareCall(SQLI);
                    CallableStatement cs_addPenalty = con.prepareCall(SQLJ);
                    CallableStatement cs_addTransactionPenalty = con.prepareCall(SQLK);){
                cs_addStudent.setInt(1, tuition.getStudent().getAdmission().getAdmissionId());
                cs_addStudent.setString(2, tuition.getStudent().getRegistration().getStudentType());
                cs_addStudent.registerOutParameter(3, Types.INTEGER);
                cs_addStudent.executeUpdate();
                int studentId = cs_addStudent.getInt(3);
                
                cs_addSchoolYearStudent.setInt(1,schoolYearId);
                cs_addSchoolYearStudent.setInt(2, studentId);
                cs_addSchoolYearStudent.setInt(3, gradeLevelId);
                cs_addSchoolYearStudent.executeUpdate();
                
                Map<Integer,String> breakdownFeeId_and_breakdownFeeName_pair = new HashMap<>();
                for (BalanceBreakDownFee bbFee : tuition.getBalanceBreakDownFees()) {
                    cs_addBalanceBreakDownFee.setString(1, bbFee.getName().trim());
                    cs_addBalanceBreakDownFee.setBigDecimal(2, bbFee.getAmountDue());
                    cs_addBalanceBreakDownFee.setDate(3, dateUtil.toSqlDate(bbFee.getDeadline()));
                    cs_addBalanceBreakDownFee.setString(4, bbFee.getCategory().trim());
                    cs_addBalanceBreakDownFee.setInt(5, studentId);
                    cs_addBalanceBreakDownFee.setInt(6, paymentTerm.getPaymentTermId());
                    cs_addBalanceBreakDownFee.setInt(7, schoolYearId);
                    cs_addBalanceBreakDownFee.registerOutParameter(8, Types.INTEGER);
                    cs_addBalanceBreakDownFee.executeUpdate();
                    int balancebreakdownId = cs_addBalanceBreakDownFee.getInt(8);
                    breakdownFeeId_and_breakdownFeeName_pair.put(balancebreakdownId, bbFee.getName());
                }
                
                cs_addTransaction.setInt(1, studentId);
                cs_addTransaction.setBigDecimal(2, payment.getAmountReceived());
                cs_addTransaction.setBigDecimal(3, payment.getAmountCharged());
                cs_addTransaction.setInt(4, payment.getOrNoAttached());
                cs_addTransaction.setInt(5, payment.getCashier().getUserId());
                cs_addTransaction.registerOutParameter(6, Types.INTEGER);
                cs_addTransaction.executeUpdate();
                int transactionId = cs_addTransaction.getInt(6);
                
                for (Particular p : payment.getParticulars()) {
                    int particularId = 0;
                    for(Map.Entry<Integer,String> entry : breakdownFeeId_and_breakdownFeeName_pair.entrySet()){
                        if(entry.getValue().equalsIgnoreCase(p.getName().trim())){
                            particularId = entry.getKey();
                            break;
                        }
                    }
                    cs_addTransactionBalanceBreakDown.setInt(1, transactionId);
                    cs_addTransactionBalanceBreakDown.setInt(2, particularId);
                    cs_addTransactionBalanceBreakDown.setBigDecimal(3, p.getAmountPaid());
                    cs_addTransactionBalanceBreakDown.executeUpdate();
                }
                
                cs_markOrNoAsUsed.setInt(1, payment.getOrNoAttached());
                cs_markOrNoAsUsed.executeUpdate();
                
                cs_addEnrollment.setInt(1, schoolYearId);
                cs_addEnrollment.setInt(2, studentId);
                cs_addEnrollment.setString(3, tuition.getStudent().getEnrollment().getEnrollmentType().trim());
                cs_addEnrollment.setInt(4, gradeLevelId);
                cs_addEnrollment.registerOutParameter(5, Types.INTEGER);
                cs_addEnrollment.executeUpdate();
                int enrollmentId = cs_addEnrollment.getInt(5);
                
                cs_activateStudent.setInt(1, studentId);
                cs_activateStudent.executeUpdate();
                for(Discount d : tuition.getDiscounts()){
                    cs_addStudentDiscounts.setInt(1, studentId);
                    cs_addStudentDiscounts.setInt(2, d.getDiscountID());
                    cs_addStudentDiscounts.setInt(3, schoolYearId);
                    cs_addStudentDiscounts.setInt(4, d.getCreatedBy().getUserId());
                    cs_addStudentDiscounts.setBigDecimal(5, d.getAmount());
                    cs_addStudentDiscounts.executeUpdate();
                }
                

                for (Particular p : payment.getParticulars()) {
                    if (p.getPenalty() != null) {
                        int particularId = 0;
                        for (Map.Entry<Integer, String> entry : breakdownFeeId_and_breakdownFeeName_pair.entrySet()) {
                            if (entry.getValue().equalsIgnoreCase(p.getName().trim())) {
                                particularId = entry.getKey();
                                break;
                            }
                        }
                        cs_addPenalty.setString(1, p.getPenalty().getPenaltyName());
                        cs_addPenalty.setBigDecimal(2, p.getPenalty().getPenaltyAmount());
                        cs_addPenalty.setInt(3, particularId);
                        cs_addPenalty.setInt(4,tuition.getStudent().getRegistration().getRegistrationId());
                        cs_addPenalty.registerOutParameter(5, Types.INTEGER);
                        cs_addPenalty.executeUpdate();
                        int penaltyId = cs_addPenalty.getInt(5);
                        cs_addTransactionPenalty.setInt(1, transactionId);
                        cs_addTransactionPenalty.setInt(2, penaltyId);
                        cs_addTransactionPenalty.setBigDecimal(3, p.getPenalty().getAmountPaid());
                        cs_addTransactionPenalty.executeUpdate();
                    }
                }
                con.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean paySummerFees(Tuition tuition) {
        boolean isSuccessful = false;
        DateUtil dateUtil = new DateUtil();
        int gradeLevelId = gradeLevelDaoImpl.getId(tuition.getStudent().getRegistration().getGradeLevelNo());
        String sqlA = "{CALL addBalanceBreakDownFee(?,?,?,?,?,?,?,?)}";
        String sqlB = "{CALL addTransaction(?,?,?,?,?,?)}";
        String sqlC = "{CALL addTransactionBalanceBreakDown(?,?,?)}";
        String sqlD = "{CALL markOrNoAsUsed(?)}";
        String sqlE = "{CALL addEnrollment(?,?,?,?,?)}";
        String sqlF = "{CALL markSummerStudentAsEnrolled(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement cs_addBalanceBreakDownFee = con.prepareCall(sqlA);
                    CallableStatement cs_addTransaction = con.prepareCall(sqlB);
                    CallableStatement cs_addTransactionBalanceBreakDown = con.prepareCall(sqlC);
                    CallableStatement cs_markOrNoAsUsed = con.prepareCall(sqlD);
                    CallableStatement cs_addEnrollment = con.prepareCall(sqlE);
                    CallableStatement cs_markSummerStudentAsEnrolled = con.prepareCall(sqlF);){
                
                Map<Integer,String> breakdownFeeId_and_breakdownFeeName_pair = new HashMap<>();
                for (BalanceBreakDownFee bbFee : tuition.getBalanceBreakDownFees()) {
                    cs_addBalanceBreakDownFee.setString(1, bbFee.getName().trim());
                    cs_addBalanceBreakDownFee.setBigDecimal(2, bbFee.getAmountDue());
                    cs_addBalanceBreakDownFee.setDate(3, dateUtil.toSqlDate(bbFee.getDeadline()));
                    cs_addBalanceBreakDownFee.setString(4, bbFee.getCategory().trim());
                    cs_addBalanceBreakDownFee.setInt(5, tuition.getStudent().getStudentId());
                    cs_addBalanceBreakDownFee.setInt(6, tuition.getPaymentTerm().getPaymentTermId());
                    cs_addBalanceBreakDownFee.setInt(7, tuition.getSchoolYearId());
                    cs_addBalanceBreakDownFee.registerOutParameter(8, Types.INTEGER);
                    cs_addBalanceBreakDownFee.executeUpdate();
                    int balancebreakdownId = cs_addBalanceBreakDownFee.getInt(8);
                    breakdownFeeId_and_breakdownFeeName_pair.put(balancebreakdownId, bbFee.getName());
                }
                
                cs_addTransaction.setInt(1, tuition.getStudent().getStudentId());
                cs_addTransaction.setBigDecimal(2, tuition.getPayment().getAmountReceived());
                cs_addTransaction.setBigDecimal(3, tuition.getPayment().getAmountCharged());
                cs_addTransaction.setInt(4, tuition.getPayment().getOrNoAttached());
                cs_addTransaction.setInt(5, tuition.getPayment().getCashier().getUserId());
                cs_addTransaction.registerOutParameter(6, Types.INTEGER);
                cs_addTransaction.executeUpdate();
                int transactionId = cs_addTransaction.getInt(6);
                
                for (Particular p : tuition.getPayment().getParticulars()) {
                    int particularId = 0;
                    for(Map.Entry<Integer,String> entry : breakdownFeeId_and_breakdownFeeName_pair.entrySet()){
                        if(entry.getValue().equalsIgnoreCase(p.getName().trim())){
                            particularId = entry.getKey();
                            break;
                        }
                    }
                    cs_addTransactionBalanceBreakDown.setInt(1, transactionId);
                    cs_addTransactionBalanceBreakDown.setInt(2, particularId);
                    cs_addTransactionBalanceBreakDown.setBigDecimal(3, p.getAmountPaid());
                    cs_addTransactionBalanceBreakDown.executeUpdate();
                }
                
                cs_markOrNoAsUsed.setInt(1, tuition.getPayment().getOrNoAttached());
                cs_markOrNoAsUsed.executeUpdate();
                
                cs_addEnrollment.setInt(1, tuition.getSchoolYearId());
                cs_addEnrollment.setInt(2, tuition.getStudent().getStudentId());
                cs_addEnrollment.setString(3, "S");
                cs_addEnrollment.setInt(4, gradeLevelId);
                cs_addEnrollment.registerOutParameter(5, Types.INTEGER);
                cs_addEnrollment.executeUpdate();
                int enrollmentId = cs_addEnrollment.getInt(5);
                
                cs_markSummerStudentAsEnrolled.setInt(1, tuition.getStudent().getStudentId());
                cs_markSummerStudentAsEnrolled.setInt(2, tuition.getSchoolYearId());
                cs_markSummerStudentAsEnrolled.executeUpdate();
                
                con.commit();
                isSuccessful = true;
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
    public SchoolYear getSchoolYearWithTuitionBalanceFor(int studentId) {
        SchoolYear schoolYear = new SchoolYear();
        String SQL = "{CALL getSchoolYearWithTuitionBalanceFor(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, studentId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    schoolYear.setSchoolYearId(rs.getInt("schoolyear_id"));
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    schoolYear.setIsActive(rs.getBoolean("isActive"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolYear;
    }
    
}
