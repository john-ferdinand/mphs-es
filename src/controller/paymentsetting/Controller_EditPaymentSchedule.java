/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.paymentsetting;

import daoimpl.PaymentTermDaoImpl;
import daoimpl.PeriodDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.paymentterm.PaymentTerm;
import model.period.Period;
import utility.date.DateUtil;
import utility.form.FormInspector;
import utility.form.FormValidator;
import view.paymentsetting.DialogPaymentScheduleCrud;

/**
 *
 * @author Jordan
 */
public class Controller_EditPaymentSchedule extends FormInspector implements ActionListener, FormValidator {
    
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private PaymentTermDaoImpl paymentTermDaoImpl;
    private PeriodDaoImpl periodDaoImpl;
    private DateUtil dateUtil;

    private final DialogPaymentScheduleCrud view;

    public Controller_EditPaymentSchedule(DialogPaymentScheduleCrud view) {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        paymentTermDaoImpl = new PaymentTermDaoImpl();
        periodDaoImpl = new PeriodDaoImpl();
        dateUtil = new DateUtil();
        this.view = view;
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = (!hasEmptyFields(view.getJpnlSchoolYear())) && !hasEmptyFields(view.getJpnlSemestral())
                && !hasEmptyFields(view.getJpnlQuarterly()) && !hasEmptyFields(view.getJpnlMonthly());
        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (formIsValid()) {
            int choice = JOptionPane.showConfirmDialog(null, "Update Payment Schedule?", "Update", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                updateSchedule();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check if all fields are filled.");
        }
    }

    private void updateSchedule() {
        List<PaymentTerm> paymentTerms = new ArrayList<>();
        paymentTerms.add(getDownpayment());
        paymentTerms.add(getSemestral());
        paymentTerms.add(getQuarterly());
        paymentTerms.add(getMonthly());

        boolean isAdded = paymentTermDaoImpl.updatePaymentSchedule(paymentTerms);
        if (isAdded) {
            JOptionPane.showMessageDialog(null, "Successfully updated payment Schedule.");
        } else {
            JOptionPane.showMessageDialog(null, "Error encountered. ");

        }
    }

    
    private PaymentTerm getDownpayment() {
        PaymentTerm downPayment = new PaymentTerm();
        List<Period> downPaymentPeriods = new ArrayList<>();
        Period downPaymentPeriod = new Period();
        try {
            downPaymentPeriod.setPeriodName("Downpayment");
            downPaymentPeriod.setPaymentDeadline(dateUtil.toUtilDate(view.getDpDownpayment().getJFormattedTextField().getText().trim()));
            downPaymentPeriods.add(downPaymentPeriod);

            downPayment.setPaymentTermId(paymentTermDaoImpl.getPaymentTermIDByName("Cash"));
            downPayment.setSchoolYearId(schoolYearDaoImpl.getId(Integer.parseInt(view.getJcmbSchoolYear().getSelectedItem().toString())));
            downPayment.setPeriods(downPaymentPeriods);
            downPayment.setPenaltyAmount(BigDecimal.valueOf(Double.parseDouble(view.getJtfDownpaymentPenalty().getText().trim())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downPayment;
    }
    
    private PaymentTerm getSemestral() {
        PaymentTerm semestral = new PaymentTerm();
        List<Period> semestralPeriods = new ArrayList<>();
        Period firstSemPeriod = new Period();
        Period secondSemPeriod = new Period();
        try {
            firstSemPeriod.setPeriodName("1st Semester");
            firstSemPeriod.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFirstSemTo().getJFormattedTextField().getText().trim()));
            secondSemPeriod.setPeriodName("2nd Semester");
            secondSemPeriod.setPaymentDeadline(dateUtil.toUtilDate(view.getDpSecondSemTo().getJFormattedTextField().getText().trim()));
            semestralPeriods.add(firstSemPeriod);
            semestralPeriods.add(secondSemPeriod);

            semestral.setPaymentTermId(paymentTermDaoImpl.getPaymentTermIDByName("Semestral"));
            semestral.setSchoolYearId(schoolYearDaoImpl.getId(Integer.parseInt(view.getJcmbSchoolYear().getSelectedItem().toString())));
            semestral.setPeriods(semestralPeriods);
            semestral.setPenaltyAmount(BigDecimal.valueOf(Double.parseDouble(view.getJtfSemestralPenalty().getText().trim())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return semestral;
    }

    private PaymentTerm getQuarterly() {
        PaymentTerm quarterly = new PaymentTerm();
        List<Period> quarterlyPeriods = new ArrayList<>();
        Period firstQuarter = new Period();
        Period secondQuarter = new Period();
        Period thirdQuarter = new Period();
        Period fourthQuarter = new Period();
        try {
            firstQuarter.setPeriodName("1st Quarter");
            firstQuarter.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFirstQuarterTo().getJFormattedTextField().getText().trim()));
            secondQuarter.setPeriodName("2nd Quarter");
            secondQuarter.setPaymentDeadline(dateUtil.toUtilDate(view.getDpSecondQuarterTo().getJFormattedTextField().getText().trim()));
            thirdQuarter.setPeriodName("3rd Quarter");
            thirdQuarter.setPaymentDeadline(dateUtil.toUtilDate(view.getDpThirdQuarterTo().getJFormattedTextField().getText().trim()));
            fourthQuarter.setPeriodName("4th Quarter");
            fourthQuarter.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFourthQuarterTo().getJFormattedTextField().getText().trim()));
            quarterlyPeriods.add(firstQuarter);
            quarterlyPeriods.add(secondQuarter);
            quarterlyPeriods.add(thirdQuarter);
            quarterlyPeriods.add(fourthQuarter);

            quarterly.setPaymentTermId(paymentTermDaoImpl.getPaymentTermIDByName("Quarterly"));
            quarterly.setSchoolYearId(schoolYearDaoImpl.getId(Integer.parseInt(view.getJcmbSchoolYear().getSelectedItem().toString().trim())));
            quarterly.setPeriods(quarterlyPeriods);
            quarterly.setPenaltyAmount(BigDecimal.valueOf(Double.parseDouble(view.getJtfQuarterlyPenalty().getText().trim())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quarterly;
    }

    private PaymentTerm getMonthly() {
        PaymentTerm monthly = new PaymentTerm();
        List<Period> monthlyPeriods = new ArrayList<>();
        Period firstMonth = new Period();
        Period secondMonth = new Period();
        Period thirdMonth = new Period();
        Period fourthMonth = new Period();
        Period fifthMonth = new Period();
        Period sixthMonth = new Period();
        Period seventhMonth = new Period();
        Period eighthMonth = new Period();
        Period ninthMonth = new Period();
        Period tenthMonth = new Period();

        try {
            firstMonth.setPeriodName("1st Month");
            firstMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFirstMonthTo().getJFormattedTextField().getText().trim()));
            secondMonth.setPeriodName("2nd Month");
            secondMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpSecondMonthTo().getJFormattedTextField().getText().trim()));
            thirdMonth.setPeriodName("3rd Month");
            thirdMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpThirdMonthTo().getJFormattedTextField().getText().trim()));
            fourthMonth.setPeriodName("4th Month");
            fourthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFourthMonthTo().getJFormattedTextField().getText().trim()));
            fifthMonth.setPeriodName("5th Month");
            fifthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpFifthMonthTo().getJFormattedTextField().getText().trim()));
            sixthMonth.setPeriodName("6th Month");
            sixthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpSixthMonthTo().getJFormattedTextField().getText().trim()));
            seventhMonth.setPeriodName("7th Month");
            seventhMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpSeventhMonthTo().getJFormattedTextField().getText().trim()));
            eighthMonth.setPeriodName("8th Month");
            eighthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpEighthMonthTo().getJFormattedTextField().getText().trim()));
            ninthMonth.setPeriodName("9th Month");
            ninthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpNinthMonthTo().getJFormattedTextField().getText().trim()));
            tenthMonth.setPeriodName("10th Month");
            tenthMonth.setPaymentDeadline(dateUtil.toUtilDate(view.getDpTenthMonthTo().getJFormattedTextField().getText().trim()));
            monthlyPeriods.add(firstMonth);
            monthlyPeriods.add(secondMonth);
            monthlyPeriods.add(thirdMonth);
            monthlyPeriods.add(fourthMonth);
            monthlyPeriods.add(fifthMonth);
            monthlyPeriods.add(sixthMonth);
            monthlyPeriods.add(seventhMonth);
            monthlyPeriods.add(eighthMonth);
            monthlyPeriods.add(ninthMonth);
            monthlyPeriods.add(tenthMonth);

            monthly.setPaymentTermId(paymentTermDaoImpl.getPaymentTermIDByName("Monthly"));
            monthly.setSchoolYearId(schoolYearDaoImpl.getId(Integer.parseInt(view.getJcmbSchoolYear().getSelectedItem().toString().trim())));
            monthly.setPeriods(monthlyPeriods);
            monthly.setPenaltyAmount(BigDecimal.valueOf(Double.parseDouble(view.getJtfMonthlyPenalty().getText().trim())));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthly;
    }
}
