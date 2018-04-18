/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.paymentsetting;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdatepicker.impl.JDatePickerImpl;
import utility.date.DateUtil;
import view.paymentsetting.DialogPaymentScheduleCrud;

/**
 *
 * @author Jordan
 */
public class Controller_DocumentListener_FirstMonthDatePicker implements DocumentListener {

    private final DialogPaymentScheduleCrud view;
    private final DateUtil dateUtil;

    public Controller_DocumentListener_FirstMonthDatePicker(DialogPaymentScheduleCrud view) {
        this.view = view;
        this.dateUtil = new DateUtil();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        generateMonthDates();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void generateMonthDates() {
        try {
            Date firstMonthUtilDate = dateUtil.toUtilDate(view.getDpFirstMonthTo().getJFormattedTextField().getText().trim());
            if (isWeekend(dateUtil.toLocalDate(firstMonthUtilDate))) {
                view.getDpFirstMonthTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstMonthTo().getJFormattedTextField().setBackground(Color.RED);
            } else {
                view.getDpFirstMonthTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstMonthTo().getJFormattedTextField().setBackground(Color.GREEN);
            }
            
            int monthIncrement = 1;
            for (JDatePickerImpl monthDp : getMonthDatePickers()) {
                LocalDate localDate = dateUtil.toLocalDate(firstMonthUtilDate);
                LocalDate date = isWeekend(localDate.plusMonths(monthIncrement)) == true ? localDate.plusMonths(monthIncrement).plusDays(2) : localDate.plusMonths(monthIncrement);
                
                monthDp.getJFormattedTextField().setText("" + date);
                monthIncrement += 1;
            }
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    private List<JDatePickerImpl> getMonthDatePickers() {
        List<JDatePickerImpl> dpMonths = Arrays.asList(
                view.getDpSecondMonthTo(), view.getDpThirdMonthTo(), view.getDpFourthMonthTo(), view.getDpFifthMonthTo(),
                view.getDpSixthMonthTo(), view.getDpSeventhMonthTo(), view.getDpEighthMonthTo(), view.getDpNinthMonthTo(),
                view.getDpTenthMonthTo()
        );
        return dpMonths;
    }

}
