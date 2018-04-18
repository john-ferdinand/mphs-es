package controller.payment;

import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.tuitionfee.Tuition;
import view.payment.Dialog_MakePayment;

/**
 *
 * @author Jordan
 */
public class Dialog_MakePayment_RemoveItemToPay implements ActionListener {

    private final Dialog_MakePayment view;

    public Dialog_MakePayment_RemoveItemToPay(Dialog_MakePayment view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        remove();
    }

    private void remove() {
        if (view.getJtblPaymentBreakDown().getRowCount() > 0 && view.getJtblPaymentBreakDown().getSelectedRow() > -1) {
            int selectedRow = view.getJtblPaymentBreakDown().getSelectedRow();
            DefaultTableModel tableModel = (DefaultTableModel) view.getJtblPaymentBreakDown().getModel();
            tableModel.removeRow(selectedRow);
//            view.getJtfPenalty().setText("");
        }
    }
}
