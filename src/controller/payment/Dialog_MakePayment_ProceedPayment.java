package controller.payment;

import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.particulars.Particular;
import model.payment.Payment;
import model.penalty.Penalty;
import model.tuitionfee.Tuition;
import model.user.User;
import view.payment.Dialog_MakePayment;

/**
 *
 * @author Jordan
 */
public class Dialog_MakePayment_ProceedPayment implements ActionListener {

    private final boolean isStudent;
    private final boolean hasTuitionBalance;
    private final Dialog_MakePayment view;
    private final Tuition tuition;
    private final TuitionFeeDaoImpl tuitionFeeDaoImpl;
    private final User user;

    public Dialog_MakePayment_ProceedPayment(boolean isStudent, boolean hasTuitionBalance, Dialog_MakePayment view, Tuition tuition, TuitionFeeDaoImpl tuitionFeeDaoImpl, User user) {
        this.isStudent = isStudent;
        this.hasTuitionBalance = hasTuitionBalance;
        this.view = view;
        this.tuition = tuition;
        this.tuitionFeeDaoImpl = tuitionFeeDaoImpl;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (proceedPayment()) {
            JOptionPane.showMessageDialog(null, "Successfully processed.");
            view.dispose();
        } else {
//            JOptionPane.showMessageDialog(null, "Encountered problem processing request. Please contact your support.");
        }
    }

    private boolean proceedPayment() {
        boolean isSuccessful = false;
        int choice = JOptionPane.showConfirmDialog(null, "Proceed with payment?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            Payment payment = getPayment();
            tuition.setPayment(payment);
            if (!isStudent && !hasTuitionBalance) {
                isSuccessful = tuitionFeeDaoImpl.payPrimary(tuition);
            } else if (isStudent && hasTuitionBalance) {
                isSuccessful = tuitionFeeDaoImpl.payBalanceOf(tuition);
            }else if(isStudent && !hasTuitionBalance){
                isSuccessful = tuitionFeeDaoImpl.payNewSchoolYear(tuition);
            }
        }
        return isSuccessful;
    }

    private Payment getPayment() {
        Payment payment = new Payment();
        if (!view.getJtfTendered().getText().trim().isEmpty() && !view.getJtfTotal().getText().trim().isEmpty()) {
            if (paymentInputAreValid()) {
                BigDecimal total = total();
                BigDecimal tendered = amountTendered();
                BigDecimal amountCharged = new BigDecimal(BigInteger.ZERO);
                if (total.compareTo(tendered) == 1) { //greater than
                    amountCharged = tendered;
                } else {
                    amountCharged = total;
                }

                int orNo = Integer.parseInt(view.getJlblOrNo().getText().trim());
                payment.setParticulars(particulars());
                payment.setAmountReceived(tendered);
                payment.setAmountCharged(amountCharged);
                payment.setOrNo(orNo);
                payment.setCashier(user);
            }
        }
        return payment;
    }

    private boolean paymentInputAreValid() {
        boolean isValid = false;
        String amountReceived = view.getJtfTendered().getText().trim();
        String amountCharged = view.getJtfTotal().getText().trim();
        if (isValidAmount(amountReceived) && isValidAmount(amountCharged)) {
            isValid = true;
        }
        return isValid;
    }

    private BigDecimal amountTendered() {
        String amountReceived = view.getJtfTendered().getText().trim();
        return BigDecimal.valueOf(Double.parseDouble(amountReceived)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal total() {
        String amountCharged = view.getJtfTotal().getText().trim();
        return BigDecimal.valueOf(Double.parseDouble(amountCharged)).setScale(2, RoundingMode.HALF_UP);
    }

    private List<Particular> particulars() {
        List<Particular> particularList = new ArrayList<>();
        JTable t = view.getJtblPaymentBreakDown();
        BigDecimal tendered = amountTendered();
        for (int row = 0; row < t.getRowCount(); row++) {
            String balancebreakdownName = t.getValueAt(row, 0).toString().trim();
            BigDecimal balanceItemAmount = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(row, 1).toString().trim()));
            Penalty penalty = (t.getValueAt(row, 2) instanceof Penalty) ? (Penalty) t.getValueAt(row, 2) : null;
            
            Particular particular = new Particular();
            particular.setName(balancebreakdownName);
            BigDecimal amountPaid = new BigDecimal(BigInteger.ZERO);
            
            if (penalty != null) {
                BigDecimal amountPaidToPenalty = new BigDecimal(BigInteger.ZERO);
                if(tendered.compareTo(penalty.getPenaltyAmount()) == 1){
                    amountPaidToPenalty = penalty.getPenaltyAmount();
                    penalty.setAmountPaid(amountPaidToPenalty);
                    tendered = tendered.subtract(penalty.getPenaltyAmount());
                }else if (tendered.compareTo(penalty.getPenaltyAmount()) == -1 && tendered.compareTo(BigDecimal.ZERO) != 0) {
                    amountPaidToPenalty = tendered;
                    penalty.setAmountPaid(amountPaidToPenalty);
                    tendered = BigDecimal.ZERO;
                } else if (tendered.compareTo(penalty.getPenaltyAmount()) == 0) {
                    amountPaidToPenalty = tendered;
                    penalty.setAmountPaid(amountPaidToPenalty);
                    tendered = BigDecimal.ZERO;
                }
                particular.setPenalty(penalty);
            }

            System.out.println("Tendered After deducting penalty: " + tendered);
            boolean tendered_GT_balance = (tendered.compareTo(balanceItemAmount) == 1);
            boolean tendered_LT_balance = (tendered.compareTo(balanceItemAmount) == -1);
            boolean tendered_notEQ_zero = (tendered.compareTo(BigDecimal.ZERO) != 0);
            boolean tendered_EQ_balance = (tendered.compareTo(balanceItemAmount) == 0);
            
            if (tendered_GT_balance) { //>
                amountPaid = balanceItemAmount;
                particular.setAmountPaid(amountPaid);
                particularList.add(particular);
                tendered = tendered.subtract(balanceItemAmount);
                System.out.println("@Greater than Tendered: " + tendered);
                System.out.println("@Greater than Amount Paid: " + amountPaid);
            } else if (tendered_LT_balance && tendered_notEQ_zero) { //<
                amountPaid = tendered;
                particular.setAmountPaid(amountPaid);
                particularList.add(particular);
                tendered = BigDecimal.ZERO;
                System.out.println("@LessThan Tendered: " + tendered);
                System.out.println("@LessThan Amount Paid: " + amountPaid);
            } else if (tendered_EQ_balance) { // tendered equal to balanceItemAmount
                amountPaid = tendered;
                particular.setAmountPaid(amountPaid);
                particularList.add(particular);
                tendered = BigDecimal.ZERO;
                System.out.println("@Equal Tendered: " + tendered);
                System.out.println("@Equal Amount Paid: " + amountPaid);
            }
        }
        System.out.println("Particular List Size: "+particularList.size());
        return particularList;
    }

    private boolean isValidAmount(String s) {
        boolean isValid;
        try {
            Double.parseDouble(s.trim());
            isValid = true;
        } catch (Exception e) {
            isValid = false;
            e.printStackTrace();
        }
        return isValid;
    }
}
