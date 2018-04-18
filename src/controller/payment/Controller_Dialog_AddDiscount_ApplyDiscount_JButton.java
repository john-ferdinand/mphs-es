package controller.payment;

import daoimpl.GradeLevelDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.discount.Discount;
import model.fee.Fee;
import model.gradelevel.GradeLevel;
import model.paymentterm.PaymentTerm;
import model.student.Student;
import model.user.User;
import service.tuition.TuitionPopulator;
import view.payment.Dialog_AddDiscount;
import view.payment.Panel_Payment;

/**
 *
 * @author Jordan
 */
public class Controller_Dialog_AddDiscount_ApplyDiscount_JButton implements ActionListener {

    private final Panel_Payment panelPayment;
    private final Dialog_AddDiscount dialogAddDiscount;
    private List<Fee> feeList;
    private boolean hasStudentNo;
    private Student student;

    private final User user;

    public Controller_Dialog_AddDiscount_ApplyDiscount_JButton(Panel_Payment panelPayment, Dialog_AddDiscount dialogAddDiscount) {
        this.user = panelPayment.getUser();
        this.panelPayment = panelPayment;
        this.dialogAddDiscount = dialogAddDiscount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        initResources();
        if (hasStudentNo) {
            loadForStudent();
        } else {
            loadForStudentApplicant();
        }
        dialogAddDiscount.dispose();
    }

    private void initResources() {
        feeList = panelPayment.getFeeList();
        hasStudentNo = panelPayment.getIsStudent();
        student = panelPayment.getStudent();
    }

    private void loadForStudent() {

    }

    private void loadForStudentApplicant() {
        PaymentTerm paymentTerm = getPaymentTerm();
        List<Discount> discounts = getDiscountsFor(feeList);
        BigDecimal discountAmount = getDiscountAmountSumOf(feeList);
        loadDiscountedBreakDown(feeList, paymentTerm, discounts);
        panelPayment.getJtfDiscount().setText("" + discountAmount);
        panelPayment.setDiscounts(discounts);
    }
    
    private BigDecimal getSumOf(List<Fee> fees) {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        for (Fee f : fees) {
            if (!f.getFeeCategory().getName().trim().equalsIgnoreCase("summer")) {
                sum = sum.add(f.getAmount());
            }
        }
        return sum;
    }

    private List<Discount> getDiscountsFor(List<Fee>fees) {
        List<Discount> discounts = new ArrayList<>();
        JTable t = dialogAddDiscount.getJtblAppliedDiscount();
        for (int row = 0; row < t.getRowCount(); row++) {
            Discount discount = new Discount();
            discount.setDiscountID(Integer.parseInt(t.getValueAt(row, 0).toString().trim()));
            discount.setDiscountName(t.getValueAt(row, 1).toString().trim());
            discount.setPercent(Integer.parseInt(t.getValueAt(row, 2).toString().trim()));
            discount.setDescription(t.getValueAt(row, 3).toString().trim());
            discount.setProvision(t.getValueAt(row, 4).toString().trim());
            discount.setCreatedBy(user);
            BigDecimal discountAmount = getSumOf(fees).multiply(BigDecimal.valueOf(discount.getPercent()).divide(BigDecimal.valueOf(100)));
            discount.setAmount(discountAmount);
            discounts.add(discount);
        }
        return discounts;
    }

    private BigDecimal getDiscountAmountSumOf(List<Fee> fees) {
        BigDecimal feeSum = getSumOf(fees);
        double totalPercent = 0.0;
        for (Discount discount : getDiscountsFor(fees)) {
            totalPercent += discount.getPercent();
        }
        BigDecimal discountAmount = feeSum.multiply(BigDecimal.valueOf(totalPercent).divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
        return discountAmount;
    }

    private PaymentTerm getPaymentTerm() {
        PaymentTermDaoImpl paymentTermDaoImpl = new PaymentTermDaoImpl();
        PaymentTerm paymentTerm = new PaymentTerm();
        if (panelPayment.getJcmbPaymentTerm().getSelectedIndex() > -1) {
            String paymentTermName = panelPayment.getJcmbPaymentTerm().getSelectedItem().toString().trim();
            int paymentTermID = paymentTermDaoImpl.getPaymentTermIDByName(paymentTermName);
            paymentTerm = paymentTermDaoImpl.getPaymentTermByPaymentTermId(paymentTermID);
        }
        return paymentTerm;
    }

    private void loadDiscountedBreakDown(List<Fee> fees, PaymentTerm paymentTerm, List<Discount> discounts) {
        GradeLevelDaoImpl gradeLevelDaoImpl = new GradeLevelDaoImpl();
        String sGradeLevelNo = panelPayment.getJtfGradeLevel().getText().trim();
        int levelNo = 0;
        if(sGradeLevelNo.trim().equalsIgnoreCase("Kindergarten")){
            levelNo = 0;
        }else{
            levelNo = Integer.parseInt(sGradeLevelNo.trim());
        }
        int gradeLevelId = gradeLevelDaoImpl.getId(levelNo);
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setGradeLevelID(gradeLevelId);

        if (panelPayment.getJtblBalanceBreakDown().getRowCount() > 0) {
            TuitionPopulator tuitionPopulator = new TuitionPopulator(fees, paymentTerm, discounts,gradeLevel);
            DefaultTableModel tableModel = tuitionPopulator.getTuitionItemsTableModel(panelPayment.getJtblBalanceBreakDown(),gradeLevel);
            panelPayment.getJtblBalanceBreakDown().setModel(tableModel);
        } else {
            dialogAddDiscount.dispose();
        }
    }

}
