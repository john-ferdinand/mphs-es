
package model.officialreceipt;

import java.util.Date;
import model.payment.Payment;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;

/**
 *
 * @author John Ferdinand Antonio
 */
public class OfficialReceipt {
    
    private int id;
    private int orNo;
    private boolean isUsed;
    private Date dateGenerated;
    private Payment payment;
    private SchoolYear schoolYear;
    private User cashier;

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrNo() {
        return orNo;
    }

    public void setOrNo(int orNo) {
        this.orNo = orNo;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    
}
