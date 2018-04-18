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
public class Controller_DocumentListener_FirstQuarterDatePicker implements DocumentListener {

    private final DialogPaymentScheduleCrud view;
    private final DateUtil dateUtil;

    public Controller_DocumentListener_FirstQuarterDatePicker(DialogPaymentScheduleCrud view) {
        this.view = view;
        this.dateUtil = new DateUtil();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        generateSemDates();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void generateSemDates() {
        try {
            Date firstQuarterUtilDate = dateUtil.toUtilDate(view.getDpFirstQuarterTo().getJFormattedTextField().getText().trim());
            if (isWeekend(dateUtil.toLocalDate(firstQuarterUtilDate))) {
                view.getDpFirstQuarterTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstQuarterTo().getJFormattedTextField().setBackground(Color.RED);
            } else {
                view.getDpFirstQuarterTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstQuarterTo().getJFormattedTextField().setBackground(Color.GREEN);
            }

            int quarterMonthIncrements = 3;
            for (JDatePickerImpl quarterDp : getQuarterDatePickers()) {
                LocalDate localDate = dateUtil.toLocalDate(firstQuarterUtilDate);
                LocalDate date = isWeekend(localDate.plusMonths(quarterMonthIncrements)) == true ? localDate.plusMonths(quarterMonthIncrements).plusDays(2) : localDate.plusMonths(quarterMonthIncrements);

                quarterDp.getJFormattedTextField().setText("" + date);
                quarterMonthIncrements += 1;
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

    private List<JDatePickerImpl> getQuarterDatePickers() {
        List<JDatePickerImpl> dpSemesters = Arrays.asList(
                view.getDpSecondQuarterTo(), view.getDpThirdQuarterTo(), view.getDpFourthQuarterTo()
        );
        return dpSemesters;
    }

}
