package controller.payment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import view.payment.Dialog_MakePayment;

/**
 *
 * @author Jordan
 */
public class Dialog_MakePayment_PaymentBreakDown_TableModelListener implements TableModelListener {

    private final Dialog_MakePayment view;

    public Dialog_MakePayment_PaymentBreakDown_TableModelListener(Dialog_MakePayment view) {
        this.view = view;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        setPaymentSummary();
    }

    private void setPaymentSummary() {
        if (view.getJtblPaymentBreakDown().getRowCount() > 0) {
            BigDecimal sumOfFees = new BigDecimal(BigInteger.ZERO).setScale(2, RoundingMode.HALF_UP);
            if (view.getJtblPaymentBreakDown().getRowCount() > 0) {
                for (int row = 0; row < view.getJtblPaymentBreakDown().getRowCount(); row++) {
                    Double amount = Double.parseDouble(view.getJtblPaymentBreakDown().getValueAt(row, 1).toString().trim());
                    sumOfFees = sumOfFees.add(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
                }
            }
            view.getJtfSubtotal().setText("" + sumOfFees);
        }
    }

}
