
package model.tuitionfee;

import java.math.BigDecimal;
import java.util.List;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.discount.Discount;
import model.payment.Payment;
import model.paymentterm.PaymentTerm;
import model.student.Student;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Tuition {

    private Payment payment;
    private PaymentTerm paymentTerm;
    private int schoolyearId;
    private boolean hasDiscount;
    private double totalFees;
    private boolean exists;
    private BigDecimal totalPaid;
    private BigDecimal remainingBalance;
    private List<Discount> discounts;
    
    private List<BalanceBreakDownFee> balanceBreakDownFees; //composed only of (basic + miscellaneous)/paymentterm
    
    private Student student;

    public int getSchoolYearId() {
        return schoolyearId;
    }

    public void setSchoolyearId(int schoolyearId) {
        this.schoolyearId = schoolyearId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<BalanceBreakDownFee> getBalanceBreakDownFees() {
        return balanceBreakDownFees;
    }

    public void setBalanceBreakDownFees(List<BalanceBreakDownFee> balanceBreakDownFees) {
        this.balanceBreakDownFees = balanceBreakDownFees;
    }

}
