
package controller.payment;

import daoimpl.OfficialReceiptDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.particulars.Particular;
import model.payment.Payment;
import model.paymentterm.PaymentTerm;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.tuitionfee.Tuition;
import model.user.User;
import view.payment.Dialog_AddSummerFee;

/**
 *
 * @author Jordan
 */
public class Controller_Summer_Pay_JButton implements ActionListener{
    
    private final Dialog_AddSummerFee dialog;
    private final Student student;
    private final User user;
    private final SchoolYear currentSchoolYear;
    private final PaymentTermDaoImpl paymentTermDaoImpl;
    private final OfficialReceiptDaoImpl officialReceiptDaoImpl;
    private final TuitionFeeDaoImpl tuitionFeeDaoImpll;
    
    public Controller_Summer_Pay_JButton(Dialog_AddSummerFee dialog, Student student, SchoolYear currentSchoolYear,User user) {
        this.dialog = dialog;
        this.user = user;
        this.student = student;
        this.currentSchoolYear = currentSchoolYear;
        this.paymentTermDaoImpl = new PaymentTermDaoImpl();
        this.officialReceiptDaoImpl = new OfficialReceiptDaoImpl();
        this.tuitionFeeDaoImpll = new TuitionFeeDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dialog.getJbtnPaySummer()) {
            JTable table = dialog.getJtblSummerFees();
            Tuition tuition = new Tuition();
            tuition.setStudent(student);
            tuition.setSchoolyearId(currentSchoolYear.getSchoolYearId());
            tuition.setBalanceBreakDownFees(balanceBreakDownFeeList(table));
            tuition.setPayment(payment());
            tuition.setPaymentTerm(paymentTerm());
            int choice = JOptionPane.showConfirmDialog(null, "Process payment? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                boolean isSuccessful = tuitionFeeDaoImpll.paySummerFees(tuition);
                if (isSuccessful) {
                    JOptionPane.showMessageDialog(null, "Successful processed payment for Summer.");
                } else {
                    JOptionPane.showMessageDialog(null, "Encountered problems processing payment. Please try again.");
                }
            }
        }
    }
    
    private int orNo(){
        String orNo = officialReceiptDaoImpl.getNextAvailableOrNoForPaymentBySchoolYearId(currentSchoolYear.getSchoolYearId());
        return Integer.parseInt(orNo);
    }
    
    private Payment payment() {
        Payment payment = new Payment();
        payment.setCashier(user);
        payment.setParticulars(particulars());
        payment.setAmountReceived(BigDecimal.valueOf(Double.parseDouble(dialog.getJtfTendered().getText().trim())));
        payment.setAmountCharged(BigDecimal.valueOf(Double.parseDouble(dialog.getJtfAmountCharged().getText().trim())));
        payment.setOrNo(orNo());
        return payment;
    }
    
    private List<Particular> particulars() {
        List<Particular> particularList = new ArrayList<>();
        JTable table = dialog.getJtblSummerFees();
        int divisor = table.getRowCount();
        BigDecimal totalAmountCharged = BigDecimal.valueOf(Double.parseDouble(dialog.getJtfAmountCharged().getText().trim()));
        BigDecimal amountChargedPerParticular = totalAmountCharged.divide(BigDecimal.valueOf(divisor));
        for (int i = 0; i < table.getRowCount(); i++) {
            String balancebreakdownName = table.getValueAt(i, 0).toString().trim();
            Particular particular = new Particular();
            particular.setName(balancebreakdownName);
            particular.setAmountPaid(amountChargedPerParticular.setScale(2,BigDecimal.ROUND_HALF_UP));
            particularList.add(particular);
        }
        return particularList;
    }
    
    private PaymentTerm paymentTerm() {
        int paymentTermId = paymentTermDaoImpl.getPaymentTermIDByName("Cash");
        PaymentTerm paymentTerm = paymentTermDaoImpl.getPaymentTermByPaymentTermId(paymentTermId);
        return paymentTerm;
    }
    
    private List<BalanceBreakDownFee> balanceBreakDownFeeList(JTable t) {
        List<BalanceBreakDownFee> balanceBreakDownList = new ArrayList<>();
        try {
            for (int row = 0; row < t.getRowCount(); row++) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = format.parse("1970-01-01");
                String name = t.getValueAt(row, 0).toString().trim();
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(row, 2).toString().trim())).setScale(2, BigDecimal.ROUND_HALF_UP);
                String category = (t.getValueAt(row, 3).toString().trim());
                
                BalanceBreakDownFee bbFee = new BalanceBreakDownFee();
                bbFee.setName(name);
                bbFee.setAmountDue(amount);
                bbFee.setDeadline(deadline);
                bbFee.setCategory(category);
                balanceBreakDownList.add(bbFee);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return balanceBreakDownList;
    }
}
