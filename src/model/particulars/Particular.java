package model.particulars;

import java.math.BigDecimal;
import model.balancebreakdownfee.BalanceBreakDownFee;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Particular extends BalanceBreakDownFee{
    private BigDecimal amountPaid;

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}
