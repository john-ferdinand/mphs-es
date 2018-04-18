/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.period;

import java.util.Date;

/**
 * <p>
 * <h2>Period</h2>
 * <br>
 * is a class that represents a payment term's period. <br>
 * Payment Terms are :
 * <br>
 * 1.) Cash <br>
 * 2.) Semestral <br>
 * 3.) Quarterly <br>
 * 4.) Monthly <br>
 * <br>
 * For instance, 
 * <br>
 * * First Semester <br>
 * * Second Semester <br>
 * * First Quarter <br>
 * * Second Quarter <br>
 * * Third Quarter <br> and so on<br><br>... are all considered of type <b>Period</b>
 * <br>
 * </p>
 * 
 *
 * @author John Ferdinand Antonio
 */
public class Period {
    private int periodId;
    private String periodCode;
    private String periodName;
    private Date paymentDeadline;

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
    
}
