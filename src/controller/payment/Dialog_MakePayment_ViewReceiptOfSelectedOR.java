
package controller.payment;

import daoimpl.OfficialReceiptDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.officialreceipt.OfficialReceipt;
import model.student.Student;
import view.payment.Panel_Payment;
import view.receipt.Dialog_Receipt;

/**
 *
 * @author Jordan
 */
public class Dialog_MakePayment_ViewReceiptOfSelectedOR implements ActionListener{

    private final Panel_Payment panelPayment;
    
    public Dialog_MakePayment_ViewReceiptOfSelectedOR(Panel_Payment panelPayment) {
        this.panelPayment = panelPayment;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        displayReceipt();
    }
    
    private void displayReceipt(){
        JTable t = panelPayment.getJtblReceiptsMasterList();
        if(t.getSelectedRow() > -1){
            int selectedRow = t.getSelectedRow();
            Student student = panelPayment.getStudent();
            int orNo = Integer.parseInt(t.getValueAt(selectedRow, 0).toString().toString());
            OfficialReceiptDaoImpl officialReceiptDaoImpl = new OfficialReceiptDaoImpl();
            OfficialReceipt officialReceipt = new OfficialReceipt();
            officialReceipt = officialReceiptDaoImpl.getOfficialReceiptByOrNo(orNo);
            
            Dialog_Receipt dialog = new Dialog_Receipt(student,officialReceipt);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
}
