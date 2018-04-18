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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdatepicker.impl.JDatePickerImpl;
import utility.date.DateUtil;
import view.paymentsetting.DialogPaymentScheduleCrud;

/**
 *
 * @author Jordan
 */
public class Controller_DocumentListener_DateWeekEndValidator implements DocumentListener{

    private final DialogPaymentScheduleCrud view;
    private final JDatePickerImpl datePicker;
    private final DateUtil dateUtil;
    
    public Controller_DocumentListener_DateWeekEndValidator(DialogPaymentScheduleCrud view, JDatePickerImpl datePicker) {
        this.view = view;
        this.datePicker = datePicker;
        this.dateUtil = new DateUtil();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            Date date = dateUtil.toUtilDate(datePicker.getJFormattedTextField().getText().trim());
            if(isWeekend(dateUtil.toLocalDate(date))){
                JOptionPane.showMessageDialog(null,"Weekend date found!");
                datePicker.getJFormattedTextField().setBackground(Color.RED);
                datePicker.getJFormattedTextField().setFont(new Font("Tahoma",1,14));
            }else{
                datePicker.getJFormattedTextField().setBackground(Color.GREEN);
                datePicker.getJFormattedTextField().setFont(new Font("Tahoma",1,14));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
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
    
}
