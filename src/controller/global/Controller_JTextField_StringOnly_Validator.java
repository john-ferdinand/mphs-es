/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.global;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Jordan
 */
public class Controller_JTextField_StringOnly_Validator implements DocumentListener{

    private final JTextField textField; 
    private boolean hasNumber;

    public Controller_JTextField_StringOnly_Validator(JTextField textField) {
        this.textField = textField;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        String str = textField.getText().trim();
        if (str.matches(".*\\d+.*")) {
            textField.setBackground(Color.RED);
            hasNumber = true;
        }else{
            textField.setBackground(Color.WHITE);
            hasNumber = false;
        }
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        String str = textField.getText().trim();
        if (str.matches(".*\\d+.*")) {
            textField.setBackground(Color.RED);
            hasNumber = true;
        }else{
            textField.setBackground(Color.WHITE);
            hasNumber = false;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String str = textField.getText().trim();
        if (str.matches(".*\\d+.*")) {
            textField.setBackground(Color.RED);
            hasNumber = true;
        }else{
            textField.setBackground(Color.WHITE);
            hasNumber = false;
        }
    }

    public boolean hasNumber() {
        return hasNumber;
    }
    
    
    
}
