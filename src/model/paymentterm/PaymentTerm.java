package model.paymentterm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import model.period.Period;

public class PaymentTerm {
    private List<Period> periods; //Sem1, Sem2, Q1,Q1,Q3,Q4, Mo1, Mo2, Mo3....
    private int paymentTermId;
    private String paymentTermName;
    private BigDecimal penaltyAmount;
    private boolean isPenaltyActive;
    private int divisor;
    private int schoolYearId;
    private boolean isPaymentTermActive;
    private Date dateAdded;

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isIsPenaltyActive() {
        return isPenaltyActive;
    }

    public void setIsPenaltyActive(boolean isPenaltyActive) {
        this.isPenaltyActive = isPenaltyActive;
    }
    
    public boolean getIsPaymentTermActive() {
        return isPaymentTermActive;
    }

    public void setIsPaymentTermActive(boolean isPaymentTermActive) {
        this.isPaymentTermActive = isPaymentTermActive;
    }

    public int getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(int schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public int getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(int paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

}
