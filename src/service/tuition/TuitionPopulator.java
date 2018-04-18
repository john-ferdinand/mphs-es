/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.tuition;

import daoimpl.FeeDaoImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.discount.Discount;
import model.fee.Fee;
import model.gradelevel.GradeLevel;
import model.paymentterm.PaymentTerm;
import model.period.Period;

/**
 *
 * @author Jordan
 */
public class TuitionPopulator {

    private final List<Fee> fees;
    private final PaymentTerm paymentTerm;
    private List<Discount> discounts;
    private final FeeDaoImpl feeDaoImpl;
    private final GradeLevel gradeLevel;

    public TuitionPopulator(List<Fee> fees, PaymentTerm paymentTerm, GradeLevel gradeLevel) {
        this.fees = fees;
        this.gradeLevel = gradeLevel;
        this.paymentTerm = paymentTerm;
        discounts = new ArrayList<>();
        this.feeDaoImpl = new FeeDaoImpl();
    }
    
    public TuitionPopulator(List<Fee> fees, PaymentTerm paymentTerm, List<Discount> discounts, GradeLevel gradeLevel) {
        this.fees = fees;
        this.paymentTerm = paymentTerm;
        this.discounts = discounts;
        this.feeDaoImpl = new FeeDaoImpl();
        this.gradeLevel = gradeLevel;
    }

    private BigDecimal getDiscount(){
        BigDecimal feeSum = getFeeSum();
        Double totalPercent = 0.0;
        for(Discount d : discounts){
            totalPercent+= d.getPercent();
        }
        BigDecimal discountAmount = feeSum.multiply(BigDecimal.valueOf(totalPercent)).divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return discountAmount;
    }
    
    private BigDecimal getFeeSum(){
        BigDecimal downpayment = feeDaoImpl.getDownpaymentByGradeLevel(gradeLevel);
        BigDecimal others = feeDaoImpl.getSumOfOthersFeeByGradeLevelId(gradeLevel);
        BigDecimal misc = feeDaoImpl.getSumOfMiscFeesByGradeLeveLId(gradeLevel);
        BigDecimal basic = feeDaoImpl.getBasicByGradeLevel(gradeLevel);
        BigDecimal feeSum = downpayment.add(others).add(misc).add(basic);
        return feeSum;
    }
    
    public BigDecimal getPerPeriodAmount() {
        BigDecimal downpayment = feeDaoImpl.getDownpaymentByGradeLevel(gradeLevel);
        BigDecimal others = feeDaoImpl.getSumOfOthersFeeByGradeLevelId(gradeLevel);
        BigDecimal misc = feeDaoImpl.getSumOfMiscFeesByGradeLeveLId(gradeLevel);
        BigDecimal basic = feeDaoImpl.getBasicByGradeLevel(gradeLevel);
        BigDecimal feeSum = basic.add(misc).add(others);
        if(discounts.size() > 0){
            feeSum = feeSum.subtract(getDiscount());
        }
        
        BigDecimal divisor = BigDecimal.valueOf(paymentTerm.getDivisor());
        BigDecimal perPeriod = new BigDecimal(BigInteger.ZERO);
        perPeriod = perPeriod.add(feeSum.subtract(downpayment).divide(divisor));
        return perPeriod;
    }

    private BigDecimal getDownpaymentAmount() {
        BigDecimal downpayment = feeDaoImpl.getDownpaymentByGradeLevel(gradeLevel);
        return downpayment;
    }

    private void addCash(DefaultTableModel tableModel,GradeLevel gradeLevel) {
        List<Period> periods = paymentTerm.getPeriods();
        for (Period p : periods) {
            String name = paymentTerm.getPaymentTermName().trim() + " (Tuition)";
            BigDecimal amountDue = getFeeSum().subtract(feeDaoImpl.getDownpaymentByGradeLevel(gradeLevel));
            Date dueDate = p.getPaymentDeadline();
            String category = paymentTerm.getPaymentTermName().trim();
            Object[] perPeriodRowData = {name, amountDue, amountDue, dueDate, "No", category};
            tableModel.addRow(perPeriodRowData);
        }
    }

    private void addPerPeriodFees(JTable table,DefaultTableModel tableModel) {
        List<Period> periods = paymentTerm.getPeriods();
        for (Period p : periods) {
            String name = p.getPeriodName();
            BigDecimal amountDue = name.trim().equalsIgnoreCase("Downpayment") ? getDownpaymentAmount() : getPerPeriodAmount();
            Date dueDate = p.getPaymentDeadline();
            String category = name.trim().equalsIgnoreCase("Downpayment")? "Downpayment" : "Balance";
            Object[] perPeriodRowData = {name, amountDue, amountDue, dueDate, "No", category};
            tableModel.addRow(perPeriodRowData);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
//        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public DefaultTableModel getTuitionItemsTableModel(JTable table,GradeLevel gradeLevel) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        boolean cash = paymentTerm.getPaymentTermName().trim().equalsIgnoreCase("Cash");
        if (cash) {
            addCash(tableModel,gradeLevel);
        } else {
            addPerPeriodFees(table,tableModel);
        }
       
        return tableModel;
    }
}
