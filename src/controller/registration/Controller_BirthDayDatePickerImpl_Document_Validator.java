/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.registration;

import java.awt.Color;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_BirthDayDatePickerImpl_Document_Validator implements DocumentListener {

    private final View_Panel_Registration view;

    public Controller_BirthDayDatePickerImpl_Document_Validator(View_Panel_Registration view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        validateBirthdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validateBirthdate();
    }

    private void validateBirthdate() {
        Date selectedDate = (Date) view.getDpBirthday().getModel().getValue();
        if (selectedDate != null) {
            LocalDate birthDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            int age = Period.between(birthDate, now).getYears();
            if (age >= 0) {
                view.getJlblAge().setText("" + age + " years old");
            }
            if (age <= 0) {
                view.getJlblAge().setForeground(Color.YELLOW);
                view.getJlblAge().setBackground(Color.RED);
                view.getJlblAge().setText("" + age + " year old is NOT valid");
            } else {
                view.getJlblAge().setForeground(Color.BLACK);
                view.getJlblAge().setBackground(Color.WHITE);
            }
        }
    }

}
