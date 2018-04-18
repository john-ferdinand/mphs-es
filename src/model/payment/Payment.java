package model.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import model.particulars.Particular;
import model.penalty.Penalty;
import model.user.User;

/**
 *
 * @author Jordan
 */
public class Payment {

    private BigDecimal amountReceived;
    private BigDecimal amountCharged;
    private BigDecimal change;
    private Date dateCharged;
    private int orNoAttached;
    private List<Particular> particulars;
    private User cashier;

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public BigDecimal getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(BigDecimal amountCharged) {
        this.amountCharged = amountCharged;
    }
    
    public List<Particular> getParticulars() {
        return particulars;
    }

    public void setParticulars(List<Particular> particulars) {
        this.particulars = particulars;
    }

    public int getOrNoAttached() {
        return orNoAttached;
    }

    public void setOrNo(int orNo) {
        this.orNoAttached = orNo;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Date getDateOfPayment() {
        return dateCharged;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateCharged = dateOfPayment;
    }

}
