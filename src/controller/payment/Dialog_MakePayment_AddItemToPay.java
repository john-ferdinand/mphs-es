
package controller.payment;

import daoimpl.PenaltyDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.penalty.Penalty;
import model.tuitionfee.Tuition;
import utility.date.DateUtil;
import view.payment.Dialog_MakePayment;

/**
 *
 * @author Jordan
 */
public class Dialog_MakePayment_AddItemToPay implements ActionListener{

    private final boolean hasTuitionRecord;
    private final Dialog_MakePayment view;
    private final Tuition tuition;
    private final PenaltyDaoImpl penaltyDaoImpl;
    
    public Dialog_MakePayment_AddItemToPay(boolean hasTuitionRecord,Dialog_MakePayment view, Tuition tuition){
        this.view = view;
        this.hasTuitionRecord = hasTuitionRecord;
        this.tuition = tuition;
        this.penaltyDaoImpl = new PenaltyDaoImpl();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        add();
    }
    
    private boolean pastDueDate(BalanceBreakDownFee b){
        DateUtil dateUtil = new DateUtil();
        Date dateToday = new Date();
        return dateUtil.setTimeToMidnightOf(dateToday).after(dateUtil.setTimeToMidnightOf(b.getDeadline()));
    }
    
    private void add() {
        List<BalanceBreakDownFee> bbFeeListToAdd = new ArrayList<>();
        BigDecimal penaltyAmount = tuition.getPaymentTerm().getPenaltyAmount();
        int tuitionSchoolYearId = tuition.getSchoolYearId();
        int registrationId = tuition.getStudent().getRegistration().getRegistrationId();
        if (view.getJcbDownPayment().isSelected()) {
            for (BalanceBreakDownFee b : tuition.getBalanceBreakDownFees()) {
                if (b.getCategory().trim().equalsIgnoreCase("Downpayment") || b.getCategory().trim().equalsIgnoreCase("D")) {
                    if (!b.isFullyPaid()) {
                        if(pastDueDate(b)){
                            //if penalty does not exist for this item
                            if(!penaltyDaoImpl.hasExistingPenaltyFor(b.getName().trim(), tuitionSchoolYearId,registrationId)){
//                                JOptionPane.showMessageDialog(null, "has no existing payment for penalty for DP");
                                Penalty penalty = new Penalty();
                                penalty.setPenaltyAmount(penaltyAmount);
                                penalty.setPenaltyName(b.getName() + " Late Payment");
                                b.setPenalty(penalty);
                                addPenalty(b.getName(), penaltyAmount, b.getCategory());
                            }
                        }
                        bbFeeListToAdd.add(b);
                    } else {
                        JOptionPane.showMessageDialog(null, b.getName() + " is already paid.");
                    }
                }
            }
        }
        if (view.getJcbBalance().isSelected()) {
            String selectedBalanceItem = view.getJcmbBalance().getSelectedItem().toString().trim();
            for (BalanceBreakDownFee b : tuition.getBalanceBreakDownFees()) {
                if (b.getCategory().trim().equalsIgnoreCase("Balance") || b.getCategory().trim().equalsIgnoreCase("B")) {
                    if (!b.isFullyPaid() && b.getName().trim().equalsIgnoreCase(selectedBalanceItem)) {
                        if (pastDueDate(b)) {
                            if(!penaltyDaoImpl.hasExistingPenaltyFor(b.getName().trim(), tuitionSchoolYearId,registrationId)){
//                                JOptionPane.showMessageDialog(null, "has no existing payment for penalty for Balance");
                                Penalty penalty = new Penalty();
                                penalty.setPenaltyAmount(penaltyAmount);
                                penalty.setPenaltyName(b.getName() + " Late Payment");
                                b.setPenalty(penalty);
                                addPenalty(b.getName(),penaltyAmount,b.getCategory());
                            }
                        }
                        bbFeeListToAdd.add(b);
                    } else if (b.isFullyPaid() && b.getName().trim().equalsIgnoreCase(selectedBalanceItem)) {
                        JOptionPane.showMessageDialog(null, b.getName() + " is already paid.");
                    }
                }
            }
        }
        
        addRowToTable(bbFeeListToAdd);
    }
    
    private void addPenalty(String balanceName, BigDecimal amount, String category) {
        boolean isDownPayment = category.trim().equalsIgnoreCase("Downpayment") || category.trim().equalsIgnoreCase("D");
        boolean isBalance = category.trim().equalsIgnoreCase("Balance") || category.trim().equalsIgnoreCase("B");
        boolean penaltyHasValue = !view.getJtfPenalty().getText().trim().isEmpty();
        if (!balanceAlreadyOnTable(balanceName)) {
            if (penaltyHasValue && (isDownPayment || isBalance)) {
                BigDecimal penaltyTotal = BigDecimal.valueOf(Double.parseDouble(view.getJtfPenalty().getText().trim()));
                BigDecimal sum = penaltyTotal.add(amount);
                view.getJtfPenalty().setText("" + sum);
            } else {
                view.getJtfPenalty().setText("" + amount);
            }
        }
    }
    
    private boolean balanceAlreadyOnTable(String balanceName) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getJtblPaymentBreakDown().getModel();
        int count = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String bName = tableModel.getValueAt(row, 0).toString().trim();
            if (balanceName.equalsIgnoreCase(bName)) {
                count++;
            }
        }
        return count > 0;
    }
    
    private void addRowToTable(List<BalanceBreakDownFee> bbFeeList) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getJtblPaymentBreakDown().getModel();
        for (BalanceBreakDownFee b : bbFeeList) {
            if (!isDuplicate(b.getName().trim())) {
                if (hasTuitionRecord) {
                    Object[] rowData = {b.getName(), b.getBalance(), b.getPenalty()};
                    tableModel.addRow(rowData);
                } else {
                    Object[] rowData = {b.getName(), b.getAmountDue(), b.getPenalty()};
                    tableModel.addRow(rowData);
                }
            }
        }
    }
    
    private boolean isDuplicate(String name) {
        boolean isDuplicate = false;
        DefaultTableModel tableModel = (DefaultTableModel) view.getJtblPaymentBreakDown().getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (name.trim().equalsIgnoreCase(tableModel.getValueAt(i, 0).toString().trim())) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }
    
}
