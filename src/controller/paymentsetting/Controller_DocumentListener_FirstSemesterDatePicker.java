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
public class Controller_DocumentListener_FirstSemesterDatePicker implements DocumentListener{
    
    private final DialogPaymentScheduleCrud view;
    private final DateUtil dateUtil;

    public Controller_DocumentListener_FirstSemesterDatePicker(DialogPaymentScheduleCrud view) {
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
            Date firstSemUtilDate = dateUtil.toUtilDate(view.getDpFirstSemTo().getJFormattedTextField().getText().trim());
            if (isWeekend(dateUtil.toLocalDate(firstSemUtilDate))) {
                view.getDpFirstSemTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstSemTo().getJFormattedTextField().setBackground(Color.RED);
            } else {
                view.getDpFirstSemTo().getJFormattedTextField().setFont(new Font("Tahoma", 1, 14));
                view.getDpFirstSemTo().getJFormattedTextField().setBackground(Color.GREEN);
            }
            
            int semMonthIncrement = 6;
            for (JDatePickerImpl semesterDp : getSemesterDatePickers()) {
                LocalDate localDate = dateUtil.toLocalDate(firstSemUtilDate);
                LocalDate date = isWeekend(localDate.plusMonths(semMonthIncrement)) == true ? localDate.plusMonths(semMonthIncrement).plusDays(2) : localDate.plusMonths(semMonthIncrement);
                
                semesterDp.getJFormattedTextField().setText("" + date);
                semMonthIncrement += 1;
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

    private List<JDatePickerImpl> getSemesterDatePickers() {
        List<JDatePickerImpl> dpSemesters = Arrays.asList(view.getDpSecondSemTo());
        return dpSemesters;
    }
    
}
